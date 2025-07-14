package com.geekybyte.auth_boost_backend.tenant;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekybyte.auth_boost_backend.tenant.dto.TenantsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
@Profile({"dev"})
public class TenantDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(TenantDataLoader.class);
    private final ObjectMapper objectMapper;
    private final TenantRepository tenantRepository;

    public TenantDataLoader(ObjectMapper objectMapper, TenantRepository tenantRepository) {
        this.objectMapper = objectMapper;
        this.tenantRepository = tenantRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (tenantRepository.count() == 0) {
            String tenantJson = "/data/tenants.json";
            log.info("Loading tenant data from {}", tenantJson);
            try (InputStream inputStream = TypeReference.class.getResourceAsStream(tenantJson)) {
                TenantsDto result = objectMapper.readValue(inputStream, TenantsDto.class);
                List<Tenant> tenants = result.tenants().stream().map(dto -> {
                    Tenant tenant = new Tenant();
                    tenant.setUsername(dto.username());
                    tenant.setEmail(dto.email());
                    tenant.setFirstName(dto.firstName());
                    tenant.setLastName(dto.lastName());
                    tenant.setBirthDate(dto.birthDate());
                    return tenant;
                }).toList();
                tenantRepository.saveAll(tenants);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load tenants from " + tenantJson, e);
            }
        }
    }
}
