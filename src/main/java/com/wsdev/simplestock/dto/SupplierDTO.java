package com.wsdev.simplestock.dto;

import java.util.ArrayList;
import java.util.List;

public class SupplierDTO
{
    private Long id;
    private String name;
    private String contact;
    private String email;
    private String address;
    private List<ProductDTO> products = new ArrayList<>();

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

    public String getContact()
    {
        return contact;
    }

    public void setContact( String contact )
    {
        this.contact = contact;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress( String address )
    {
        this.address = address;
    }

    public List<ProductDTO> getProducts()
    {
        return products;
    }

    public void setProducts( List<ProductDTO> products )
    {
        this.products = products;
    }
}
