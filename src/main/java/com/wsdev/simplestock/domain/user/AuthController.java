package com.wsdev.simplestock.domain.user;

import com.wsdev.simplestock.domain.user.dto.request.LoginRequestDTO;
import com.wsdev.simplestock.domain.user.dto.request.RegisterUserRequestDTO;
import com.wsdev.simplestock.domain.user.dto.response.LoginResponseDTO;
import com.wsdev.simplestock.domain.user.dto.response.RegisterUserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin( origins = "http://localhost:5173" )
@RequestMapping( "/api/v1/auth" )
public class AuthController
{
    @Autowired
    private AuthService authService;

    @PostMapping( "/login" )
    public ResponseEntity<LoginResponseDTO> login( @Valid @RequestBody LoginRequestDTO loginRequestDTO )
    {
        return ResponseEntity.ok( authService.login( loginRequestDTO) );
    }

    @PostMapping( "/register" )
    public ResponseEntity<RegisterUserResponseDTO> register( @Valid @RequestBody RegisterUserRequestDTO registerUserRequestDTO )
    {
        return ResponseEntity.status( HttpStatus.CREATED ).body( authService.register( registerUserRequestDTO) );
    }
}