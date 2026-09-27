package com.wsdev.simplestock.domain.product.dto.response;

import com.wsdev.simplestock.domain.category.dto.response.CategoryResponseDTO;
import com.wsdev.simplestock.domain.movement.dto.response.MovementResponseDTO;
import com.wsdev.simplestock.domain.product.model.enums.ProductState;
import com.wsdev.simplestock.domain.supplier.dto.response.SupplierResponseDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductResponseDTO
{
    private Long id;
    private String name;
    private String description;
    private String image;
    private ProductState state;
    private BigDecimal price;
    private int quantity;
    private int minimumQuantity;
    private int maximumQuantity;
    private CategoryResponseDTO categoryDTO;
    private SupplierResponseDTO supplierResponseDTO;
    private List<MovementResponseDTO> movementResponseDTOS = new ArrayList<>();
}
