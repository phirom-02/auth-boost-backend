package com.geekybyte.auth_boost_backend.config.env;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtProperties {

    private String jwtSecretKey;
    private String jwtRefreshTokenExpirationTime;
    private String jwtAccessTokenExpirationTime;
}
