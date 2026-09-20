package com.wsdev.simplestock.domain.movement;

import com.wsdev.simplestock.domain.movement.dto.request.MovementRequestDTO;
import com.wsdev.simplestock.domain.movement.dto.response.MovementResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping( "/api/v1/movement" )
public class MovementController
{
    @Autowired
    private MovementService movementService;

    @GetMapping()
    @ResponseStatus( HttpStatus.OK )
    public List<MovementResponseDTO> getCategories() throws Exception
    {
        return movementService.getMovements();
    }

    @PostMapping( "/inbound" )
    @ResponseStatus( HttpStatus.OK )
    public void entryProduct( @RequestBody MovementRequestDTO movementRequestDTO ) throws Exception
    {
        movementService.entryProduct( movementRequestDTO );
    }

    @PostMapping( "/outbound" )
    @ResponseStatus( HttpStatus.OK )
    public void exitProduct( @RequestBody MovementRequestDTO movementRequestDTO ) throws Exception
    {
        movementService.exitProduct( movementRequestDTO );
    }

    @PostMapping( "/add" )
    @ResponseStatus( HttpStatus.CREATED )
    public void addMovement( @RequestBody MovementRequestDTO movementRequestDTO ) throws Exception
    {
        movementService.addMovement( movementRequestDTO );
    }

    @PutMapping( "/update/{id}" )
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void updateMovement( @PathVariable Long id, @RequestBody MovementRequestDTO movementRequestDTO ) throws Exception
    {
        movementService.updateMovement( id, movementRequestDTO );
    }

    @DeleteMapping( "/delete/{id}" )
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void deleteMovement( @PathVariable Long id ) throws Exception
    {
        movementService.deleteMovement( id );
    }
}