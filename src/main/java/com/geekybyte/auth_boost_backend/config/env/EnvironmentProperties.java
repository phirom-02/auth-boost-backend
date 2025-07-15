package com.geekybyte.auth_boost_backend.config.env;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "environment")
public class EnvironmentProperties {

    @NestedConfigurationProperty
    private JwtProperties jwt;
    @NestedConfigurationProperty
    private String mail;
}
