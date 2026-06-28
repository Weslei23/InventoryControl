package com.wsdev.simplestock.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductDTO
{
    private Long id;
    private String name;
    private String description;
    private String image;
    private BigDecimal price;
    private int quantity;
    private int minimumQuantity;
    private int maximumQuantity;
    private CategoryDTO categoryDTO;
    private SupplierDTO supplierDTO;
    private List<MovementDTO> movementDTOS = new ArrayList<>();

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage( String image )
    {
        this.image = image;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice( BigDecimal price )
    {
        this.price = price;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getMinimumQuantity()
    {
        return minimumQuantity;
    }

    public void setMinimumQuantity( int minimumQuantity )
    {
        this.minimumQuantity = minimumQuantity;
    }

    public int getMaximumQuantity()
    {
        return maximumQuantity;
    }

    public void setMaximumQuantity( int maximumQuantity )
    {
        this.maximumQuantity = maximumQuantity;
    }

    public CategoryDTO getCategoryDTO()
    {
        return categoryDTO;
    }

    public void setCategoryDTO( CategoryDTO categoryDTO )
    {
        this.categoryDTO = categoryDTO;
    }

    public SupplierDTO getSupplierDTO()
    {
        return supplierDTO;
    }

    public void setSupplierDTO( SupplierDTO supplierDTO )
    {
        this.supplierDTO = supplierDTO;
    }

    public List<MovementDTO> getMovementDTOS()
    {
        return movementDTOS;
    }

    public void setMovementDTOS( List<MovementDTO> movementDTOS )
    {
        this.movementDTOS = movementDTOS;
    }
}
