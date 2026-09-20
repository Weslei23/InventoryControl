package com.wsdev.simplestock.domain.category;

import com.wsdev.simplestock.domain.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>
{
    Category getCategoryByName( String name );
}
