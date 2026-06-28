package com.wsdev.simplestock.controller;

import com.wsdev.simplestock.dto.CategoryDTO;
import com.wsdev.simplestock.dto.ProductDTO;
import com.wsdev.simplestock.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping( "/api/v1/product" )
public class ProductController
{
    @Autowired
    private ProductService productService;

    @GetMapping()
    @ResponseStatus( HttpStatus.OK )
    public List<ProductDTO> getProducts()
    {
        return productService.getProducts();
    }

    @GetMapping( "/{id}" )
    @ResponseStatus( HttpStatus.OK )
    public ProductDTO getProductById( @PathVariable Long id )
    {
        return productService.getProductById( id );
    }

    @GetMapping( "/{category}" )
    @ResponseStatus( HttpStatus.OK )
    public ProductDTO getProductByCategory( @PathVariable CategoryDTO categoryDTO )
    {
        return  productService.getProductByCategory( categoryDTO );
    }

    @PostMapping( "/add" )
    @ResponseStatus( HttpStatus.CREATED )
    public void addProduct( @RequestBody ProductDTO productDTO ) throws Exception
    {
        productService.addProduct( productDTO );
    }

    @PutMapping( "/update/{id}" )
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void updateProduct( @PathVariable Long id, @RequestBody ProductDTO productDTO )
    {
        productService.updateProduct( id, productDTO );
    }

    @GetMapping( "/download" )
    public ResponseEntity<byte[]> downloadSheet() throws Exception
    {
        byte[] file = productService.downloadSheet();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType( MediaType.parseMediaType( "application/vnd.ms-excel" ) );

        headers.setContentDisposition( ContentDisposition.attachment().filename( "Relatorio_Produtos.xls" ).build() );

        return ResponseEntity.ok().headers( headers ).body( file );
    }

    @DeleteMapping( "/delete/{id}" )
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void deleteProduct( @PathVariable Long id )
    {
        productService.deleteProduct( id );
    }
}
