package com.batchproject.jobs.models.maintenance;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MaintenanceDTO {
    private String issueDescription;
    private LocalDate requestDate;
    private String priority; // e.g., "Low", "Medium", "High"
    private String status;   // e.g., "Open", "Closed"
    private Integer suiteId;    // ID of the associated suite
}
