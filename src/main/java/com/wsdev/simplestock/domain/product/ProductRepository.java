package com.wsdev.simplestock.domain.product;

import com.wsdev.simplestock.domain.category.model.Category;
import com.wsdev.simplestock.domain.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>
{
    Product getProductByCategory( Category category );
    List<Product> getProductsByCategory( Category category );
}
