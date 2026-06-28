package com.wsdev.simplestock.repository;

import com.wsdev.simplestock.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>
{
    Category getCategoryByName( String name );
    //Category getCategoryByProduct( Product product );
}
