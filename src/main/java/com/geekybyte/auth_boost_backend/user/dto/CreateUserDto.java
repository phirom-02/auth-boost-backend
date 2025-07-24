package com.geekybyte.auth_boost_backend.user.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateUserDto(
        @NotNull
        @NotEmpty
        String username,
        @NotNull
        @NotEmpty
        String email,
        @NotNull
        @NotEmpty
        String firstName,
        @NotEmpty
        String middleName,
        @NotNull
        @NotEmpty
        String lastName,
        @NotNull
        @NotEmpty
        LocalDate birthDate
) {
}
