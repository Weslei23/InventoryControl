package com.wsdev.simplestock.domain.movement.model;

import com.wsdev.simplestock.domain.product.model.Product;
import com.wsdev.simplestock.domain.movement.model.enums.MovementType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
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
}