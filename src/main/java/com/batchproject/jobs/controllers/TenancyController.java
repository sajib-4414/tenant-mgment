package com.batchproject.jobs.controllers;

import com.batchproject.jobs.models.tenant.Tenancy;
import com.batchproject.jobs.models.tenant.TenancyDTO;
import com.batchproject.jobs.services.TenancyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/tenancy")
public class TenancyController {

    private final TenancyService tenancyService;

    public TenancyController(TenancyService tenancyService) {
        this.tenancyService = tenancyService;
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Tenancy>>> getAllTenancies() {
        return tenancyService.getAllTenancies()
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<Tenancy>> createTenancy(@RequestBody TenancyDTO tenancyDTO) {
        return tenancyService.createTenancy(tenancyDTO)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Tenancy>> getTenancyById(@PathVariable Long id) {
        return tenancyService.getTenancyById(id)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Tenancy>> updateTenancy(@PathVariable Long id, @RequestBody TenancyDTO tenancyDTO) {
        return tenancyService.updateTenancy(id, tenancyDTO)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteTenancy(@PathVariable Long id) {
        return tenancyService.deleteTenancy(id)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
    }
}
