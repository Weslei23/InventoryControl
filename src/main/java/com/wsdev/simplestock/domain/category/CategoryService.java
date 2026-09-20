package com.wsdev.simplestock.domain.category;

import com.wsdev.simplestock.common.exceptions.RecordNotFoundException;
import com.wsdev.simplestock.domain.category.dto.request.CategoryRequestDTO;
import com.wsdev.simplestock.domain.category.dto.response.CategoryResponseDTO;
import com.wsdev.simplestock.domain.category.mapper.CategoryMapper;
import com.wsdev.simplestock.domain.category.model.Category;
import com.wsdev.simplestock.common.utilities.Validator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService
{
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Get all categories
     * @return
     */
    public List<CategoryResponseDTO> getCategories()
    {
        List<CategoryResponseDTO> categoryDTOS = new ArrayList<>();

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
    public CategoryResponseDTO getCategoryByName( String name ) throws Exception
    {
        Validator.requiredNonNull( name, new IllegalArgumentException( "Argument 'Name' must not be null." ) );
        return CategoryMapper.entityToDto( categoryRepository.getCategoryByName( name ) );
    }

    /**
     * Add category
     * @param categoryRequestDTO
     */
    public void addCategory( @Valid CategoryRequestDTO categoryRequestDTO ) throws Exception
    {
        if( categoryRepository.getCategoryByName( categoryRequestDTO.getCategoryName() ) != null )
        {
            throw new IllegalArgumentException( "Category already exists with name " + categoryRequestDTO.getCategoryName() );
        }

        categoryRepository.save( CategoryMapper.dtoToEntity( categoryRequestDTO ) );
    }

    /**
     * Update category
     * @param id
     * @param categoryRequestDTO
     */
    public void updateCategory( Long id, @Valid CategoryRequestDTO categoryRequestDTO ) throws Exception
    {
        Category category = categoryRepository.findById( id ).orElseThrow( () -> new RecordNotFoundException( id ) );

        category.setName( categoryRequestDTO.getCategoryName() );

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