package com.geekybyte.auth_boost_backend.tenant;

import com.geekybyte.auth_boost_backend.BaseTest;
import com.geekybyte.auth_boost_backend.tenant.dto.CreateTenantDto;
import com.geekybyte.auth_boost_backend.tenant.dto.UpdateTenantDto;
import com.geekybyte.auth_boost_backend.tenant.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TenantController.class)
@AutoConfigureMockMvc
class TenantControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FindAllTenantsService findAllTenantsService;
    @MockitoBean
    private FindTenantByIdService findTenantByIdService;
    @MockitoBean
    private DeleteTenantByIdService deleteTenantByIdService;
    @MockitoBean
    private CreateTenantServiceImpl createTenantServiceImpl;
    @MockitoBean
    private UpdateTenantServiceImpl updateTenantServiceImpl;

    List<Tenant> tenants = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Tenant tenant1 = new Tenant();
        tenant1.setId(1);
        tenant1.setUsername("tenant_1");
        tenant1.setEmail("tenant1@example.com");
        tenant1.setFirstName("John");
        tenant1.setLastName("Smith");
        tenant1.setBirthDate(LocalDate.of(2000, 4, 10));
        Tenant tenant2 = new Tenant();
        tenant2.setId(2);
        tenant2.setUsername("tenant_2");
        tenant2.setEmail("tenant2@example.com");
        tenant2.setFirstName("Luke");
        tenant2.setLastName("Cage");
        tenant2.setBirthDate(LocalDate.of(2000, 4, 10));

        tenants = List.of(tenant1, tenant2);
    }

    @Test
    void shouldReturnAllTenants() throws Exception {
        String jsonResponse = """
                [
                    {
                        "id": 1,
                        "username": "tenant_1",
                        "email": "tenant1@example.com",
                        "firstName": "John",
                        "middleName": null,
                        "lastName": "Smith",
                        "birthDate": "2000-04-10",
                        "createdAt": null,
                        "updatedAt": null
                    },
                    {
                        "id": 2,
                        "username": "tenant_2",
                        "email": "tenant2@example.com",
                        "firstName": "Luke",
                        "middleName": null,
                        "lastName": "Cage",
                        "birthDate": "2000-04-10",
                        "createdAt": null,
                        "updatedAt": null
                    }
                ]
                """;

        when(findAllTenantsService.findAllTenants()).thenReturn(tenants);

        mockMvc.perform(get("/tenants"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResponse));
    }

    @Test
    void shouldReturnTenantById() throws Exception {
        int tenantId = 1;

        String jsonResponse = """
                {
                        "id": 1,
                        "username": "tenant_1",
                        "email": "tenant1@example.com",
                        "firstName": "John",
                        "middleName": null,
                        "lastName": "Smith",
                        "birthDate": "2000-04-10",
                        "createdAt": null,
                        "updatedAt": null
                }
                """;

        when(findTenantByIdService.findTenantById(tenantId)).thenReturn(Optional.of(tenants.getFirst()));

        mockMvc.perform(get("/tenants/" + tenantId))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResponse));
    }

    @Test
    void shouldDeleteTenantById() throws Exception {
        int tenantId = 1;

        doNothing().when(deleteTenantByIdService).deleteTenantById(tenantId);

        mockMvc.perform(delete("/tenants/" + tenantId))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnTenantAfterCreate() throws Exception {
        String requestBody = """
                {
                    "username": "tenant_1",
                    "email": "tenant1@example.com",
                    "firstName": "John",
                    "lastName": "Smith",
                    "birthDate": "2000-04-10"
                }
                """;

        String responseBody = """
                {
                    "username": "tenant_1",
                    "email": "tenant1@example.com",
                    "firstName": "John",
                    "middleName": null,
                    "lastName": "Smith",
                    "birthDate": "2000-04-10",
                    "createdAt": null,
                    "updatedAt": null
                }
                """;

        CreateTenantDto dto = new CreateTenantDto(
                "tenant_1",
                "tenant1@example.com",
                "John",
                null,
                "Smith",
                LocalDate.parse("2000-04-10")
        );

        Tenant tenant = new Tenant();
        tenant.setId(1);
        tenant.setUsername("tenant_1");
        tenant.setEmail("tenant1@example.com");
        tenant.setFirstName("John");
        tenant.setLastName("Smith");
        tenant.setBirthDate(LocalDate.of(2000, 4, 10));

        when(createTenantServiceImpl.createTenant(dto)).thenReturn(tenant);

        mockMvc.perform(post("/tenants")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(content().json(responseBody));
    }

    @Test
    void shouldReturnTenantAfterUpdate() throws Exception {
        int tenantId = 1;

        String requestBody = """
                {
                    "username": "tenant_xyz"
                }
                """;

        String responseBody = """
                {
                    "id": 1,
                    "username": "tenant_xyz",
                    "email": "tenant1@example.com",
                    "firstName": "John",
                    "middleName": null,
                    "lastName": "Smith",
                    "birthDate": "2000-04-10",
                    "createdAt": null,
                    "updatedAt": null
                }
                """;

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
        tenant.setUsername("tenant_xyz");
        tenant.setEmail("tenant1@example.com");
        tenant.setFirstName("John");
        tenant.setLastName("Smith");
        tenant.setBirthDate(LocalDate.of(2000, 4, 10));

        when(updateTenantServiceImpl.updateTenant(tenantId, dto)).thenReturn(tenant);

        mockMvc.perform(patch("/tenants/" + tenantId)
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));

    }
}