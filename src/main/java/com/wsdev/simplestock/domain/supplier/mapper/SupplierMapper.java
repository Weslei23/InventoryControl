package com.wsdev.simplestock.domain.supplier.mapper;

import com.wsdev.simplestock.domain.product.mapper.ProductMapper;
import com.wsdev.simplestock.domain.supplier.dto.request.SupplierRequestDTO;
import com.wsdev.simplestock.domain.supplier.dto.response.SupplierResponseDTO;
import com.wsdev.simplestock.domain.supplier.model.Supplier;

public class SupplierMapper
{
    /**
     * Entity to dto
     *
     * @param supplier
     * @return
     */
    public static SupplierResponseDTO entityToDto( Supplier supplier )
    {
        SupplierResponseDTO supplierResponseDTO = new SupplierResponseDTO();

        supplierResponseDTO.setId( supplier.getId() );
        supplierResponseDTO.setName( supplier.getName() );
        supplierResponseDTO.setAddress( supplier.getAddress() );
        supplierResponseDTO.setEmail( supplier.getEmail() );
        supplierResponseDTO.setContact( supplier.getContact() );
        supplierResponseDTO.setProducts( supplier.getProducts().stream().map( ProductMapper::entityToDto ).toList() );

        return supplierResponseDTO;
    }

    /**
     * Dto to entity
     *
     * @param supplierRequestDTO
     * @return
     */
    public static Supplier dtoToEntity( SupplierRequestDTO supplierRequestDTO )
    {
        Supplier supplier = new Supplier();

        supplier.setName( supplierRequestDTO.getName() );
        supplier.setAddress( supplierRequestDTO.getAddress() );
        supplier.setEmail( supplierRequestDTO.getEmail() );
        supplier.setContact( supplierRequestDTO.getContact() );

        return supplier;
    }
}