package com.geekybyte.auth_boost_backend.tenant.service;

import com.geekybyte.auth_boost_backend.tenant.Tenant;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FindAllTenantsService {

    List<Tenant> findAllTenants();
}
