package com.geekybyte.auth_boost_backend.user.service;

import com.geekybyte.auth_boost_backend.BaseTest;
import com.geekybyte.auth_boost_backend.user.User;
import com.geekybyte.auth_boost_backend.user.UserRepository;
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
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@Testcontainers
@Transactional
class FindAllUsersServiceImplIntegrationTest extends BaseTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    private UserRepository userRepository;

    private FindAllUsersServiceImpl findAllUsersService;

    @BeforeEach
    @Rollback
    void setUp() {
        findAllUsersService = new FindAllUsersServiceImpl(userRepository);

        User user1 = new User();
        user1.setUsername("user_1");
        user1.setEmail("user1@example.com");
        user1.setFirstName("John");
        user1.setLastName("Smith");
        user1.setBirthDate(LocalDate.of(2000, 4, 10));
        User user2 = new User();
        user2.setUsername("user_2");
        user2.setEmail("user2@example.com");
        user2.setFirstName("Luke");
        user2.setLastName("Cage");
        user2.setBirthDate(LocalDate.of(2000, 4, 10));

        List<User> users = List.of(user1, user2);

        userRepository.saveAll(users);
    }

    @Test
    void shouldReturnAllUsers() {
        List<User> users = findAllUsersService.findAllUsers();
        assertThat(2).isEqualTo(users.size());
    }
}