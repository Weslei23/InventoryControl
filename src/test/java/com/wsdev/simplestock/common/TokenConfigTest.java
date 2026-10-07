package com.wsdev.simplestock.common;

import com.wsdev.simplestock.common.config.JWTUserData;
import com.wsdev.simplestock.common.config.TokenConfig;
import com.wsdev.simplestock.domain.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TokenConfigTest
{
    private TokenConfig tokenConfig;

    @BeforeEach
    void setUp()
    {
        tokenConfig = new TokenConfig();
        ReflectionTestUtils.setField( tokenConfig, "secretKey", "unit-secret" );
    }

    private User user()
    {
        User user = new User();
        user.setId( 7L );
        user.setEmail( "a@b.com" );
        return user;
    }

    @Test
    void generatedToken_isValidAndCarriesUserData()
    {
        String token = tokenConfig.getSecretKey( user() );

        Optional<JWTUserData> data = tokenConfig.validateToken( token );

        assertTrue( data.isPresent() );
        assertEquals( 7L, data.get().getUserId() );
        assertEquals( "a@b.com", data.get().getEmail() );
    }

    @Test
    void validateToken_garbage_isEmpty()
    {
        assertTrue( tokenConfig.validateToken( "not-a-jwt" ).isEmpty() );
    }

    @Test
    void validateToken_signedWithOtherSecret_isEmpty()
    {
        String token = tokenConfig.getSecretKey( user() );
        TokenConfig other = new TokenConfig();
        ReflectionTestUtils.setField( other, "secretKey", "another-secret" );

        assertTrue( other.validateToken( token ).isEmpty() );
    }
}
