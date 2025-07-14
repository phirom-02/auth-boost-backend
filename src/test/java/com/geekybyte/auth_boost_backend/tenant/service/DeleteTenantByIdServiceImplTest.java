package com.geekybyte.auth_boost_backend.tenant.service;

import com.geekybyte.auth_boost_backend.BaseTest;
import com.geekybyte.auth_boost_backend.tenant.TenantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteTenantByIdServiceImplTest extends BaseTest {

    @InjectMocks
    private DeleteTenantByIdServiceImpl deleteTenantByIdService;

    @Mock
    private TenantRepository tenantRepository;

    @Test
    void shouldDeleteTenantById() {
        // Arrange
        int tenantId = 1;

        doNothing().when(tenantRepository).deleteById(tenantId);

        // Act
        deleteTenantByIdService.deleteTenantById(tenantId);

        // Verify
        verify(tenantRepository, times(1)).deleteById(tenantId);
    }
}