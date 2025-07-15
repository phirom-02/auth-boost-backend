package com.geekybyte.auth_boost_backend.tenant.mapper;

import com.geekybyte.auth_boost_backend.tenant.Tenant;
import com.geekybyte.auth_boost_backend.tenant.dto.UpdateTenantDto;

public class UpdateTenantDtoToTenant {

    public static Tenant map(Tenant tenant, UpdateTenantDto dto) {

        if(dto.username() != null && !dto.username().isEmpty()) {
            tenant.setUsername(dto.username());
        }

        if(dto.email() != null && !dto.email().isEmpty()) {
            tenant.setEmail(dto.email());
        }

        if(dto.firstName() != null && !dto.firstName().isEmpty()) {
            tenant.setFirstName(dto.firstName());
        }

        if(dto.middleName() != null && !dto.middleName().isEmpty()) {
            tenant.setMiddleName(dto.middleName());
        }

        if(dto.lastName() != null && !dto.lastName().isEmpty()) {
            tenant.setLastName(dto.lastName());
        }

        if(dto.birthDate() != null) {
            tenant.setBirthDate(dto.birthDate());
        }

        return tenant;
    }
}
