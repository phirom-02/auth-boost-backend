package com.geekybyte.auth_boost_backend.user.service;

import com.geekybyte.auth_boost_backend.BaseTest;
import com.geekybyte.auth_boost_backend.user.User;
import com.geekybyte.auth_boost_backend.user.UserRepository;
import com.geekybyte.auth_boost_backend.user.dto.UpdateUserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateUserServiceImplTest extends BaseTest {

    @InjectMocks
    private UpdateUserServiceImpl updateUserService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private FindUserByIdServiceImpl findUserByIdService;

    @Test
    void shouldReturnUserAfterUpdate() {
        // Arrange
        int userId = 1;
        UpdateUserDto dto = new UpdateUserDto(
                "user_xyz",
                null,
                null,
                null,
                null,
                null
        );

        User user = new User();
        user.setId(1);
        user.setUsername("user_1");

        when(findUserByIdService.findUserById(userId)).thenReturn(Optional.of(user));

        user.setUsername("user_xyz");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        User updatedUser = updateUserService.updateUser(userId, dto);

        // Assert
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getId()).isEqualTo(1);
        assertThat(updatedUser.getUsername()).isEqualTo("user_xyz");

        // Verify
        verify(userRepository, times(1)).save(any(User.class));
        verify(findUserByIdService, times(1)).findUserById(userId);
    }
}