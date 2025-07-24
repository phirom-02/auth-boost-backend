package com.geekybyte.auth_boost_backend.user.service;

import com.geekybyte.auth_boost_backend.BaseTest;
import com.geekybyte.auth_boost_backend.user.User;
import com.geekybyte.auth_boost_backend.user.UserRepository;
import com.geekybyte.auth_boost_backend.user.dto.CreateUserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateUserServiceImplTest extends BaseTest {

    @InjectMocks
    CreateUserServiceImpl createUserService;

    @Mock
    UserRepository userRepository;

    @Test
    void shouldReturnUserAfterCreate() {
        // Arrange
        CreateUserDto dto = new CreateUserDto(
                "user_1",
                "user1@example.com",
                "John",
                null,
                "Smith",
                LocalDate.parse("2000-04-28")
        );

        User user = new User();
        user.setId(1);
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setFirstName(dto.firstName());
        user.setMiddleName(dto.middleName());
        user.setLastName(dto.lastName());
        user.setBirthDate(dto.birthDate());

        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        User createUser = createUserService.createUser(dto);

        // Assert
        assertThat(createUser).isNotNull();
        assertThat(createUser.getId()).isEqualTo(1);
        assertThat(createUser.getUsername()).isEqualTo(dto.username());
        assertThat(createUser.getEmail()).isEqualTo(dto.email());
        assertThat(createUser.getFirstName()).isEqualTo(dto.firstName());
        assertThat(createUser.getMiddleName()).isEqualTo(dto.middleName());
        assertThat(createUser.getLastName()).isEqualTo(dto.lastName());
        assertThat(createUser.getBirthDate()).isEqualTo(dto.birthDate());

        // Verify
        verify(userRepository, times(1)).save(any(User.class));
    }

}