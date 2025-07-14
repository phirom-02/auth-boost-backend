package com.geekybyte.auth_boost_backend.tenant.service;

import com.geekybyte.auth_boost_backend.BaseTest;
import com.geekybyte.auth_boost_backend.tenant.Tenant;
import com.geekybyte.auth_boost_backend.tenant.TenantRepository;
import com.geekybyte.auth_boost_backend.tenant.dto.UpdateTenantDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateTenantServiceImplTest extends BaseTest {

    @InjectMocks
    private UpdateTenantServiceImpl updateTenantService;

    @Mock
    private TenantRepository tenantRepository;
    @Mock
    private FindTenantByIdServiceImpl findTenantByIdService;

    @Test
    void shouldReturnTenantAfterUpdate() {
        // Arrange
        int tenantId = 1;
        UpdateTenantDto dto = new UpdateTenantDto(
                "tenant_xyz",
                null,
                null,
                null,
                null,
                null
        );

        Tenant tenant = new Tenant();
        tenant.setId(1);
        tenant.setUsername("tenant_1");

        when(findTenantByIdService.findTenantById(tenantId)).thenReturn(Optional.of(tenant));

        tenant.setUsername("tenant_xyz");
        when(tenantRepository.save(any(Tenant.class))).thenReturn(tenant);

        // Act
        Tenant updatedTenant = updateTenantService.updateTenant(tenantId, dto);

        // Assert
        assertThat(updatedTenant).isNotNull();
        assertThat(updatedTenant.getId()).isEqualTo(1);
        assertThat(updatedTenant.getUsername()).isEqualTo("tenant_xyz");

        // Verify
        verify(tenantRepository, times(1)).save(any(Tenant.class));
        verify(findTenantByIdService, times(1)).findTenantById(tenantId);
    }
}