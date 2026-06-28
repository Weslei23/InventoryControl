package com.wsdev.simplestock.service;

import com.wsdev.simplestock.Exception.RecordNotFoundException;
import com.wsdev.simplestock.dto.CategoryDTO;
import com.wsdev.simplestock.dto.ProductDTO;
import com.wsdev.simplestock.dto.mapper.CategoryMapper;
import com.wsdev.simplestock.entity.Category;
import com.wsdev.simplestock.entity.Product;
import com.wsdev.simplestock.repository.CategoryRepository;
import com.wsdev.simplestock.repository.ProductRepository;
import com.wsdev.simplestock.utilities.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService
{
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    /**
     * Get all categories
     * @return
     */
    public List<CategoryDTO> getCategories()
    {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        for( Category category : categoryRepository.findAll() )
        {
            categoryDTOS.add( CategoryMapper.entityToDto( category ) );
        }

        return categoryDTOS;
    }

    /**
     * Get category by name
     * @param name
     * @return
     */
    public CategoryDTO getCategoryByName( String name ) throws Exception
    {
        Validator.requiredNonNull( name, new IllegalArgumentException( "Argument 'Name' must not be null." ) );
        return CategoryMapper.entityToDto( categoryRepository.getCategoryByName( name ) );
    }

//    /**
//     *
//     * @param productDTO
//     * @return
//     */
//    public CategoryDTO getCategoryByProduct( ProductDTO productDTO )
//    {
//        Product product = productRepository.findById( productDTO.getId() ).orElseThrow( () -> new IllegalArgumentException( "Product not found" ) );
//
//        return CategoryMapper.entityToDto( categoryRepository.getCategoryByProduct( product ) );
//    }

    /**
     * Add category
     * @param categoryDTO
     */
    public void addCategory( CategoryDTO categoryDTO ) throws Exception
    {
        if( categoryRepository.getCategoryByName( categoryDTO.getName() ) != null )
        {
            throw new IllegalArgumentException( "Category already exists with name " + categoryDTO.getName() );
        }

        categoryRepository.save( CategoryMapper.dtoToEntity( categoryDTO ) );
    }

    /**
     * Update category
     * @param id
     * @param categoryDTO
     */
    public void updateCategory( Long id, CategoryDTO categoryDTO )
    {
        Category category = categoryRepository.findById( id ).orElseThrow( () -> new RecordNotFoundException( id ) );

        category.setName( categoryDTO.getName() );

        categoryRepository.save( category );
    }

    /**
     * Delete category
     * @param id
     */
    public void deleteCategory( Long id )
    {
        Category category = categoryRepository.findById( id ).orElseThrow( () -> new RecordNotFoundException( id ) );
        
        categoryRepository.delete( category );
    }
}
