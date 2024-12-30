package com.batchproject.jobs.models.maintenance;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WorkOrderDTO {

    private String description;
    private LocalDate completedDate;
    private String status; // e.g., "Pending", "In Progress", "Completed"
    private String notes;
    private Long suiteId;       // ID of the associated Suite
    private Long buildingId;    // ID of the associated HousingBuilding
    private Long maintenanceId;
}
