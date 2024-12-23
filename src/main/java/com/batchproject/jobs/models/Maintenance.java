package com.batchproject.jobs.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

//@Entity
//@Data
//@Table(name = "maintenance")
public class Maintenance extends BaseEntity {

    @Column(name = "issue_description", nullable = false)
    private String issueDescription;

    @Column(name = "request_date", nullable = false)
    private LocalDate requestDate;

    @Column(name = "priority", nullable = false)
    private String priority; // e.g., "Low", "Medium", "High"

    @Column(name = "status", nullable = false)
    private String status; // e.g., "Open", "Closed"

    @OneToMany(mappedBy = "maintenance")
    private List<WorkOrder> workOrders;

    // Getters and setters
}
