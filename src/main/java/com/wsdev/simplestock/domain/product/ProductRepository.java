package com.wsdev.simplestock.domain.product;

import com.wsdev.simplestock.domain.category.model.Category;
import com.wsdev.simplestock.domain.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>
{
    Product getProductByCategory( Category category );
    Page<Product> getProductsByCategory( Category category, Pageable pageable );
}
