package com.wsdev.simplestock.domain.product.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDTO
{
    @NotBlank( message = "argument 'name' must not be null or empty." )
    private String name;

    private String description;

    private String image;

    @NotNull( message = "argument 'price' must not be null." )
    @DecimalMin( value = "0.01", message = "argument 'price' must be greater than 0." )
    private BigDecimal price;

    @NotNull( message = "argument 'quantity' must not be null." )
    @Min( value = 0, message = "argument 'quantity' must be greater than or equal to 0." )
    private Integer quantity;

    @NotNull( message = "argument 'minimumQuantity' must not be null." )
    @Min( value = 0, message = "argument 'minimumQuantity' must be greater than or equal to 0." )
    private Integer minimumQuantity;

    @NotNull( message = "argument 'maximumQuantity' must not be null." )
    @Min( value = 1, message = "argument 'maximumQuantity' must be greater than 0." )
    private Integer maximumQuantity;

    @NotNull( message = "argument 'categoryId' must not be null." )
    @Positive( message = "argument 'categoryId' must be greater than 0." )
    private Long categoryId;

    @NotNull( message = "argument 'supplierId' must not be null." )
    @Positive( message = "argument 'supplierId' must be greater than 0." )
    private Long supplierId;
}