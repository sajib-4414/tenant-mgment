package com.batchproject.jobs.controllers;

import com.batchproject.jobs.models.SysData;
import com.batchproject.jobs.models.housing.HousingBuilding;
import com.batchproject.jobs.models.housing.HousingDTO;
import com.batchproject.jobs.services.HousingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/housing-buildings")
@AllArgsConstructor
public class HousingController {
    private final HousingService housingService;

    @GetMapping
    public CompletableFuture<ResponseEntity<List<HousingBuilding>>> getAllBuildings() {

        CompletableFuture<ResponseEntity<List<HousingBuilding>>> future = housingService.getAllBuildings()
                .thenApply(result -> {
                    return ResponseEntity.ok(result);
                })
                .exceptionally(ex -> {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
        return future;
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<HousingBuilding>> getBuildingById(@PathVariable Integer id) {

        CompletableFuture<ResponseEntity<HousingBuilding>> future = housingService.getBuildingById(id)
                .thenApply(result -> {
                    return ResponseEntity.ok(result.orElseThrow(()->new RuntimeException("error happened")));
//                    if(result.p)
                })
                .exceptionally(ex -> {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
        return future;
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<HousingBuilding>> createBuilding(@RequestBody HousingDTO payload) {
        CompletableFuture<ResponseEntity<HousingBuilding>> future = housingService.saveBuilding(payload)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });

        return future;
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<HousingBuilding>> updateBuilding(
            @PathVariable Integer id, @RequestBody HousingDTO payload) {

        return housingService.updateBuilding(id, payload)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteBuilding(@PathVariable Integer id) {
        return housingService.deleteBuilding(id)
                .thenApply(unused -> ResponseEntity.noContent().<Void>build())
                .exceptionally(ex -> {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).<Void>build();
                });
    }





}
