package com.geekybyte.auth_boost_backend.tenant;

import com.geekybyte.auth_boost_backend.BaseTest;
import com.geekybyte.auth_boost_backend.tenant.dto.CreateTenantDto;
import com.geekybyte.auth_boost_backend.tenant.dto.UpdateTenantDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class TenantControllerIntegrationTest extends BaseTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnAllTenants() {
        Tenant[] tenants = restTemplate.getForObject("/tenants", Tenant[].class);
        assertThat(tenants.length).isEqualTo(1);
    }

    @Test
    void shouldReturnTenantById() {
        Tenant tenant = restTemplate.getForObject("/tenants/1", Tenant.class);
        assertThat(tenant.getId()).isEqualTo(1);
    }

    @Test
    @Rollback
    void shouldReturnTenantAfterCreate() {
        CreateTenantDto dto = new CreateTenantDto(
                "tenant_1",
                "tenant1@example.com",
                "John",
                null,
                "Smith",
                LocalDate.parse("2000-04-10")
        );

        ResponseEntity<Tenant> response = restTemplate.exchange("/tenants", HttpMethod.POST, new HttpEntity<CreateTenantDto>(dto), Tenant.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getId()).isEqualTo(1);
        assertThat(Objects.requireNonNull(response.getBody()).getUsername()).isEqualTo("tenant_1");
        assertThat(Objects.requireNonNull(response.getBody()).getEmail()).isEqualTo("tenant1@example.com");
        assertThat(Objects.requireNonNull(response.getBody()).getFirstName()).isEqualTo("John");
        assertThat(Objects.requireNonNull(response.getBody()).getMiddleName()).isEqualTo(null);
        assertThat(Objects.requireNonNull(response.getBody()).getLastName()).isEqualTo("Smith");
        assertThat(Objects.requireNonNull(response.getBody()).getBirthDate()).isEqualTo("2000-04-10");
    }

    @Test
    @Rollback
    void shouldDeleteTenantById() {
        int tenantId = 1;
        ResponseEntity<Object> response = restTemplate.exchange("/tenants/" + tenantId, HttpMethod.DELETE, null, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();
    }

    @Test
    @Rollback
    void shouldReturnTenantAfterUpdate() {
        int tenantId = 1;
        UpdateTenantDto dto = new UpdateTenantDto(
                "tenant_xyz",
                null,
                null,
                null,
                null,
                null
        );

        ResponseEntity<Tenant> response = restTemplate.exchange("/tenants/" + tenantId, HttpMethod.PATCH, new HttpEntity<UpdateTenantDto>(dto), Tenant.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getUsername()).isEqualTo("tenant_xyz");
    }
}
