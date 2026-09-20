package com.wsdev.simplestock.domain.supplier;

import com.wsdev.simplestock.common.exceptions.RecordNotFoundException;
import com.wsdev.simplestock.domain.supplier.dto.request.SupplierRequestDTO;
import com.wsdev.simplestock.domain.supplier.dto.response.SupplierResponseDTO;
import com.wsdev.simplestock.domain.supplier.mapper.SupplierMapper;
import com.wsdev.simplestock.domain.supplier.model.Supplier;
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
    public SupplierResponseDTO getSupplierById( Long id )
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
    public List<SupplierResponseDTO> getSuppliers() throws Exception
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
    public SupplierResponseDTO getSupplierByName( String name ) throws Exception
    {
        return SupplierMapper.entityToDto( supplierRepository.findByName( name ) );
    }

    /**
     * Add new supplier
     *
     * @param supplierRequestDTO
     * @throws Exception
     */
    public void addSupplier( SupplierRequestDTO supplierRequestDTO ) throws Exception
    {
        if ( supplierRepository.findByName( supplierRequestDTO.getName() ) != null )
        {
            throw new IllegalArgumentException( "Supplier already exists with name " + supplierRequestDTO.getName() );
        }

        supplierRepository.save( SupplierMapper.dtoToEntity( supplierRequestDTO ) );
    }

    /**
     * Update supplier
     *
     * @param id
     * @param supplierRequestDTO
     * @throws Exception
     */
    public void updateSupplier( Long id, SupplierRequestDTO supplierRequestDTO ) throws Exception
    {
        Supplier supplier = supplierRepository.findById( id  ).orElseThrow( () -> new RecordNotFoundException( id ) );

        if ( supplierRepository.findByName( supplierRequestDTO.getName() ) != null )
        {
            throw new IllegalArgumentException( "Supplier already exists with name " + supplierRequestDTO.getName() );
        }

        supplier.setName( supplierRequestDTO.getName() );
        supplier.setAddress( supplierRequestDTO.getAddress() );
        supplier.setEmail( supplierRequestDTO.getEmail() );
        supplier.setContact( supplierRequestDTO.getContact() );

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