package com.wsdev.simplestock.service;

import com.wsdev.simplestock.Exception.RecordNotFoundException;
import com.wsdev.simplestock.dto.SupplierDTO;
import com.wsdev.simplestock.dto.mapper.SupplierMapper;
import com.wsdev.simplestock.entity.Supplier;
import com.wsdev.simplestock.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService
{
    @Autowired
    private SupplierRepository supplierRepository;

    /**
     * Get supplier by id
     *
     * @param id
     * @return
     */
    public SupplierDTO getSupplierById( Long id )
    {
        Supplier supplier = supplierRepository.findById( id ).orElseThrow( () -> new RecordNotFoundException( id ) );

        return SupplierMapper.entityToDto( supplier );
    }

    /**
     * Get all supplier
     *
     * @return
     * @throws Exception
     */
    public List<SupplierDTO> getSuppliers() throws Exception
    {
        return supplierRepository.findAll().stream().map( SupplierMapper::entityToDto ).toList();
    }

    /**
     * Get supplier by name
     *
     * @param name
     * @return
     * @throws Exception
     */
    public SupplierDTO getSupplierByName( String name ) throws Exception
    {
        return SupplierMapper.entityToDto( supplierRepository.findByName( name ) );
    }

    /**
     * Add new supplier
     *
     * @param supplierDTO
     * @throws Exception
     */
    public void addSupplier( SupplierDTO supplierDTO ) throws Exception
    {
        if ( supplierRepository.findByName( supplierDTO.getName() ) != null )
        {
            throw new IllegalArgumentException( "Supplier already exists with name " + supplierDTO.getName() );
        }

        supplierRepository.save( SupplierMapper.dtoToEntity( supplierDTO ) );
    }

    /**
     * Update supplier
     *
     * @param id
     * @param supplierDTO
     * @throws Exception
     */
    public void updateSupplier( Long id, SupplierDTO supplierDTO ) throws Exception
    {
        Supplier supplier = supplierRepository.findById( id  ).orElseThrow( () -> new RecordNotFoundException( id ) );

        if ( supplierRepository.findByName( supplierDTO.getName() ) != null )
        {
            throw new IllegalArgumentException( "Supplier already exists with name " + supplierDTO.getName() );
        }

        supplier.setName( supplierDTO.getName() );
        supplier.setAddress( supplierDTO.getAddress() );
        supplier.setEmail( supplierDTO.getEmail() );
        supplier.setContact( supplierDTO.getContact() );

        supplierRepository.save( supplier );
    }

    /**
     * Delete supplier
     * @param id
     * @throws Exception
     */
    public void deleteSupplier( Long id ) throws Exception
    {
        Supplier supplier = supplierRepository.findById( id  ).orElseThrow( () -> new RecordNotFoundException( id ) );

        supplierRepository.delete( supplier );
    }
}
