package com.wsdev.simplestock.domain.product;

import com.wsdev.simplestock.domain.category.dto.request.CategoryRequestDTO;
import com.wsdev.simplestock.domain.category.dto.response.CategoryResponseDTO;
import com.wsdev.simplestock.domain.product.dto.request.ProductRequestDTO;
import com.wsdev.simplestock.domain.product.dto.response.ProductResponseDTO;
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
    public List<ProductResponseDTO> getProducts()
    {
        return productService.getProducts();
    }

    @GetMapping( "/{id}" )
    @ResponseStatus( HttpStatus.OK )
    public ProductResponseDTO getProductById( @PathVariable Long id )
    {
        return productService.getProductById( id );
    }

    @GetMapping( "/{category}" )
    @ResponseStatus( HttpStatus.OK )
    public ProductResponseDTO getProductByCategory( @PathVariable CategoryRequestDTO categoryRequestDTO )
    {
        return  productService.getProductByCategory( categoryRequestDTO );
    }

    @PostMapping( "/add" )
    @ResponseStatus( HttpStatus.CREATED )
    public void addProduct( @RequestBody ProductRequestDTO productRequestDTO ) throws Exception
    {
        productService.addProduct( productRequestDTO );
    }

    @PutMapping( "/update/{id}" )
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void updateProduct( @PathVariable Long id, @RequestBody ProductRequestDTO productRequestDTO )
    {
        productService.updateProduct( id, productRequestDTO );
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
