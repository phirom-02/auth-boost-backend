package com.geekybyte.auth_boost_backend.tenant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TenantRepositoryIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16:9");

    @Autowired
    private TenantRepository tenantRepository;

    @Test
    void connectionEstablished() {
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }

    @BeforeEach
    void setUp() {
        Tenant tenant1 = new Tenant();
        tenant1.setUsername("tenant_1");
        tenant1.setEmail("tenant1@example.com");
        tenant1.setFirstName("John");
        tenant1.setLastName("Smith");
        tenant1.setBirthDate(LocalDate.of(2000, 4, 10));
        Tenant tenant2 = new Tenant();
        tenant2.setUsername("tenant_2");
        tenant2.setEmail("tenant2@example.com");
        tenant2.setFirstName("Luke");
        tenant2.setLastName("Cage");
        tenant2.setBirthDate(LocalDate.of(2000, 4, 10));


        List<Tenant> tenants = List.of(tenant1, tenant2);

        tenantRepository.saveAll(tenants);
    }

    @Test
    void shouldRetrieveTenantById() {
        int tenantId = 1;
        Optional<Tenant> tenant = tenantRepository.findById(tenantId);
        assertThat(tenant).isPresent();
        assertThat(tenant.get().getId()).isEqualTo(tenantId);
    }
}
