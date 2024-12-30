package com.batchproject.jobs.controllers;

import com.batchproject.jobs.models.maintenance.Maintenance;
import com.batchproject.jobs.models.maintenance.MaintenanceDTO;
import com.batchproject.jobs.services.MaintenanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@RequestMapping("/api/maintenances")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Maintenance>>> getAllMaintenances() {
        return maintenanceService.getAllMaintenances()
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Maintenance>> getMaintenanceDetail(@PathVariable Integer id) {
        return maintenanceService.getMaintenanceDetail(id)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                });
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Maintenance>> updateMaintenance(@PathVariable Integer id, @RequestBody MaintenanceDTO updatedDto) {
        return maintenanceService.updateMaintenance(id, updatedDto)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
                });
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteMaintenance(@PathVariable Integer id) {
        return maintenanceService.deleteMaintenance(id)
                .thenApply(unused -> ResponseEntity.noContent().<Void>build())
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                });
    }


}
