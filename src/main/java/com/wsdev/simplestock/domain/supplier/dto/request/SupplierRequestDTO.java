package com.wsdev.simplestock.domain.supplier.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SupplierRequestDTO
{
    @NotBlank( message = "argument 'name' must not be null or empty." )
    private String name;

    @NotBlank( message = "argument 'contact' must not be null or empty." )
    private String contact;

    @NotBlank( message = "argument 'email' must not be null or empty." )
    private String email;

    @NotBlank( message = "argument 'address' must not be null or empty." )
    private String address;
}