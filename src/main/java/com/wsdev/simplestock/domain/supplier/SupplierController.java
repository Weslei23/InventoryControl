package com.wsdev.simplestock.domain.supplier;

import com.wsdev.simplestock.domain.supplier.dto.request.SupplierRequestDTO;
import com.wsdev.simplestock.domain.supplier.dto.response.SupplierResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping( "/api/v1/supplier" )
public class SupplierController
{
    @Autowired
    private SupplierService supplierService;

    @GetMapping()
    @ResponseStatus( HttpStatus.OK )
    public List<SupplierResponseDTO> getSuppliers() throws Exception
    {
        return supplierService.getSuppliers();
    }

    @GetMapping( "/{id}" )
    @ResponseStatus( HttpStatus.OK )
    public SupplierResponseDTO getSupplierById( @PathVariable Long id ) throws Exception
    {
        return supplierService.getSupplierById( id );
    }

    @GetMapping( "/{name}" )
    @ResponseStatus( HttpStatus.OK )
    public SupplierResponseDTO getSupplierByName( @PathVariable String name ) throws Exception
    {
        return  supplierService.getSupplierByName( name );
    }

    @PostMapping( "/add" )
    @ResponseStatus( HttpStatus.CREATED )
    public void addSupplier( @RequestBody SupplierRequestDTO supplierRequestDTO ) throws Exception
    {
        supplierService.addSupplier( supplierRequestDTO );
    }

    @PutMapping( "/update/{id}" )
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void updateSupplier( @PathVariable Long id, @RequestBody SupplierRequestDTO supplierRequestDTO ) throws Exception
    {
        supplierService.updateSupplier( id, supplierRequestDTO );
    }

    @DeleteMapping( "/delete/{id}" )
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void deleteSupplier( @PathVariable Long id ) throws Exception
    {
        supplierService.deleteSupplier( id );
    }
}
