package com.geekybyte.auth_boost_backend.tenant.service;

import com.geekybyte.auth_boost_backend.BaseTest;
import com.geekybyte.auth_boost_backend.tenant.Tenant;
import com.geekybyte.auth_boost_backend.tenant.TenantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindAllTenantsServiceImplTest extends BaseTest {

    @InjectMocks
    FindAllTenantsServiceImpl findAllTenantsService;

    @Mock
    TenantRepository tenantRepository;

    List<Tenant> tenants = new ArrayList<>();

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
        tenants.add(tenant1);
        tenants.add(tenant2);
    }

    @Test
    void shouldReturnAllTenants() {
        // Arrange
        when(tenantRepository.findAll()).thenReturn(tenants);

        // Act
        List<Tenant> tenants = findAllTenantsService.findAllTenants();

        // Assert
        assertThat(tenants).isNotNull();
        assertThat(tenants.size()).isEqualTo(tenants.size());

        // Verify
        verify(tenantRepository, times(1)).findAll();
    }
}