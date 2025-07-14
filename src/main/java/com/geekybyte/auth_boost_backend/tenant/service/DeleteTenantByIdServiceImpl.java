package com.geekybyte.auth_boost_backend.tenant.service;

import com.geekybyte.auth_boost_backend.tenant.TenantRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteTenantByIdServiceImpl implements DeleteTenantByIdService {

    private final TenantRepository tenantRepository;

    public DeleteTenantByIdServiceImpl(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public void deleteTenantById(int id) {
        tenantRepository.deleteById(id);
    }
}
