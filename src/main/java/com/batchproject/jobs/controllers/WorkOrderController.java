package com.batchproject.jobs.controllers;

import com.batchproject.jobs.models.maintenance.WorkOrder;
import com.batchproject.jobs.models.maintenance.WorkOrderDTO;
import com.batchproject.jobs.services.WorkOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
public class WorkOrderController {

    private final WorkOrderService workOrderService;

    @GetMapping
    public CompletableFuture<ResponseEntity<List<WorkOrder>>> getAllWorkOrders() {
        return workOrderService.getAllWorkOrders()
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
    }
    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<WorkOrder>> getWorkOrderDetail(@PathVariable Integer id) {
        return workOrderService.getWorkOrderDetail(id)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                });
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<WorkOrderDTO>> updateWorkOrder(@PathVariable Integer id, @RequestBody WorkOrderDTO updatedDto) {
        return workOrderService.updateWorkOrder(id, updatedDto)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
                });
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteWorkOrder(@PathVariable Integer id) {
        return workOrderService.deleteWorkOrder(id)
                .thenApply(unused -> ResponseEntity.noContent().<Void>build())
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                });
    }


}
