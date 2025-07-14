package com.geekybyte.auth_boost_backend.tenant.dto;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record UpdateTenantDto(
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
