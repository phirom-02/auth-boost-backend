package com.geekybyte.auth_boost_backend.tenant.service;

import com.geekybyte.auth_boost_backend.tenant.Tenant;
import com.geekybyte.auth_boost_backend.tenant.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindTenantByIdServiceImpl implements FindTenantByIdService {

    private final TenantRepository tenantRepository;

    public FindTenantByIdServiceImpl(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public Optional<Tenant> findTenantById(int id) {
        return tenantRepository.findById(id);
    }
}
