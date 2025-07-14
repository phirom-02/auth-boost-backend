package com.geekybyte.auth_boost_backend.tenant.service;

import com.geekybyte.auth_boost_backend.tenant.Tenant;
import com.geekybyte.auth_boost_backend.tenant.TenantRepository;
import com.geekybyte.auth_boost_backend.tenant.dto.UpdateTenantDto;
import com.geekybyte.auth_boost_backend.tenant.mapper.UpdateTenantDtoToTenant;
import org.springframework.stereotype.Service;

@Service
public class UpdateTenantServiceImpl implements UpdateTenantService {

    private final TenantRepository tenantRepository;
    private final FindTenantByIdService findTenantByIdService;

    public UpdateTenantServiceImpl(
            TenantRepository tenantRepository,
            FindTenantByIdService findTenantByIdService
    ) {
        this.tenantRepository = tenantRepository;
        this.findTenantByIdService = findTenantByIdService;
    }

    @Override
    public Tenant updateTenant(int id, UpdateTenantDto dto) {
        Tenant tenant = findTenantByIdService.findTenantById(id).orElse(null);

        if (tenant == null) {
            return null;
        }

        Tenant updateTenant = UpdateTenantDtoToTenant.map(tenant, dto);
        return tenantRepository.save(updateTenant);
    }
}
