package com.geekybyte.auth_boost_backend.tenant.service;

import com.geekybyte.auth_boost_backend.BaseTest;
import com.geekybyte.auth_boost_backend.tenant.Tenant;
import com.geekybyte.auth_boost_backend.tenant.TenantRepository;
import com.geekybyte.auth_boost_backend.tenant.dto.CreateTenantDto;
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
public class CreateTenantServiceImplIntegrationTest extends BaseTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    private TenantRepository tenantRepository;

    private CreateTenantServiceImpl createTenantService;

    @BeforeEach
    void setUp() {
        createTenantService = new CreateTenantServiceImpl(tenantRepository);
    }

    @Test
    @Rollback
    void shouldReturnTenantAfterCreateTenant() {
        CreateTenantDto dto = new CreateTenantDto(
                "tenant1",
                "tenant1@example.com",
                "John",
                null,
                "Smith",
                LocalDate.parse("2000-04-28")
        );

        Tenant createdTenant = createTenantService.createTenant(dto);
        assertThat(createdTenant).isNotNull();
        assertThat(createdTenant.getUsername()).isEqualTo(dto.username());
        assertThat(createdTenant.getEmail()).isEqualTo(dto.email());
        assertThat(createdTenant.getFirstName()).isEqualTo(dto.firstName());
        assertThat(createdTenant.getMiddleName()).isEqualTo(dto.middleName());
        assertThat(createdTenant.getLastName()).isEqualTo(dto.lastName());
        assertThat(createdTenant.getBirthDate()).isEqualTo(dto.birthDate());
    }
}
