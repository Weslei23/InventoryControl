package com.wsdev.simplestock.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table( name = "tb_suppliers" )
public class Supplier
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;
    private String contact;
    private String email;
    private String address;

    @OneToMany( mappedBy = "supplier", cascade = CascadeType.ALL )
    private List<Product> products;

    public Supplier(){}

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

    public void setAddress(String address)
    {
        this.address = address;
    }

    public List<Product> getProducts()
    {
        return products;
    }

    public void setProducts( List<Product> products )
    {
        this.products = products;
    }
}
