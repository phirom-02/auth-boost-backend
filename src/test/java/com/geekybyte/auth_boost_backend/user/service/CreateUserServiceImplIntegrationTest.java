package com.geekybyte.auth_boost_backend.user.service;

import com.geekybyte.auth_boost_backend.BaseTest;
import com.geekybyte.auth_boost_backend.user.User;
import com.geekybyte.auth_boost_backend.user.UserRepository;
import com.geekybyte.auth_boost_backend.user.dto.CreateUserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Testcontainers
@Transactional
public class CreateUserServiceImplIntegrationTest extends BaseTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    private UserRepository userRepository;

    private CreateUserServiceImpl createTenantService;

    @BeforeEach
    void setUp() {
        createTenantService = new CreateUserServiceImpl(userRepository);
    }

    @Test
    @Rollback
    void shouldReturnTenantAfterCreateUser() {
        CreateUserDto dto = new CreateUserDto(
                "user1",
                "user1@example.com",
                "John",
                null,
                "Smith",
                LocalDate.parse("2000-04-28")
        );

        User createdUser = createTenantService.createUser(dto);
        assertThat(createdUser).isNotNull();
        assertThat(createdUser.getUsername()).isEqualTo(dto.username());
        assertThat(createdUser.getEmail()).isEqualTo(dto.email());
        assertThat(createdUser.getFirstName()).isEqualTo(dto.firstName());
        assertThat(createdUser.getMiddleName()).isEqualTo(dto.middleName());
        assertThat(createdUser.getLastName()).isEqualTo(dto.lastName());
        assertThat(createdUser.getBirthDate()).isEqualTo(dto.birthDate());
    }
}
