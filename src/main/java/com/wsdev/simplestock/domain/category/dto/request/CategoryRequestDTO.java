package com.wsdev.simplestock.domain.category.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDTO
{
    @NotBlank( message = "argument 'categoryName' must not be null or empty." )
    private String categoryName;
}
