package com.wsdev.simplestock.domain.movement.mapper;

import com.wsdev.simplestock.domain.movement.dto.request.MovementRequestDTO;
import com.wsdev.simplestock.domain.movement.dto.response.MovementResponseDTO;
import com.wsdev.simplestock.domain.movement.model.Movement;

public class MovementMapper
{
    /**
     * Entity to dto
     *
     * @param movement
     * @return
     */
    public static MovementResponseDTO entityToDto( Movement movement )
    {
        MovementResponseDTO movementResponseDTO = new MovementResponseDTO();

        movementResponseDTO.setId( movement.getId() );
        movementResponseDTO.setMovementType( movement.getMovementType() );
        movementResponseDTO.setQuantity( movement.getQuantity() );
        movementResponseDTO.setCreatedAt( movement.getCreatedAt() );

        return movementResponseDTO;
    }

    /**
     * Dto to entity
     *
     * @param movementRequestDTO
     * @return
     */
    public static Movement dtoToEntity( MovementRequestDTO movementRequestDTO )
    {
        Movement movement = new Movement();

        movement.setQuantity( movementRequestDTO.getQuantity() );
        movement.setMovementType( movementRequestDTO.getMovementType() );

        return movement;
    }
}