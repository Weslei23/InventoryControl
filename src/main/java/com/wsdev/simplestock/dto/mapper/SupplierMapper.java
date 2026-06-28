package com.wsdev.simplestock.dto.mapper;

import com.wsdev.simplestock.dto.SupplierDTO;
import com.wsdev.simplestock.entity.Supplier;

public class SupplierMapper
{
    /**
     * Entity to dto
     *
     * @param supplier
     * @return
     */
    public static SupplierDTO entityToDto( Supplier supplier )
    {
        SupplierDTO supplierDTO = new SupplierDTO();

        supplierDTO.setId( supplier.getId() );
        supplierDTO.setName( supplier.getName() );
        supplierDTO.setAddress( supplier.getAddress() );
        supplierDTO.setEmail( supplier.getEmail() );
        supplierDTO.setContact( supplier.getContact() );
        supplierDTO.setProducts( supplier.getProducts().stream().map( ProductMapper::entityToDto ).toList() );

        return supplierDTO;
    }

    /**
     * Dto to entity
     *
     * @param supplierDTO
     * @return
     */
    public static Supplier dtoToEntity( SupplierDTO supplierDTO )
    {
        Supplier supplier = new Supplier();

        supplier.setId( supplierDTO.getId() );
        supplier.setName( supplierDTO.getName() );
        supplier.setAddress( supplierDTO.getAddress() );
        supplier.setEmail( supplierDTO.getEmail() );
        supplier.setContact( supplierDTO.getContact() );
        supplier.setProducts( supplierDTO.getProducts().stream().map( ProductMapper::dtoToEntity ).toList() );

        return supplier;
    }
}
