package com.geekybyte.auth_boost_backend.user.dto;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record UpdateUserDto(
        @NotEmpty
        String username,
        @NotEmpty
        String email,
        @NotEmpty
        String firstName,
        @NotEmpty
        String middleName,
        @NotEmpty
        String lastName,
        @NotEmpty
        LocalDate birthDate
) {
}
