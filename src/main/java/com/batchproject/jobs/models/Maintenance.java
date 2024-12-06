package com.batchproject.jobs.models;

import java.time.LocalDate;
import java.util.List;

public class Maintenance {

    private String issueDescription;
    private LocalDate requestDate;
    private String priority; // e.g., "Low", "Medium", "High"
    private String status; // e.g., "Open", "Closed"
    private List<WorkOrder> workOrders;
}
