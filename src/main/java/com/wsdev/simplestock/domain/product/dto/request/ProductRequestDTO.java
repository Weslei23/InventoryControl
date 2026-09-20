package com.wsdev.simplestock.domain.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDTO
{
    @NotBlank( message = "argument 'name' must not be null or empty." )
    private String name;
    private String description;
    private String image;
    private BigDecimal price;
    private int quantity;
    private int minimumQuantity;
    private int maximumQuantity;

    private Long categoryId;
    private Long supplierId;
}