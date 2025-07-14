package com.geekybyte.auth_boost_backend.tenant.mapper;

import com.geekybyte.auth_boost_backend.tenant.Tenant;
import com.geekybyte.auth_boost_backend.tenant.dto.CreateTenantDto;

public class CreateTenantDtoToTenant {

    public static Tenant map(CreateTenantDto dto) {
        Tenant tenant = new Tenant();
        tenant.setUsername(dto.username());
        tenant.setEmail(dto.email());
        tenant.setFirstName(dto.firstName());
        tenant.setLastName(dto.middleName());
        tenant.setLastName(dto.lastName());
        tenant.setBirthDate(dto.birthDate());
        return tenant;
    }
}
