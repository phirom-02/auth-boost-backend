package com.geekybyte.auth_boost_backend.user;

import com.geekybyte.auth_boost_backend.user.dto.CreateUserDto;
import com.geekybyte.auth_boost_backend.user.dto.UpdateUserDto;
import com.geekybyte.auth_boost_backend.user.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final FindAllUsersService findAllUsersService;
    private final FindUserByIdService findUserByIdService;
    private final DeleteUserByIdService deleteUserByIdService;
    private final CreateUserService createUserService;
    private final UpdateUserService updateUserService;

    public UserController(
            FindAllUsersService findAllUsersService,
            FindUserByIdService findUserByIdService,
            DeleteUserByIdService deleteUserByIdService,
            CreateUserService createUserService,
            UpdateUserService updateUserService
    ) {
        this.findAllUsersService = findAllUsersService;
        this.findUserByIdService = findUserByIdService;
        this.deleteUserByIdService = deleteUserByIdService;
        this.createUserService = createUserService;
        this.updateUserService = updateUserService;
    }

    @GetMapping("")
    public List<User> getAllUsers() {
        return findAllUsersService.findAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable int id) {
        return findUserByIdService.findUserById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable int id) {
        deleteUserByIdService.deleteUserById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody CreateUserDto dto) {
        return createUserService.createUser(dto);
    }

    @PatchMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody UpdateUserDto dto) {
        return updateUserService.updateUser(id, dto);
    }
}
