package com.geekybyte.auth_boost_backend.tenant.service;

import com.geekybyte.auth_boost_backend.tenant.Tenant;
import com.geekybyte.auth_boost_backend.tenant.TenantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

@SpringBootTest
@Testcontainers
public class DeleteTenantByIdServiceImplIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    private TenantRepository tenantRepository;

    private DeleteTenantByIdServiceImpl deleteTenantByIdService;

    @BeforeEach
    void setUp() {
        deleteTenantByIdService = new DeleteTenantByIdServiceImpl(tenantRepository);

        Tenant tenant = new Tenant();
        tenant.setUsername("tenant_1");
        tenant.setEmail("tenant1@example.com");
        tenant.setFirstName("John");
        tenant.setLastName("Smith");
        tenant.setBirthDate(LocalDate.of(2000, 4, 10));

        tenantRepository.save(tenant);
    }

    @Test
    void shouldDeleteTenantById() {
        int tenantId = 1;
        deleteTenantByIdService.deleteTenantById(tenantId);
    }
}
