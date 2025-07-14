package com.geekybyte.auth_boost_backend.tenant.service;

import com.geekybyte.auth_boost_backend.tenant.Tenant;

import java.util.Optional;

public interface FindTenantByIdService {

    Optional<Tenant> findTenantById(int id);
}
