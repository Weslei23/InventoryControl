package com.wsdev.simplestock.dto;

import com.wsdev.simplestock.entity.enums.MovementType;

public class MovementDTO
{
    private ProductDTO productDTO;
    private MovementType movementType;
    private int quantity;

    public ProductDTO getProductDTO()
    {
        return productDTO;
    }

    public void setProductDTO( ProductDTO productDTO )
    {
        this.productDTO = productDTO;
    }

    public MovementType getMovementType()
    {
        return movementType;
    }

    public void setMovementType( MovementType movementType )
    {
            this.movementType = movementType;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity( int quantity )
    {
        this.quantity = quantity;
    }
}
