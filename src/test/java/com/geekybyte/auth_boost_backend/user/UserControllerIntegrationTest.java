package com.geekybyte.auth_boost_backend.user;

import com.geekybyte.auth_boost_backend.BaseTest;
import com.geekybyte.auth_boost_backend.user.dto.CreateUserDto;
import com.geekybyte.auth_boost_backend.user.dto.UpdateUserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class UserControllerIntegrationTest extends BaseTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnAllUsers() {
        User[] users = restTemplate.getForObject("/users", User[].class);
        assertThat(users.length).isEqualTo(0);
    }

    @Test
    void shouldReturnUserById() {
        User user = restTemplate.getForObject("/users/1", User.class);
        assertThat(user.getId()).isEqualTo(1);
    }

    @Test
    @Rollback
    void shouldReturnUserAfterCreate() {
        CreateUserDto dto = new CreateUserDto(
                "user_1",
                "user1@example.com",
                "John",
                null,
                "Smith",
                LocalDate.parse("2000-04-10")
        );

        ResponseEntity<User> response = restTemplate.exchange("/users", HttpMethod.POST, new HttpEntity<>(dto), User.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getId()).isEqualTo(1);
        assertThat(Objects.requireNonNull(response.getBody()).getUsername()).isEqualTo("user_1");
        assertThat(Objects.requireNonNull(response.getBody()).getEmail()).isEqualTo("user1@example.com");
        assertThat(Objects.requireNonNull(response.getBody()).getFirstName()).isEqualTo("John");
        assertThat(Objects.requireNonNull(response.getBody()).getMiddleName()).isEqualTo(null);
        assertThat(Objects.requireNonNull(response.getBody()).getLastName()).isEqualTo("Smith");
        assertThat(Objects.requireNonNull(response.getBody()).getBirthDate()).isEqualTo("2000-04-10");
    }

    @Test
    @Rollback
    void shouldDeleteUserById() {
        int userId = 1;
        ResponseEntity<Object> response = restTemplate.exchange("/users/" + userId, HttpMethod.DELETE, null, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();
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

        ResponseEntity<User> response = restTemplate.exchange("/users/" + userId, HttpMethod.PATCH, new HttpEntity<>(dto), User.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getUsername()).isEqualTo("user_xyz");
    }
}
