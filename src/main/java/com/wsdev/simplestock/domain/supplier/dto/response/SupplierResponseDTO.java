package com.wsdev.simplestock.domain.supplier.dto.response;

import com.wsdev.simplestock.domain.product.dto.response.ProductResponseDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SupplierResponseDTO
{
    private Long id;
    private String name;
    private String contact;
    private String email;
    private String address;
    private List<ProductResponseDTO> products = new ArrayList<>();
}