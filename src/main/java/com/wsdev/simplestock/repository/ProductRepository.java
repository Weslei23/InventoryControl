package com.wsdev.simplestock.repository;

import com.wsdev.simplestock.entity.Category;
import com.wsdev.simplestock.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>
{
    Product getProductByCategory( Category category );
    List<Product> getProductsByCategory( Category category );
}
