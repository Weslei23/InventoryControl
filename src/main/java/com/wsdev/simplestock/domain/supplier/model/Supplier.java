package com.wsdev.simplestock.domain.supplier.model;

import com.wsdev.simplestock.domain.product.model.Product;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
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
}