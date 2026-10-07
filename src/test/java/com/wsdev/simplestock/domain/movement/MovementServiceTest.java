package com.wsdev.simplestock.domain.movement;

import com.wsdev.simplestock.common.exceptions.RecordNotFoundException;
import com.wsdev.simplestock.domain.movement.dto.request.MovementRequestDTO;
import com.wsdev.simplestock.domain.movement.dto.response.MovementResponseDTO;
import com.wsdev.simplestock.domain.movement.model.Movement;
import com.wsdev.simplestock.domain.movement.model.enums.MovementType;
import com.wsdev.simplestock.domain.product.ProductRepository;
import com.wsdev.simplestock.domain.product.model.Product;
import com.wsdev.simplestock.domain.user.UserRepository;
import com.wsdev.simplestock.domain.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith( MockitoExtension.class )
class MovementServiceTest
{
    @Mock
    private MovementRepository movementRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MovementService movementService;

    private Product product;
    private User user;

    @BeforeEach
    void setUp()
    {
        product = new Product();
        product.setId( 1L );
        product.setQuantity( 10 );

        user = new User();
        user.setId( 2L );
        user.setUsername( "john" );
    }

    private MovementRequestDTO request( MovementType type, int quantity )
    {
        MovementRequestDTO dto = new MovementRequestDTO();
        dto.setProductId( 1L );
        dto.setUserId( 2L );
        dto.setMovementType( type );
        dto.setQuantity( quantity );
        return dto;
    }

    private void stubLookups()
    {
        when( productRepository.findById( 1L ) ).thenReturn( Optional.of( product ) );
        when( userRepository.findById( 2L ) ).thenReturn( Optional.of( user ) );
    }

    @Test
    void getMovements_mapsPageIncludingUserName() throws Exception
    {
        Movement movement = new Movement();
        movement.setId( 5L );
        movement.setMovementType( MovementType.INBOUND );
        movement.setQuantity( 3 );
        movement.setUser( user );
        PageRequest pageable = PageRequest.of( 0, 10 );
        when( movementRepository.findAll( pageable ) ).thenReturn( new PageImpl<>( List.of( movement ) ) );

        Page<MovementResponseDTO> result = movementService.getMovements( pageable );

        MovementResponseDTO dto = result.getContent().get( 0 );
        assertEquals( 5L, dto.getId() );
        assertEquals( "john", dto.getUserName() );
        assertEquals( MovementType.INBOUND, dto.getMovementType() );
    }

    // ---- addMovement ----

    @Test
    void addMovement_inbound_increasesStockAndSavesMovement() throws Exception
    {
        stubLookups();

        movementService.addMovement( request( MovementType.INBOUND, 5 ) );

        assertEquals( 15, product.getQuantity() );
        verify( productRepository ).save( product );
        ArgumentCaptor<Movement> captor = ArgumentCaptor.forClass( Movement.class );
        verify( movementRepository ).save( captor.capture() );
        assertSame( product, captor.getValue().getProduct() );
        assertSame( user, captor.getValue().getUser() );
        assertEquals( 5, captor.getValue().getQuantity() );
    }

    @Test
    void addMovement_outbound_decreasesStock() throws Exception
    {
        stubLookups();

        movementService.addMovement( request( MovementType.OUTBOUND, 4 ) );

        assertEquals( 6, product.getQuantity() );
        verify( movementRepository ).save( any( Movement.class ) );
    }

    @Test
    void addMovement_outboundAboveStock_throwsAndDoesNotSave() throws Exception
    {
        stubLookups();

        IllegalArgumentException ex = assertThrows( IllegalArgumentException.class,
                () -> movementService.addMovement( request( MovementType.OUTBOUND, 11 ) ) );

        assertEquals( "Insufficient stock.", ex.getMessage() );
        assertEquals( 10, product.getQuantity() );
        verify( productRepository, never() ).save( any() );
        verify( movementRepository, never() ).save( any() );
    }

    @Test
    void addMovement_adjustment_keepsStockUnchanged() throws Exception
    {
        stubLookups();

        movementService.addMovement( request( MovementType.ADJUSTMENT, 3 ) );

        assertEquals( 10, product.getQuantity() );
        verify( movementRepository ).save( any( Movement.class ) );
    }

