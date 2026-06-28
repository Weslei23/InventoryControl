package com.wsdev.simplestock.dto;

import java.util.ArrayList;
import java.util.List;

public class CategoryDTO
{
    private Long id;
    private String name;
    private List<ProductDTO> productDTOS = new ArrayList<>();

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

    public List<ProductDTO> getProductDTOS()
    {
        return productDTOS;
    }

    public void setProductDTOS( List<ProductDTO> productDTOS )
    {
        this.productDTOS = productDTOS;
    }
}
