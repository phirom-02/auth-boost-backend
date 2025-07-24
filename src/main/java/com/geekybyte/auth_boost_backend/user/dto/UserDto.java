package com.geekybyte.auth_boost_backend.user.dto;

import java.time.LocalDate;

public record UserDto(String username, String email, String firstName, String lastName, LocalDate birthDate) {
}
