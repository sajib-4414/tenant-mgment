package com.batchproject.jobs.services;

import com.batchproject.jobs.models.housing.HousingBuilding;
import com.batchproject.jobs.models.housing.HousingBuildingRepository;
import com.batchproject.jobs.models.housing.Suite;
import com.batchproject.jobs.models.housing.SuiteRepository;
import com.batchproject.jobs.models.maintenance.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class WorkOrderService {

    private final WorkOrderRepository workOrderRepository;
    private final SuiteRepository suiteRepository;
    private final HousingBuildingRepository buildingRepository;
    private final MaintenanceRepository maintenanceRepository;

    @Async
    public CompletableFuture<List<WorkOrder>> getAllWorkOrders() {
        return CompletableFuture.completedFuture(
                workOrderRepository.findAll()
                        .stream()
                        .toList()
        );
    }

    @Async
    public CompletableFuture<WorkOrder> getWorkOrderDetail(Integer id) {

        return CompletableFuture.completedFuture(
                workOrderRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("WorkOrder not found with id: " + id)));

    }

    private WorkOrderDTO convertToDTO(WorkOrder workOrder) {
        WorkOrderDTO dto = new WorkOrderDTO();
        dto.setDescription(workOrder.getDescription());
        dto.setCompletedDate(workOrder.getCompletedDate());
        dto.setStatus(workOrder.getStatus());
        dto.setNotes(workOrder.getNotes());
        dto.setSuiteId(workOrder.getSuite().getId());
        dto.setBuildingId(workOrder.getBuilding().getId());
        dto.setMaintenanceId(workOrder.getMaintenance().getId());
        return dto;
    }

    @Async
    public CompletableFuture<WorkOrderDTO> updateWorkOrder(Integer id, WorkOrderDTO updatedDto) {
        return CompletableFuture.supplyAsync(() -> {
            WorkOrder workOrder = workOrderRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("WorkOrder not found with id: " + id));

            workOrder.setDescription(updatedDto.getDescription());
            workOrder.setCompletedDate(updatedDto.getCompletedDate());
            workOrder.setStatus(updatedDto.getStatus());
            workOrder.setNotes(updatedDto.getNotes());

            if (updatedDto.getSuiteId() != null) {
                Suite suite = suiteRepository.findById(updatedDto.getSuiteId())
                        .orElseThrow(() -> new EntityNotFoundException("Suite not found with id: " + updatedDto.getSuiteId()));
                workOrder.setSuite(suite);
            }

            if (updatedDto.getBuildingId() != null) {
                HousingBuilding building = buildingRepository.findById(updatedDto.getBuildingId())
                        .orElseThrow(() -> new EntityNotFoundException("Building not found with id: " + updatedDto.getBuildingId()));
                workOrder.setBuilding(building);
            }

            if (updatedDto.getMaintenanceId() != null) {
                Maintenance maintenance = maintenanceRepository.findById(updatedDto.getMaintenanceId())
                        .orElseThrow(() -> new EntityNotFoundException("Maintenance not found with id: " + updatedDto.getMaintenanceId()));
                workOrder.setMaintenance(maintenance);
            }

            WorkOrder saved = workOrderRepository.save(workOrder);
            return convertToDTO(saved);
        });
    }

    @Async
    public CompletableFuture<Void> deleteWorkOrder(Integer id) {

        if (!workOrderRepository.existsById(id)) {
            throw new EntityNotFoundException("WorkOrder not found with id: " + id);
        }
        workOrderRepository.deleteById(id);
        return CompletableFuture.completedFuture(null);

    }



}
