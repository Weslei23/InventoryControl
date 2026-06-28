package com.wsdev.simplestock.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table( name = "tb_products" )
public class Product
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;
    private String description;
    private String image;

    @OneToMany( mappedBy = "product", cascade = CascadeType.ALL )
    private List<Movement> movements;

    @ManyToOne
    @JoinColumn( name = "category_id", nullable = false )
    private Category category;

    private BigDecimal price;
    private int quantity;
    private int minimumQuantity;
    private int maximumQuantity;

    @ManyToOne
    @JoinColumn( name = "supplier_id" )
    private Supplier supplier;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Product() {}

    public Category getCategory()
    {
        return category;
    }

    public void setCategory( Category category )
    {
        this.category = category;
    }

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

    public List<Movement> getMovements()
    {
        return movements;
    }

    public void setMovements( List<Movement> movements )
    {
        this.movements = movements;
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

    public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }

    public Supplier getSupplier()
    {
        return supplier;
    }

    public void setSupplier( Supplier supplier )
    {
        this.supplier = supplier;
    }

    public void setCreatedAt( LocalDateTime createdAt )
    {
        this.createdAt = createdAt;
    }
}
