package com.batchproject.jobs.models;

import java.time.LocalDate;

public class WorkOrder {
    private String description;
    private LocalDate completedDate;
    private String status; // e.g., "Pending", "In Progress", "Completed"
    private String notes;
    private Suite suite;
    private Maintenance maintenance;

}
