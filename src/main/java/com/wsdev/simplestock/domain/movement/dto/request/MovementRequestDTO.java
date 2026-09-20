package com.wsdev.simplestock.domain.movement.dto.request;

import com.wsdev.simplestock.domain.movement.model.enums.MovementType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovementRequestDTO
{
    @NotNull( message = "argument 'productId' must not be null." )
    private Long productId;

    @NotNull( message = "argument 'movementType' must not be null." )
    private MovementType movementType;

    private int quantity;
}