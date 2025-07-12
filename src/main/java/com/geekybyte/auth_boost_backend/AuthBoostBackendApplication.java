package com.geekybyte.auth_boost_backend;

import com.geekybyte.auth_boost_backend.config.env.EnvironmentProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(EnvironmentProperties.class)
public class AuthBoostBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthBoostBackendApplication.class, args);
	}

}
