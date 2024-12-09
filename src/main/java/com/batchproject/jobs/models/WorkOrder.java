package com.batchproject.jobs.models;

import java.time.LocalDate;

//every work order is created for a maintenance.
//some maintenance can have more than one work order
public class WorkOrder {
    private String description;
    private LocalDate completedDate;
    private String status; // e.g., "Pending", "In Progress", "Completed"
    private String notes;
    private Suite suite;
    private Maintenance maintenance;

}
