package com.wsdev.simplestock.domain.movement;

import com.wsdev.simplestock.common.exceptions.RecordNotFoundException;
import com.wsdev.simplestock.domain.movement.dto.request.MovementRequestDTO;
import com.wsdev.simplestock.domain.movement.dto.response.MovementResponseDTO;
import com.wsdev.simplestock.domain.movement.mapper.MovementMapper;
import com.wsdev.simplestock.domain.movement.model.Movement;
import com.wsdev.simplestock.domain.product.model.Product;
import com.wsdev.simplestock.domain.movement.model.enums.MovementType;
import com.wsdev.simplestock.domain.product.ProductRepository;
import com.wsdev.simplestock.common.utilities.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MovementService
{
    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private ProductRepository productRepository;

    /**
     * get movements
     * @return
     * @throws Exception
     */
    public Page<MovementResponseDTO> getMovements( Pageable pageable ) throws Exception
    {
        Page<Movement> movements = movementRepository.findAll( pageable );

        return movements.map( MovementMapper::entityToDto );
    }

    /**
     * Add movement
     * @param movementRequestDTO
     * @throws Exception
     */
    public void addMovement( MovementRequestDTO movementRequestDTO ) throws Exception
    {
        Validator.requiredNonNull( movementRequestDTO, new IllegalArgumentException( "Argunement 'MovementDTO' must not be null" ) );

        Product product = productRepository.findById( movementRequestDTO.getProductId() )
                .orElseThrow(( ) -> new RecordNotFoundException( movementRequestDTO.getProductId() ) );

        Movement movement = MovementMapper.dtoToEntity( movementRequestDTO );

        movement.setProduct( product );

        movementRepository.save( movement );
    }

    /**
     * Update movement
     * @param id
     * @param movementRequestDTO
     * @throws Exception
     */
    public void updateMovement( Long id, MovementRequestDTO movementRequestDTO )
    {
        Movement movement = movementRepository.findById( id )
                .orElseThrow( () -> new RecordNotFoundException( id ) );

        Product product = productRepository.findById( movementRequestDTO.getProductId() )
                .orElseThrow( () -> new RecordNotFoundException( movementRequestDTO.getProductId() ) );

        movement.setProduct( product );
        movement.setMovementType( movementRequestDTO.getMovementType() );
        movement.setQuantity( movementRequestDTO.getQuantity() );

        movementRepository.save( movement );
    }

    /**
     * entryProduct
     * @param movementRequestDTO
     * @throws Exception
     */
    public void entryProduct( MovementRequestDTO movementRequestDTO ) throws Exception
    {
        Validator.requiredNonNull( movementRequestDTO, new IllegalArgumentException( "argument 'movementRequestDTO' must not be null." ) );

        if( MovementType.INBOUND.equals( movementRequestDTO.getMovementType() ) )
        {
            Product product = productRepository.findById( movementRequestDTO.getProductId() )
                    .orElseThrow( () -> new RecordNotFoundException( movementRequestDTO.getProductId() ) );

            product.setQuantity( product.getQuantity() + movementRequestDTO.getQuantity() );
            productRepository.save( product );

            Movement newMovement = MovementMapper.dtoToEntity( movementRequestDTO );
            newMovement.setProduct(product);

            movementRepository.save( newMovement );
        }
    }

    /**
     * exitProduct
     * @param movementRequestDTO
     * @throws Exception
     */
    public void exitProduct( MovementRequestDTO movementRequestDTO ) throws Exception
    {
        Validator.requiredNonNull( movementRequestDTO, new IllegalArgumentException( "argument 'movementRequestDTO' must not be null." ) );

        if ( !MovementType.OUTBOUND.equals( movementRequestDTO.getMovementType() ) )
        {
            throw new IllegalArgumentException( "Movement type must be 'Saída'." );
        }

        if ( movementRequestDTO.getQuantity() <= 0 )
        {
            throw new IllegalArgumentException( "Quantity must be greater than zero." );
        }

        Product product = productRepository.findById( movementRequestDTO.getProductId() )
                .orElseThrow( () -> new RecordNotFoundException( movementRequestDTO.getProductId() ) );

        int newQuantity = product.getQuantity() - movementRequestDTO.getQuantity();

        if ( newQuantity < 0 )
        {
            throw new IllegalArgumentException( "Insufficient stock." );
        }

        product.setQuantity( newQuantity );

        productRepository.save( product );

        Movement newMovement = MovementMapper.dtoToEntity( movementRequestDTO );
        newMovement.setProduct( product );

        movementRepository.save( newMovement );
    }

    /**
     * Delete movement
     *
     * @param id
     * @throws Exception
     */
    public void deleteMovement( Long id ) throws Exception
    {
        Movement movement = movementRepository.findById( id ).orElseThrow( () -> new RecordNotFoundException( id ) );

        movementRepository.delete( movement );
    }
}
