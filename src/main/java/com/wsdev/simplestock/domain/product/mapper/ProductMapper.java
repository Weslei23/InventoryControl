package com.wsdev.simplestock.domain.product.mapper;

import com.wsdev.simplestock.domain.category.mapper.CategoryMapper;
import com.wsdev.simplestock.domain.product.dto.request.ProductRequestDTO;
import com.wsdev.simplestock.domain.product.dto.response.ProductResponseDTO;
import com.wsdev.simplestock.domain.product.model.Product;

public class ProductMapper
{
    /**
     * Entity to dto
     *
     * @param product
     * @return
     */
    public static ProductResponseDTO entityToDto( Product product )
    {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        productResponseDTO.setId( product.getId() );
        productResponseDTO.setName( product.getName() );
        productResponseDTO.setCategoryDTO( CategoryMapper.entityToDto( product.getCategory() ) );
        productResponseDTO.setImage( product.getImage() );
        productResponseDTO.setQuantity( product.getQuantity() );
        productResponseDTO.setMaximumQuantity( product.getMaximumQuantity() );
        productResponseDTO.setMinimumQuantity( product.getMinimumQuantity() );
        productResponseDTO.setDescription( product.getDescription() );
        productResponseDTO.setPrice( product.getPrice() );

        return productResponseDTO;
    }

    /**
     * Dto to entity
     *
     * @param productRequestDTO
     * @return
     */
    public static Product dtoToEntity( ProductRequestDTO productRequestDTO )
    {
        Product product = new Product();

        product.setName( productRequestDTO.getName() );
        product.setImage( productRequestDTO.getImage() );
        product.setQuantity( productRequestDTO.getQuantity() );
        product.setMaximumQuantity( productRequestDTO.getMaximumQuantity() );
        product.setDescription( productRequestDTO.getDescription() );
        product.setMinimumQuantity( productRequestDTO.getMinimumQuantity() );
        product.setPrice( productRequestDTO.getPrice() );

        return product;
    }
}
