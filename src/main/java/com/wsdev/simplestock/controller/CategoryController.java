package com.wsdev.simplestock.controller;

import com.wsdev.simplestock.dto.CategoryDTO;
import com.wsdev.simplestock.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping( "/api/v1/categories" )
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    @ResponseStatus( HttpStatus.OK )
    public List<CategoryDTO> getCategories()
    {
        return categoryService.getCategories();
    }

    @GetMapping( "/{name}" )
    @ResponseStatus( HttpStatus.OK )
    public CategoryDTO getCategoryByName( @PathVariable String name ) throws Exception
    {
        return categoryService.getCategoryByName( name );
    }

    @PostMapping( "/add" )
    @ResponseStatus( HttpStatus.CREATED )
    public void addCategory( @RequestBody CategoryDTO category ) throws Exception
    {
        categoryService.addCategory( category );
    }

    @PutMapping( "/update/{id}" )
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void updateCategory( @PathVariable Long id, @RequestBody CategoryDTO categoryDTO )
    {
        categoryService.updateCategory( id, categoryDTO );
    }

    @DeleteMapping( "/delete/{id}" )
    public void deleteCategory( @PathVariable Long id )
    {
        categoryService.deleteCategory( id );
    }
}
