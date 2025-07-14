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

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindTenantByIdServiceImplTest extends BaseTest {

    @InjectMocks
    FindTenantByIdServiceImpl findTenantByIdService;

    @Mock
    TenantRepository tenantRepository;

    Tenant tenant;

    @BeforeEach
    void setUp() {
        tenant = new Tenant();
        tenant.setId(1);
        tenant.setUsername("tenant_1");
        tenant.setEmail("tenant1@example.com");
        tenant.setFirstName("John");
        tenant.setLastName("Smith");
    }

    @Test
    void shouldReturnTenantByValidId() {
        // Arrange
        int tenantId = 1;

        when(tenantRepository.findById(tenantId)).thenReturn(Optional.of(tenant));

        // Act
        Optional<Tenant> result = findTenantByIdService.findTenantById(tenantId);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.get().getId()).isEqualTo(tenantId);

        // Verify
        verify(tenantRepository, times(1)).findById(tenantId);
    }
}