package com.batchproject.jobs.services;

import com.batchproject.jobs.models.housing.Suite;
import com.batchproject.jobs.models.housing.SuiteRepository;
import com.batchproject.jobs.models.maintenance.Maintenance;
import com.batchproject.jobs.models.maintenance.MaintenanceDTO;
import com.batchproject.jobs.models.maintenance.MaintenanceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final SuiteRepository suiteRepository;

    @Async
    public CompletableFuture<List<Maintenance>> getAllMaintenances() {
        return CompletableFuture.completedFuture(
                maintenanceRepository.findAll());
    }

    @Async
    public CompletableFuture<Maintenance> getMaintenanceDetail(Integer id) {
        return CompletableFuture.completedFuture(
                maintenanceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Maintenance not found with id: " + id)));

    }

    @Async
    public CompletableFuture<Maintenance> updateMaintenance(Integer id, MaintenanceDTO updatedDto) {
            Maintenance maintenance = maintenanceRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Maintenance not found with id: " + id));

            maintenance.setIssueDescription(updatedDto.getIssueDescription());
            maintenance.setRequestDate(updatedDto.getRequestDate());
            maintenance.setPriority(updatedDto.getPriority());
            maintenance.setStatus(updatedDto.getStatus());
            if (updatedDto.getSuiteId() != null) {
                Suite suite = suiteRepository.findById(updatedDto.getSuiteId())
                        .orElseThrow(() -> new EntityNotFoundException("Suite not found with id: " + updatedDto.getSuiteId()));
                maintenance.setSuite(suite);
            } else {
                maintenance.setSuite(null);
            }
            Maintenance saved = maintenanceRepository.save(maintenance);
            return CompletableFuture.completedFuture(saved);
    }

    @Async
    public CompletableFuture<Void> deleteMaintenance(Integer id) {
        return CompletableFuture.runAsync(() -> {
            if (!maintenanceRepository.existsById(id)) {
                throw new EntityNotFoundException("Maintenance not found with id: " + id);
            }
            maintenanceRepository.deleteById(id);
        });
    }
}
