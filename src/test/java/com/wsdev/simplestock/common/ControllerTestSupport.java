package com.wsdev.simplestock.common;

import com.wsdev.simplestock.common.config.SecurityConfig;
import com.wsdev.simplestock.common.config.SecurityFilter;
import com.wsdev.simplestock.common.config.TokenConfig;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@Import( { SecurityConfig.class, SecurityFilter.class, TokenConfig.class } )
@TestPropertySource( properties = "secretKey=test-secret" )
public abstract class ControllerTestSupport
{
    protected RequestPostProcessor auth()
    {
        return user( "tester@mail.com" );
    }
}
