package com.geekybyte.auth_boost_backend.tenant.service;

import com.geekybyte.auth_boost_backend.BaseTest;
import com.geekybyte.auth_boost_backend.tenant.Tenant;
import com.geekybyte.auth_boost_backend.tenant.TenantRepository;
import com.geekybyte.auth_boost_backend.tenant.dto.UpdateTenantDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Testcontainers
@Transactional
public class UpdateTenantServiceImplIntegrationTest extends BaseTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    private TenantRepository tenantRepository;
    @Autowired
    private FindTenantByIdServiceImpl findTenantByIdService;

    private UpdateTenantServiceImpl updateTenantService;

    @BeforeEach
    void setUp() {
        updateTenantService = new UpdateTenantServiceImpl(tenantRepository, findTenantByIdService);

        Tenant tenant = new Tenant();
        tenant.setUsername("tenant_1");
        tenant.setEmail("tenant1@example.com");
        tenant.setFirstName("John");
        tenant.setLastName("Smith");
        tenant.setBirthDate(LocalDate.of(2000, 4, 10));

        tenantRepository.save(tenant);
    }

    @Test
    @Rollback
    void shouldReturnTenantAfterUpdate() {
        int tenantId = 1;
        UpdateTenantDto dto = new UpdateTenantDto("tenant_xyz", null, null, null, null, null);

        Tenant updatedTenant = updateTenantService.updateTenant(tenantId, dto);

        assertThat(updatedTenant).isNotNull();
        assertThat(updatedTenant.getUsername()).isEqualTo("tenant_xyz");
    }
}
