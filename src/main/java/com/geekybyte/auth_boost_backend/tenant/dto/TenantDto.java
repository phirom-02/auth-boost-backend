package com.geekybyte.auth_boost_backend.tenant.dto;

import java.time.LocalDate;

public record TenantDto(String username, String email, String firstName, String lastName, LocalDate birthDate) {
}
