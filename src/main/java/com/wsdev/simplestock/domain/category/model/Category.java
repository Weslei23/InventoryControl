package com.wsdev.simplestock.domain.category.model;

import com.wsdev.simplestock.domain.product.model.Product;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table( name = "tb_categories" )
public class Category
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;

    @OneToMany( mappedBy = "category", cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    private List<Product> products = new ArrayList<>();
}