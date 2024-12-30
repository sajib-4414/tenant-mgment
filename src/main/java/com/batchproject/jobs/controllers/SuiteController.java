package com.batchproject.jobs.controllers;


import com.batchproject.jobs.models.housing.Suite;
import com.batchproject.jobs.models.housing.SuiteDTO;
import com.batchproject.jobs.services.SuitService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/suites")
@AllArgsConstructor
public class SuiteController {

    private final SuitService suiteService;

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Suite>>> getAllSuites() {
        return suiteService.getAllSuites()
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Suite>> getSuiteById(@PathVariable Integer id) {
        return suiteService.getSuiteById(id)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                });
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<Suite>> createSuite(@RequestBody SuiteDTO suiteDTO) {
        return suiteService.saveSuite(suiteDTO)
                .thenApply(savedSuite -> ResponseEntity.status(HttpStatus.CREATED).body(savedSuite))
                .exceptionally(ex -> {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteSuite(@PathVariable Integer id) {
        return suiteService.deleteSuite(id)
                .thenApply(unused -> ResponseEntity.noContent().<Void>build())
                .exceptionally(ex -> {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).<Void>build();
                });
    }
}
