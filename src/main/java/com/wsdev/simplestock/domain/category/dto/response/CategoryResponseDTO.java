package com.wsdev.simplestock.domain.category.dto.response;

import com.wsdev.simplestock.domain.product.dto.response.ProductResponseDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryResponseDTO
{
    private Long id;
    private String name;
    private List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
}