package com.wsdev.simplestock.domain.movement.dto.response;

import com.wsdev.simplestock.domain.movement.model.enums.MovementType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovementResponseDTO
{
    private Long id;
    private MovementType movementType;
    private int quantity;
    private LocalDateTime createdAt;
}