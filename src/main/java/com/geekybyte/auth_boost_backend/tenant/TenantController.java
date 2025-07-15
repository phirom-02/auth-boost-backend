package com.geekybyte.auth_boost_backend.tenant;

import com.geekybyte.auth_boost_backend.tenant.dto.CreateTenantDto;
import com.geekybyte.auth_boost_backend.tenant.dto.UpdateTenantDto;
import com.geekybyte.auth_boost_backend.tenant.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tenants")
public class TenantController {

    private final FindAllTenantsService findAllTenantsService;
    private final FindTenantByIdService findTenantByIdService;
    private final DeleteTenantByIdService deleteTenantByIdService;
    private final CreateTenantService createTenantService;
    private final UpdateTenantService updateTenantService;

    public TenantController(
            FindAllTenantsService findAllTenantsService,
            FindTenantByIdService findTenantByIdService,
            DeleteTenantByIdService deleteTenantByIdService,
            CreateTenantService createTenantService,
            UpdateTenantService updateTenantService
    ) {
        this.findAllTenantsService = findAllTenantsService;
        this.findTenantByIdService = findTenantByIdService;
        this.deleteTenantByIdService = deleteTenantByIdService;
        this.createTenantService = createTenantService;
        this.updateTenantService = updateTenantService;
    }

    @GetMapping("")
    public List<Tenant> getAllTenants() {
        return findAllTenantsService.findAllTenants();
    }

    @GetMapping("/{id}")
    public Optional<Tenant> getTenantById(@PathVariable int id) {
        return findTenantByIdService.findTenantById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTenantById(@PathVariable int id) {
        deleteTenantByIdService.deleteTenantById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Tenant createTenant(@RequestBody CreateTenantDto dto) {
        return createTenantService.createTenant(dto);
    }

    @PatchMapping("/{id}")
    public Tenant updateTenant(@PathVariable int id, @RequestBody UpdateTenantDto dto) {
        return updateTenantService.updateTenant(id, dto);
    }
}
