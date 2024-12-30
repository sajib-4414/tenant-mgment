package com.batchproject.jobs.controllers;

import com.batchproject.jobs.models.rent.Rent;
import com.batchproject.jobs.models.rent.RentDTO;
import com.batchproject.jobs.services.RentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/rents")
@AllArgsConstructor
public class RentController {
    private final RentService rentService;

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Rent>>> getAllRents() {
        return rentService.getAllRents()
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<Rent>> createRent(@RequestBody RentDTO rentDTO) {
        return rentService.createRent(rentDTO)
                .thenApply(savedRent -> ResponseEntity.status(HttpStatus.CREATED).body(savedRent))
                .exceptionally(ex -> {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Rent>> updateRent(@PathVariable Integer id, @RequestBody RentDTO rentDTO) {
        return rentService.updateRent(id, rentDTO)
                .thenApply(updatedRent -> ResponseEntity.status(HttpStatus.OK).body(updatedRent))
                .exceptionally(ex -> {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteRent(@PathVariable Integer id) {
        return rentService.deleteRent(id)
                .thenApply(response -> ResponseEntity.status(HttpStatus.NO_CONTENT).<Void>build())
                .exceptionally(ex -> {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
    }
}
