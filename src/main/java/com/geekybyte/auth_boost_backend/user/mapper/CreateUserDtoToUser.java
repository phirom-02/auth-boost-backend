package com.geekybyte.auth_boost_backend.user.mapper;

import com.geekybyte.auth_boost_backend.user.User;
import com.geekybyte.auth_boost_backend.user.dto.CreateUserDto;

public class CreateUserDtoToUser {

    public static User map(CreateUserDto dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setFirstName(dto.firstName());
        user.setLastName(dto.middleName());
        user.setLastName(dto.lastName());
        user.setBirthDate(dto.birthDate());
        return user;
    }
}
