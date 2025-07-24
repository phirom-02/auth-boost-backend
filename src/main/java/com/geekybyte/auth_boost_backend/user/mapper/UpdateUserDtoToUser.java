package com.geekybyte.auth_boost_backend.user.mapper;

import com.geekybyte.auth_boost_backend.user.User;
import com.geekybyte.auth_boost_backend.user.dto.UpdateUserDto;

public class UpdateUserDtoToUser {

    public static User map(User user, UpdateUserDto dto) {

        if(dto.username() != null && !dto.username().isEmpty()) {
            user.setUsername(dto.username());
        }

        if(dto.email() != null && !dto.email().isEmpty()) {
            user.setEmail(dto.email());
        }

        if(dto.firstName() != null && !dto.firstName().isEmpty()) {
            user.setFirstName(dto.firstName());
        }

        if(dto.middleName() != null && !dto.middleName().isEmpty()) {
            user.setMiddleName(dto.middleName());
        }

        if(dto.lastName() != null && !dto.lastName().isEmpty()) {
            user.setLastName(dto.lastName());
        }

        if(dto.birthDate() != null) {
            user.setBirthDate(dto.birthDate());
        }

        return user;
    }
}
