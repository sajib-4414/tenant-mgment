package com.batchproject.jobs.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//every work order is created for a maintenance.
//some maintenance can have more than one work order
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkOrder {
    private String description;
    private LocalDate completedDate;
    private String status; // e.g., "Pending", "In Progress", "Completed"
    private String notes;
    private Suite suite;
    private Maintenance maintenance;

}
