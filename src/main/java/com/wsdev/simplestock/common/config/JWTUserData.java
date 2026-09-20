package com.wsdev.simplestock.common.config;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JWTUserData
{
    private Long userId;
    private String email;
}
