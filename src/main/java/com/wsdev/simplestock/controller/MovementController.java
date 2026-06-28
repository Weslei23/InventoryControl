package com.wsdev.simplestock.controller;

import com.wsdev.simplestock.dto.MovementDTO;
import com.wsdev.simplestock.service.MovementService;
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
    public List<MovementDTO> getCategories() throws Exception
    {
        return movementService.getMovements();
    }

    @PostMapping( "/inbound" )
    public void entryProduct( @RequestBody MovementDTO movementDTO ) throws Exception
    {
        movementService.entryProduct( movementDTO );
    }

    @PostMapping( "/outbound" )
    public void exitProduct( @RequestBody MovementDTO movementDTO ) throws Exception
    {
        movementService.exitProduct( movementDTO );
    }

    @PostMapping( "/add" )
    @ResponseStatus( HttpStatus.CREATED )
    public void addMovement( @RequestBody MovementDTO movementDTO ) throws Exception
    {
        movementService.addMovement( movementDTO );
    }

    @PutMapping( "/update/{id}" )
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void updateMovement( @PathVariable Long id, @RequestBody MovementDTO movementDTO ) throws Exception
    {
        movementService.updateMovement( id, movementDTO );
    }

    @DeleteMapping( "/delete/{id}" )
    public void deleteMovement( @PathVariable Long id ) throws Exception
    {
        movementService.deleteMovement( id );
    }
}
