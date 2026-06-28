package com.wsdev.simplestock.dto.mapper;

import com.wsdev.simplestock.dto.ProductDTO;
import com.wsdev.simplestock.entity.Product;

public class ProductMapper
{
    /**
     * Entity to dto
     *
     * @param product
     * @return
     */
    public static ProductDTO entityToDto( Product product )
    {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId( product.getId() );
        productDTO.setName( product.getName() );
        productDTO.setCategoryDTO( CategoryMapper.entityToDto( product.getCategory() ) );
        productDTO.setImage( product.getImage() );
        productDTO.setQuantity( product.getQuantity() );
        productDTO.setMaximumQuantity( product.getMaximumQuantity() );
        productDTO.setMinimumQuantity( product.getMinimumQuantity() );
        productDTO.setDescription( product.getDescription() );
        productDTO.setPrice( product.getPrice() );
        productDTO.setSupplierDTO( SupplierMapper.entityToDto( product.getSupplier() ) );
        productDTO.setMovementDTOS( product.getMovements().stream().map( MovementMapper::entityToDto ).toList() );

        return productDTO;
    }

    /**
     * Dto to entity
     *
     * @param productDTO
     * @return
     */
    public static Product dtoToEntity( ProductDTO productDTO )
    {
        Product product = new Product();

        product.setId( productDTO.getId() );
        product.setName( productDTO.getName() );
        product.setCategory( CategoryMapper.dtoToEntity( productDTO.getCategoryDTO() ) );
        product.setImage( productDTO.getImage() );
        product.setQuantity( productDTO.getQuantity() );
        product.setMaximumQuantity( productDTO.getMaximumQuantity() );
        product.setDescription( productDTO.getDescription() );
        product.setMinimumQuantity( productDTO.getMinimumQuantity() );
        product.setPrice( productDTO.getPrice() );
        product.setSupplier( SupplierMapper.dtoToEntity( productDTO.getSupplierDTO() ) );
        product.setMovements( productDTO.getMovementDTOS().stream().map( MovementMapper::dtoToEntity ).toList() );

        return product;
    }
}
