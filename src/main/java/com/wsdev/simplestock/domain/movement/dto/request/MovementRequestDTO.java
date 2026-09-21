package com.wsdev.simplestock.domain.movement.dto.request;

import com.wsdev.simplestock.domain.movement.model.enums.MovementType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovementRequestDTO
{
    @NotNull( message = "argument 'productId' must not be null." )
    private Long productId;

    @NotNull( message = "argument 'movementType' must not be null." )
    private MovementType movementType;

    @NotNull( message = "argument 'quantity' must not be null." )
    @Min( value = 0, message = "argument 'quantity' must be greater than or equal to 0." )
    private int quantity;
}