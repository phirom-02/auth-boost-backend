package com.geekybyte.auth_boost_backend.tenant.service;

import com.geekybyte.auth_boost_backend.BaseTest;
import com.geekybyte.auth_boost_backend.tenant.Tenant;
import com.geekybyte.auth_boost_backend.tenant.TenantRepository;
import com.geekybyte.auth_boost_backend.tenant.dto.CreateTenantDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateTenantServiceImplTest extends BaseTest {

    @InjectMocks
    CreateTenantServiceImpl createTenantService;

    @Mock
    TenantRepository tenantRepository;

    @Test
    void shouldReturnTenantAfterCreate() {
        // Arrange
        CreateTenantDto dto = new CreateTenantDto(
                "tenant_1",
                "tenant1@example.com",
                "John",
                null,
                "Smith",
                LocalDate.parse("2000-04-28")
        );

        Tenant tenant = new Tenant();
        tenant.setId(1);
        tenant.setUsername(dto.username());
        tenant.setEmail(dto.email());
        tenant.setFirstName(dto.firstName());
        tenant.setMiddleName(dto.middleName());
        tenant.setLastName(dto.lastName());
        tenant.setBirthDate(dto.birthDate());

        when(tenantRepository.save(any(Tenant.class))).thenReturn(tenant);

        // Act
        Tenant createTenant = createTenantService.createTenant(dto);

        // Assert
        assertThat(createTenant).isNotNull();
        assertThat(createTenant.getId()).isEqualTo(1);
        assertThat(createTenant.getUsername()).isEqualTo(dto.username());
        assertThat(createTenant.getEmail()).isEqualTo(dto.email());
        assertThat(createTenant.getFirstName()).isEqualTo(dto.firstName());
        assertThat(createTenant.getMiddleName()).isEqualTo(dto.middleName());
        assertThat(createTenant.getLastName()).isEqualTo(dto.lastName());
        assertThat(createTenant.getBirthDate()).isEqualTo(dto.birthDate());

        // Verify
        verify(tenantRepository, times(1)).save(any(Tenant.class));
    }

}