    @Test
    void addMovement_productNotFound_throws()
    {
        when( productRepository.findById( 1L ) ).thenReturn( Optional.empty() );

        assertThrows( RecordNotFoundException.class, () -> movementService.addMovement( request( MovementType.INBOUND, 1 ) ) );
    }

    @Test
    void addMovement_userNotFound_throws()
    {
        when( productRepository.findById( 1L ) ).thenReturn( Optional.of( product ) );
        when( userRepository.findById( 2L ) ).thenReturn( Optional.empty() );

        assertThrows( RecordNotFoundException.class, () -> movementService.addMovement( request( MovementType.INBOUND, 1 ) ) );
        verify( movementRepository, never() ).save( any() );
    }

    // ---- entryProduct ----

    @Test
    void entryProduct_inbound_increasesStock() throws Exception
    {
        stubLookups();

        movementService.entryProduct( request( MovementType.INBOUND, 7 ) );

        assertEquals( 17, product.getQuantity() );
        verify( movementRepository ).save( any( Movement.class ) );
    }

    @Test
    void entryProduct_nonInbound_doesNothing() throws Exception
    {
        movementService.entryProduct( request( MovementType.OUTBOUND, 7 ) );

        verifyNoInteractions( productRepository, userRepository, movementRepository );
    }

    // ---- exitProduct ----

    @Test
    void exitProduct_outbound_decreasesStock() throws Exception
    {
        stubLookups();

        movementService.exitProduct( request( MovementType.OUTBOUND, 10 ) );

        assertEquals( 0, product.getQuantity() );
        verify( productRepository ).save( product );
        verify( movementRepository ).save( any( Movement.class ) );
    }

    @Test
    void exitProduct_wrongType_throws()
    {
        assertThrows( IllegalArgumentException.class, () -> movementService.exitProduct( request( MovementType.INBOUND, 1 ) ) );
    }

    @Test
    void exitProduct_zeroQuantity_throws()
    {
        assertThrows( IllegalArgumentException.class, () -> movementService.exitProduct( request( MovementType.OUTBOUND, 0 ) ) );
    }

    @Test
    void exitProduct_insufficientStock_throws() throws Exception
    {
        stubLookups();

        IllegalArgumentException ex = assertThrows( IllegalArgumentException.class,
                () -> movementService.exitProduct( request( MovementType.OUTBOUND, 11 ) ) );

        assertEquals( "Insufficient stock.", ex.getMessage() );
        verify( movementRepository, never() ).save( any() );
    }

    // ---- update / delete ----

    @Test
    void updateMovement_updatesFields()
    {
        Movement existing = new Movement();
        existing.setId( 5L );
        when( movementRepository.findById( 5L ) ).thenReturn( Optional.of( existing ) );
        stubLookups();

        movementService.updateMovement( 5L, request( MovementType.ADJUSTMENT, 9 ) );

        assertSame( product, existing.getProduct() );
        assertSame( user, existing.getUser() );
        assertEquals( MovementType.ADJUSTMENT, existing.getMovementType() );
        assertEquals( 9, existing.getQuantity() );
        verify( movementRepository ).save( existing );
    }

    @Test
    void updateMovement_notFound_throws()
    {
        when( movementRepository.findById( 5L ) ).thenReturn( Optional.empty() );

        assertThrows( RecordNotFoundException.class, () -> movementService.updateMovement( 5L, request( MovementType.INBOUND, 1 ) ) );
    }

    @Test
    void deleteMovement_deletesExisting() throws Exception
    {
        Movement existing = new Movement();
        when( movementRepository.findById( 5L ) ).thenReturn( Optional.of( existing ) );

        movementService.deleteMovement( 5L );

        verify( movementRepository ).delete( existing );
    }

    @Test
    void deleteMovement_notFound_throws()
    {
        when( movementRepository.findById( 5L ) ).thenReturn( Optional.empty() );

        assertThrows( RecordNotFoundException.class, () -> movementService.deleteMovement( 5L ) );
    }
}
