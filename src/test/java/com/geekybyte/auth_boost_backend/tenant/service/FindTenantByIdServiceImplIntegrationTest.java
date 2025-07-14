package com.geekybyte.auth_boost_backend.tenant.service;

import com.geekybyte.auth_boost_backend.BaseTest;
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
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Testcontainers
public class FindTenantByIdServiceImplIntegrationTest extends BaseTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    private TenantRepository tenantRepository;

    private FindTenantByIdServiceImpl findTenantByIdService;

    @BeforeEach
    void setup() {
        findTenantByIdService = new FindTenantByIdServiceImpl(tenantRepository);

        Tenant tenant = new Tenant();
        tenant.setUsername("tenant_1");
        tenant.setEmail("tenant1@example.com");
        tenant.setFirstName("John");
        tenant.setLastName("Smith");
        tenant.setBirthDate(LocalDate.of(2000, 4, 10));

        tenantRepository.save(tenant);
    }

    @Test
    void shouldReturnTenantById() {
        int tenantId = 1;

        Optional<Tenant> tenant = findTenantByIdService.findTenantById(tenantId);
        assertThat(tenant).isPresent();
        assertThat(tenantId).isEqualTo(tenant.get().getId());
    }
}
