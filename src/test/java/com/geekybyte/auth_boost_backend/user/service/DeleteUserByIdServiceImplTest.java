package com.geekybyte.auth_boost_backend.user.service;

import com.geekybyte.auth_boost_backend.BaseTest;
import com.geekybyte.auth_boost_backend.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteUserByIdServiceImplTest extends BaseTest {

    @InjectMocks
    private DeleteUserByIdServiceImpl deleteUserByIdService;

    @Mock
    private UserRepository userRepository;

    @Test
    void shouldDeleteUserById() {
        // Arrange
        int userId = 1;

        doNothing().when(userRepository).deleteById(userId);

        // Act
        deleteUserByIdService.deleteUserById(userId);

        // Verify
        verify(userRepository, times(1)).deleteById(userId);
    }
}