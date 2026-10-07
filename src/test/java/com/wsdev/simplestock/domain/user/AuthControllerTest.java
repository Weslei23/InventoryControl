package com.wsdev.simplestock.domain.user;

import com.wsdev.simplestock.common.ControllerTestSupport;
import com.wsdev.simplestock.domain.user.dto.response.LoginResponseDTO;
import com.wsdev.simplestock.domain.user.dto.response.RegisterUserResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest( AuthController.class )
class AuthControllerTest extends ControllerTestSupport
{
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthService authService;

    @Test
    void login_isPublicAndReturnsToken() throws Exception
    {
        when( authService.login( any() ) ).thenReturn( new LoginResponseDTO( "jwt" ) );

        mockMvc.perform( post( "/api/v1/auth/login" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( "{\"email\":\"a@b.com\",\"password\":\"pw\"}" ) )
                .andExpect( status().isOk() )
                .andExpect( jsonPath( "$.token" ).value( "jwt" ) );
    }

    @Test
    void login_blankFields_returns400() throws Exception
    {
        mockMvc.perform( post( "/api/v1/auth/login" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( "{\"email\":\"\",\"password\":\"\"}" ) )
                .andExpect( status().isBadRequest() );

        verifyNoInteractions( authService );
    }

    @Test
    void register_isPublicAndReturns201() throws Exception
    {
        when( authService.register( any() ) ).thenReturn( new RegisterUserResponseDTO( "john", "john@mail.com" ) );

        mockMvc.perform( post( "/api/v1/auth/register" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( "{\"username\":\"john\",\"email\":\"john@mail.com\",\"password\":\"pw\"}" ) )
                .andExpect( status().isCreated() )
                .andExpect( jsonPath( "$.username" ).value( "john" ) )
                .andExpect( jsonPath( "$.email" ).value( "john@mail.com" ) );
    }

    @Test
    void register_missingFields_returns400() throws Exception
    {
        mockMvc.perform( post( "/api/v1/auth/register" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( "{\"username\":\"john\"}" ) )
                .andExpect( status().isBadRequest() );

        verifyNoInteractions( authService );
    }
}
