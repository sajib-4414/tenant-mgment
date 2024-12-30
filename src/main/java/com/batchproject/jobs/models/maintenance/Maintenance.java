package com.batchproject.jobs.models.maintenance;

import com.batchproject.jobs.models.BaseEntity;
import com.batchproject.jobs.models.housing.Suite;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "maintenance")
//maintanenace request is created first, then for each maintenance request
//work orders are created by the staff.
public class Maintenance extends BaseEntity {

    @Column(name = "issue_description", nullable = false)
    private String issueDescription;

    @Column(name = "request_date", nullable = false)
    private LocalDate requestDate;

    @Column(name = "priority", nullable = false)
    private String priority; // e.g., "Low", "Medium", "High"

    @Column(name = "status", nullable = false)
    private String status; // e.g., "Open", "Closed"

    @JoinColumn(name = "suite_id")
    @ManyToOne
    private Suite suite;

    @OneToMany(mappedBy = "maintenance")
    private List<WorkOrder> workOrders;

    // Getters and setters
}
