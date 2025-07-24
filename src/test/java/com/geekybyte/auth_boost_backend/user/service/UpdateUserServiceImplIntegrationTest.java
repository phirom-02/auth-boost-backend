package com.geekybyte.auth_boost_backend.user.service;

import com.geekybyte.auth_boost_backend.BaseTest;
import com.geekybyte.auth_boost_backend.user.User;
import com.geekybyte.auth_boost_backend.user.UserRepository;
import com.geekybyte.auth_boost_backend.user.dto.UpdateUserDto;
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
public class UpdateUserServiceImplIntegrationTest extends BaseTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FindUserByIdServiceImpl findUserByIdService;

    private UpdateUserServiceImpl updateUserService;

    @BeforeEach
    void setUp() {
        updateUserService = new UpdateUserServiceImpl(userRepository, findUserByIdService);

        User user = new User();
        user.setUsername("user_1");
        user.setEmail("user1@example.com");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setBirthDate(LocalDate.of(2000, 4, 10));

        userRepository.save(user);
    }

    @Test
    @Rollback
    void shouldReturnUserAfterUpdate() {
        int userId = 1;
        UpdateUserDto dto = new UpdateUserDto(
                "user_xyz",
                null,
                null,
                null,
                null,
                null
        );

        User updatedUser = updateUserService.updateUser(userId, dto);

        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getUsername()).isEqualTo("user_xyz");
    }
}
