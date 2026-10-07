package com.wsdev.simplestock.domain.supplier;

import com.wsdev.simplestock.common.exceptions.RecordNotFoundException;
import com.wsdev.simplestock.domain.supplier.dto.request.SupplierRequestDTO;
import com.wsdev.simplestock.domain.supplier.dto.response.SupplierResponseDTO;
import com.wsdev.simplestock.domain.supplier.model.Supplier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith( MockitoExtension.class )
class SupplierServiceTest
{
    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierService supplierService;

    private Supplier supplier( Long id, String name )
    {
        Supplier supplier = new Supplier();
        supplier.setId( id );
        supplier.setName( name );
        supplier.setContact( "555-0000" );
        supplier.setEmail( name.toLowerCase() + "@mail.com" );
        supplier.setAddress( "Street 1" );
        supplier.setProducts( new ArrayList<>() );
        return supplier;
    }

    private SupplierRequestDTO request( String name )
    {
        SupplierRequestDTO dto = new SupplierRequestDTO();
        dto.setName( name );
        dto.setContact( "555-1111" );
        dto.setEmail( "new@mail.com" );
        dto.setAddress( "Street 2" );
        return dto;
    }

    @Test
    void getSupplierById_returnsDto()
    {
        when( supplierRepository.findById( 1L ) ).thenReturn( Optional.of( supplier( 1L, "Acme" ) ) );

        SupplierResponseDTO result = supplierService.getSupplierById( 1L );

        assertEquals( 1L, result.getId() );
        assertEquals( "Acme", result.getName() );
        assertEquals( "acme@mail.com", result.getEmail() );
    }

    @Test
    void getSupplierById_notFound_throws()
    {
        when( supplierRepository.findById( 9L ) ).thenReturn( Optional.empty() );

        assertThrows( RecordNotFoundException.class, () -> supplierService.getSupplierById( 9L ) );
    }

    @Test
    void getSuppliers_mapsPage() throws Exception
    {
        PageRequest pageable = PageRequest.of( 0, 5 );
        when( supplierRepository.findAll( pageable ) ).thenReturn( new PageImpl<>( List.of( supplier( 1L, "Acme" ) ) ) );

        Page<SupplierResponseDTO> result = supplierService.getSuppliers( pageable );

        assertEquals( 1, result.getContent().size() );
        assertEquals( "Acme", result.getContent().get( 0 ).getName() );
    }

    @Test
    void getSupplierByName_returnsDto() throws Exception
    {
        when( supplierRepository.findByName( "Acme" ) ).thenReturn( supplier( 1L, "Acme" ) );

        assertEquals( "Acme", supplierService.getSupplierByName( "Acme" ).getName() );
    }

    @Test
    void addSupplier_savesNewSupplier() throws Exception
    {
        when( supplierRepository.findByName( "Acme" ) ).thenReturn( null );

        supplierService.addSupplier( request( "Acme" ) );

        ArgumentCaptor<Supplier> captor = ArgumentCaptor.forClass( Supplier.class );
        verify( supplierRepository ).save( captor.capture() );
        assertEquals( "Acme", captor.getValue().getName() );
        assertEquals( "555-1111", captor.getValue().getContact() );
    }

    @Test
    void addSupplier_duplicateName_throws()
    {
        when( supplierRepository.findByName( "Acme" ) ).thenReturn( supplier( 1L, "Acme" ) );

        assertThrows( IllegalArgumentException.class, () -> supplierService.addSupplier( request( "Acme" ) ) );
        verify( supplierRepository, never() ).save( any() );
    }

    @Test
    void updateSupplier_updatesFields() throws Exception
    {
        Supplier existing = supplier( 1L, "Old" );
        when( supplierRepository.findById( 1L ) ).thenReturn( Optional.of( existing ) );
        when( supplierRepository.findByName( "New" ) ).thenReturn( null );

        supplierService.updateSupplier( 1L, request( "New" ) );

        assertEquals( "New", existing.getName() );
        assertEquals( "555-1111", existing.getContact() );
        assertEquals( "new@mail.com", existing.getEmail() );
        assertEquals( "Street 2", existing.getAddress() );
        verify( supplierRepository ).save( existing );
    }

    @Test
    void updateSupplier_notFound_throws()
    {
        when( supplierRepository.findById( 9L ) ).thenReturn( Optional.empty() );

        assertThrows( RecordNotFoundException.class, () -> supplierService.updateSupplier( 9L, request( "New" ) ) );
    }

    @Test
    void updateSupplier_nameAlreadyUsed_throws()
    {
        when( supplierRepository.findById( 1L ) ).thenReturn( Optional.of( supplier( 1L, "Old" ) ) );
        when( supplierRepository.findByName( "Taken" ) ).thenReturn( supplier( 2L, "Taken" ) );

        assertThrows( IllegalArgumentException.class, () -> supplierService.updateSupplier( 1L, request( "Taken" ) ) );
        verify( supplierRepository, never() ).save( any() );
    }

    @Test
    void deleteSupplier_deletesExisting() throws Exception
    {
        Supplier existing = supplier( 1L, "Acme" );
        when( supplierRepository.findById( 1L ) ).thenReturn( Optional.of( existing ) );

        supplierService.deleteSupplier( 1L );

        verify( supplierRepository ).delete( existing );
    }

    @Test
    void deleteSupplier_notFound_throws()
    {
        when( supplierRepository.findById( 9L ) ).thenReturn( Optional.empty() );

        assertThrows( RecordNotFoundException.class, () -> supplierService.deleteSupplier( 9L ) );
    }
}
