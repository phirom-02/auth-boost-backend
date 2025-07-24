package com.geekybyte.auth_boost_backend.user;

import com.geekybyte.auth_boost_backend.BaseTest;
import com.geekybyte.auth_boost_backend.user.dto.CreateUserDto;
import com.geekybyte.auth_boost_backend.user.dto.UpdateUserDto;
import com.geekybyte.auth_boost_backend.user.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FindAllUsersService findAllUsersService;
    @MockitoBean
    private FindUserByIdService findUserByIdService;
    @MockitoBean
    private DeleteUserByIdService deleteUserByIdService;
    @MockitoBean
    private CreateUserServiceImpl createUserService;
    @MockitoBean
    private UpdateUserServiceImpl updateUserService;

    List<User> users = new ArrayList<>();

    @BeforeEach
    void setUp() {
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("user_1");
        user1.setEmail("user1@example.com");
        user1.setFirstName("John");
        user1.setLastName("Smith");
        user1.setBirthDate(LocalDate.of(2000, 4, 10));
        User user2 = new User();
        user2.setId(2);
        user2.setUsername("user_2");
        user2.setEmail("user2@example.com");
        user2.setFirstName("Luke");
        user2.setLastName("Cage");
        user2.setBirthDate(LocalDate.of(2000, 4, 10));

        users = List.of(user1, user2);
    }

    @Test
    void shouldReturnAllUsers() throws Exception {
        String jsonResponse = """
                [
                    {
                        "id": 1,
                        "username": "user_1",
                        "email": "user1@example.com",
                        "firstName": "John",
                        "middleName": null,
                        "lastName": "Smith",
                        "birthDate": "2000-04-10",
                        "createdAt": null,
                        "updatedAt": null
                    },
                    {
                        "id": 2,
                        "username": "user_2",
                        "email": "user2@example.com",
                        "firstName": "Luke",
                        "middleName": null,
                        "lastName": "Cage",
                        "birthDate": "2000-04-10",
                        "createdAt": null,
                        "updatedAt": null
                    }
                ]
                """;

        when(findAllUsersService.findAllUsers()).thenReturn(users);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResponse));
    }

    @Test
    void shouldReturnUserById() throws Exception {
        int userId = 1;

        String jsonResponse = """
                {
                        "id": 1,
                        "username": "user_1",
                        "email": "user1@example.com",
                        "firstName": "John",
                        "middleName": null,
                        "lastName": "Smith",
                        "birthDate": "2000-04-10",
                        "createdAt": null,
                        "updatedAt": null
                }
                """;

        when(findUserByIdService.findUserById(userId)).thenReturn(Optional.of(users.getFirst()));

        mockMvc.perform(get("/users/" + userId))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResponse));
    }

    @Test
    void shouldDeleteUserById() throws Exception {
        int userId = 1;

        doNothing().when(deleteUserByIdService).deleteUserById(userId);

        mockMvc.perform(delete("/users/" + userId))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnUserAfterCreate() throws Exception {
        String requestBody = """
                {
                    "username": "user_1",
                    "email": "user1@example.com",
                    "firstName": "John",
                    "lastName": "Smith",
                    "birthDate": "2000-04-10"
                }
                """;

        String responseBody = """
                {
                    "username": "user_1",
                    "email": "user1@example.com",
                    "firstName": "John",
                    "middleName": null,
                    "lastName": "Smith",
                    "birthDate": "2000-04-10",
                    "createdAt": null,
                    "updatedAt": null
                }
                """;

        CreateUserDto dto = new CreateUserDto(
                "user_1",
                "user1@example.com",
                "John",
                null,
                "Smith",
                LocalDate.parse("2000-04-10")
        );

        User user = new User();
        user.setId(1);
        user.setUsername("user_1");
        user.setEmail("user1@example.com");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setBirthDate(LocalDate.of(2000, 4, 10));

        when(createUserService.createUser(dto)).thenReturn(user);

        mockMvc.perform(post("/users")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(content().json(responseBody));
    }

    @Test
    void shouldReturnUserAfterUpdate() throws Exception {
        int userId = 1;

        String requestBody = """
                {
                    "username": "user_xyz"
                }
                """;

        String responseBody = """
                {
                    "id": 1,
                    "username": "user_xyz",
                    "email": "user1@example.com",
                    "firstName": "John",
                    "middleName": null,
                    "lastName": "Smith",
                    "birthDate": "2000-04-10",
                    "createdAt": null,
                    "updatedAt": null
                }
                """;

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
        user.setUsername("user_xyz");
        user.setEmail("user1@example.com");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setBirthDate(LocalDate.of(2000, 4, 10));

        when(updateUserService.updateUser(userId, dto)).thenReturn(user);

        mockMvc.perform(patch("/users/" + userId)
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));

    }
}