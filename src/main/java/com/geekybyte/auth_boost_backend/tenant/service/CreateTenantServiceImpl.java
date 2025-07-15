package com.geekybyte.auth_boost_backend.tenant.service;

import com.geekybyte.auth_boost_backend.tenant.Tenant;
import com.geekybyte.auth_boost_backend.tenant.TenantRepository;
import com.geekybyte.auth_boost_backend.tenant.dto.CreateTenantDto;
import com.geekybyte.auth_boost_backend.tenant.mapper.CreateTenantDtoToTenant;
import org.springframework.stereotype.Service;

@Service
public class CreateTenantServiceImpl implements CreateTenantService {

    private final TenantRepository tenantRepository;

    public CreateTenantServiceImpl(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public Tenant createTenant(CreateTenantDto dto) {
        Tenant tenant = CreateTenantDtoToTenant.map(dto);
        return tenantRepository.save(tenant);
    }
}
