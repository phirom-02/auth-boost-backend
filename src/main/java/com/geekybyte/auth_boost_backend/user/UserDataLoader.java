package com.geekybyte.auth_boost_backend.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekybyte.auth_boost_backend.user.dto.UsersDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
@Profile({"dev", "local"})
public class UserDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(UserDataLoader.class);
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    public UserDataLoader(ObjectMapper objectMapper, UserRepository userRepository) {
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            String tenantJson = "/data/tenants.json";
            log.info("Loading tenant data from {}", tenantJson);
            try (InputStream inputStream = TypeReference.class.getResourceAsStream(tenantJson)) {
                UsersDto result = objectMapper.readValue(inputStream, UsersDto.class);
                List<User> users = result.tenants().stream().map(dto -> {
                    User user = new User();
                    user.setUsername(dto.username());
                    user.setEmail(dto.email());
                    user.setFirstName(dto.firstName());
                    user.setLastName(dto.lastName());
                    user.setBirthDate(dto.birthDate());
                    return user;
                }).toList();
                userRepository.saveAll(users);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load tenants from " + tenantJson, e);
            }
        }
    }
}
