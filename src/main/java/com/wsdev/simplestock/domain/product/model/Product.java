package com.wsdev.simplestock.domain.product.model;

import com.wsdev.simplestock.domain.category.model.Category;
import com.wsdev.simplestock.domain.supplier.model.Supplier;
import com.wsdev.simplestock.domain.movement.model.Movement;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
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
}