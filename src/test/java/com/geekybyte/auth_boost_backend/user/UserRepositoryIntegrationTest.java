package com.geekybyte.auth_boost_backend.user;

import com.geekybyte.auth_boost_backend.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.annotation.Rollback;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryIntegrationTest extends BaseTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16:9");

    @Autowired
    private UserRepository userRepository;

    @Test
    void connectionEstablished() {
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }

    @BeforeEach
    @Rollback
    void setUp() {
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
    void shouldRetrieveTenantById() {
        int userId = 1;
        Optional<User> user = userRepository.findById(userId);
        assertThat(user).isPresent();
        assertThat(user.get().getId()).isEqualTo(userId);
    }
}
