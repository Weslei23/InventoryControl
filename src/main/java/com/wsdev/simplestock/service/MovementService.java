package com.wsdev.simplestock.service;

import com.wsdev.simplestock.Exception.RecordNotFoundException;
import com.wsdev.simplestock.dto.MovementDTO;
import com.wsdev.simplestock.dto.mapper.MovementMapper;
import com.wsdev.simplestock.dto.mapper.ProductMapper;
import com.wsdev.simplestock.entity.Movement;
import com.wsdev.simplestock.entity.Product;
import com.wsdev.simplestock.entity.enums.MovementType;
import com.wsdev.simplestock.repository.MovementRepository;
import com.wsdev.simplestock.repository.ProductRepository;
import com.wsdev.simplestock.utilities.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<MovementDTO> getMovements() throws Exception
    {
        List<MovementDTO> movements = new ArrayList<>();

        for( Movement movement : movementRepository.findAll() )
        {
            movements.add( MovementMapper.entityToDto( movement ) );
        }

        return movements;
    }

    /**
     * Add movement
     * @param movementDTO
     * @throws Exception
     */
    public void addMovement( MovementDTO movementDTO ) throws Exception
    {
        Validator.requiredNonNull( movementDTO, new IllegalArgumentException( "Argunement 'MovementDTO' must not be null" ) );
        movementRepository.save( MovementMapper.dtoToEntity( movementDTO ) );
    }

    public void updateMovement( Long id, MovementDTO movementDTO ) throws Exception
    {
        Movement movement = movementRepository.findById( id ).orElseThrow( () -> new RecordNotFoundException( id ) );

        movement.setProduct( ProductMapper.dtoToEntity( movementDTO.getProductDTO() )  );
        movement.setMovementType( movementDTO.getMovementType() );

        movementRepository.save( movement );
    }

    public void entryProduct( MovementDTO movementDTO ) throws Exception
    {
        if( movementDTO.getMovementType() == null )
        {
            throw new IllegalArgumentException( "Argument 'Movement' must not be null" );
        }

        if( movementDTO.getProductDTO() == null )
        {
            throw new IllegalArgumentException( "Argument 'Product' must not be null" );
        }


        if( MovementType.INBOUND.equals( movementDTO.getMovementType() ) )
        {
            Product product = productRepository.findById( movementDTO.getProductDTO().getId() ).orElseThrow( () -> new RecordNotFoundException( movementDTO.getProductDTO().getId() ) );

            product.setQuantity( product.getQuantity() + movementDTO.getQuantity() );
            productRepository.save( product );

            Movement newMovement = new Movement();
            newMovement.setProduct( product );
            newMovement.setMovementType( movementDTO.getMovementType() );
            movementRepository.save( newMovement );
        }
    }

    public void exitProduct( MovementDTO movementDTO ) throws Exception
    {
        if( movementDTO.getMovementType() == null )
        {
            throw new IllegalArgumentException( "Argument 'Movement' must not be null" );
        }

        if( movementDTO.getProductDTO() == null )
        {
            throw new IllegalArgumentException( "Argument 'Product' must not be null" );
        }

        if ( MovementType.OUTBOUND.equals( movementDTO.getMovementType() ) )
        {
            Product product = productRepository.findById( movementDTO.getProductDTO().getId() ).orElseThrow( () -> new Exception( "Product not found." ) );

            product.setQuantity( product.getQuantity() - movementDTO.getQuantity() );
            productRepository.save( product );

            Movement newMovement = new Movement();
            newMovement.setProduct( product );
            newMovement.setMovementType( movementDTO.getMovementType() );

            movementRepository.save( newMovement );
        }
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
