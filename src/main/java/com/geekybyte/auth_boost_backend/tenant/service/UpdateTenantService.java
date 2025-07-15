package com.geekybyte.auth_boost_backend.tenant.service;

import com.geekybyte.auth_boost_backend.tenant.Tenant;
import com.geekybyte.auth_boost_backend.tenant.dto.UpdateTenantDto;

public interface UpdateTenantService {

    Tenant updateTenant(int id, UpdateTenantDto dto);
}
