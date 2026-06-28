package com.wsdev.simplestock.entity;

import com.wsdev.simplestock.entity.enums.MovementType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table( name = "tb_movements" )
public class Movement
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @ManyToOne
    @JoinColumn( name = "product_id" )
    private Product product;
    private int  quantity;

    @Enumerated( EnumType.STRING )
    @Column( name = "movement_type" )
    private MovementType movementType;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Movement(){}

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct( Product product )
    {
        this.product = product;
    }

    public MovementType getMovementType()
    {
        return movementType;
    }

    public void setMovementType( MovementType movementType )
    {
        this.movementType = movementType;
    }

    public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt( LocalDateTime createdAt )
    {
        this.createdAt = createdAt;
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
