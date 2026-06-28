package com.wsdev.simplestock.dto.mapper;

import com.wsdev.simplestock.dto.MovementDTO;
import com.wsdev.simplestock.entity.Movement;

public class MovementMapper
{
    /**
     * Entity to dto
     *
     * @param movement
     * @return
     */
    public static MovementDTO entityToDto( Movement movement )
    {
        MovementDTO movementDTO = new MovementDTO();

        movementDTO.setProductDTO( ProductMapper.entityToDto( movement.getProduct() ) );
        movementDTO.setMovementType( movement.getMovementType() );
        movementDTO.setQuantity( movement.getQuantity() );

        return movementDTO;
    }

    /**
     * Dto to entity
     *
     * @param movementDTO
     * @return
     */
    public static Movement dtoToEntity( MovementDTO movementDTO )
    {
        Movement movement = new Movement();

        movement.setProduct( ProductMapper.dtoToEntity( movementDTO.getProductDTO() ) );
        movement.setQuantity( movementDTO.getQuantity() );
        movement.setMovementType( movementDTO.getMovementType() );
        return movement;
    }

}
