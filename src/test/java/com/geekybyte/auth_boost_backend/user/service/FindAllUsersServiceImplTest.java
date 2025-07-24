package com.geekybyte.auth_boost_backend.user.service;

import com.geekybyte.auth_boost_backend.BaseTest;
import com.geekybyte.auth_boost_backend.user.User;
import com.geekybyte.auth_boost_backend.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindAllUsersServiceImplTest extends BaseTest {

    @InjectMocks
    FindAllUsersServiceImpl findAllUsersService;

    @Mock
    UserRepository userRepository;

    List<User> users = new ArrayList<>();

    @BeforeEach
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
        users.add(user1);
        users.add(user2);
    }

    @Test
    void shouldReturnAllUsers() {
        // Arrange
        when(userRepository.findAll()).thenReturn(this.users);

        // Act
        List<User> users = findAllUsersService.findAllUsers();

        // Assert
        assertThat(users).isNotNull();
        assertThat(users.size()).isEqualTo(users.size());

        // Verify
        verify(userRepository, times(1)).findAll();
    }
}