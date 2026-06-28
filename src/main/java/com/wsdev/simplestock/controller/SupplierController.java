package com.wsdev.simplestock.controller;

import com.wsdev.simplestock.dto.SupplierDTO;
import com.wsdev.simplestock.service.SupplierService;
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
    public List<SupplierDTO> getSuppliers() throws Exception
    {
        return supplierService.getSuppliers();
    }

    @GetMapping( "/{id}" )
    @ResponseStatus( HttpStatus.OK )
    public SupplierDTO getSupplierById( @PathVariable Long id ) throws Exception
    {
        return supplierService.getSupplierById( id );
    }

    @GetMapping( "/{name}" )
    @ResponseStatus( HttpStatus.OK )
    public SupplierDTO getSupplierByName( @PathVariable String name ) throws Exception
    {
        return  supplierService.getSupplierByName( name );
    }

    @PostMapping( "/add" )
    @ResponseStatus( HttpStatus.CREATED )
    public void addSupplier( @RequestBody SupplierDTO supplierDTO ) throws Exception
    {
        supplierService.addSupplier( supplierDTO );
    }

    @PutMapping( "/update/{id}" )
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void updateSupplier( @PathVariable Long id, @RequestBody SupplierDTO supplierDTO ) throws Exception
    {
        supplierService.updateSupplier( id, supplierDTO );
    }

    @DeleteMapping( "/delete/{id}" )
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void deleteSupplier( @PathVariable Long id ) throws Exception
    {
        supplierService.deleteSupplier( id );
    }
}
