package com.geekybyte.auth_boost_backend.user.service;

import com.geekybyte.auth_boost_backend.BaseTest;
import com.geekybyte.auth_boost_backend.user.User;
import com.geekybyte.auth_boost_backend.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Testcontainers
public class FindUserByIdServiceImplIntegrationTest extends BaseTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    private UserRepository userRepository;

    private FindUserByIdServiceImpl findUserByIdService;

    @BeforeEach
    void setup() {
        findUserByIdService = new FindUserByIdServiceImpl(userRepository);

        User user = new User();
        user.setUsername("user_1");
        user.setEmail("user1@example.com");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setBirthDate(LocalDate.of(2000, 4, 10));

        userRepository.save(user);
    }

    @Test
    void shouldReturnUserById() {
        int userId = 1;

        Optional<User> user = findUserByIdService.findUserById(userId);
        assertThat(user).isPresent();
        assertThat(userId).isEqualTo(user.get().getId());
    }
}
