package com.geekybyte.auth_boost_backend.tenant.service;

import com.geekybyte.auth_boost_backend.tenant.Tenant;
import com.geekybyte.auth_boost_backend.tenant.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllTenantsServiceImpl implements FindAllTenantsService {

    private final TenantRepository tenantRepository;

    public FindAllTenantsServiceImpl(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public List<Tenant> findAllTenants() {
        return tenantRepository.findAll();
    }
}
