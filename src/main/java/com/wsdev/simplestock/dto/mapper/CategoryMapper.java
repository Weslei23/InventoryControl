package com.wsdev.simplestock.dto.mapper;

import com.wsdev.simplestock.dto.CategoryDTO;
import com.wsdev.simplestock.entity.Category;

public class CategoryMapper
{
    /**
     * Entity to dto
     *
     * @param category
     * @return
     */
    public static CategoryDTO entityToDto( Category category )
    {
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( category.getId() );
        categoryDTO.setName( category.getName() );
        categoryDTO.setProductDTOS( category.getProducts().stream().map( ProductMapper::entityToDto ).toList() );

        return categoryDTO;
    }

    /**
     * Dto to entity
     *
     * @param categoryDTO
     * @return
     */
    public static Category dtoToEntity( CategoryDTO categoryDTO )
    {
        Category category = new Category();

        category.setId( categoryDTO.getId() );
        category.setName( categoryDTO.getName() );
        category.setProducts( categoryDTO.getProductDTOS().stream().map( ProductMapper::dtoToEntity ).toList() );

        return category;
    }
}
