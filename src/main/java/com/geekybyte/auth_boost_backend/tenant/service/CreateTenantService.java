package com.geekybyte.auth_boost_backend.tenant.service;

import com.geekybyte.auth_boost_backend.tenant.Tenant;
import com.geekybyte.auth_boost_backend.tenant.dto.CreateTenantDto;

public interface CreateTenantService {

    Tenant createTenant(CreateTenantDto dto);
}
