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

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindUserByIdServiceImplTest extends BaseTest {

    @InjectMocks
    FindUserByIdServiceImpl findUserByIdService;

    @Mock
    UserRepository userRepository;

    User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1);
        user.setUsername("tenant_1");
        user.setEmail("tenant1@example.com");
        user.setFirstName("John");
        user.setLastName("Smith");
    }

    @Test
    void shouldReturnUserByValidId() {
        // Arrange
        int tenantId = 1;

        when(userRepository.findById(tenantId)).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = findUserByIdService.findUserById(tenantId);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.get().getId()).isEqualTo(tenantId);

        // Verify
        verify(userRepository, times(1)).findById(tenantId);
    }
}