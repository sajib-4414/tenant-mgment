package com.batchproject.jobs.models.maintenance;

import com.batchproject.jobs.models.BaseEntity;
import com.batchproject.jobs.models.housing.HousingBuilding;
import com.batchproject.jobs.models.housing.Suite;
import jakarta.persistence.*;
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
@Entity
@Table(name = "work_order")
public class WorkOrder extends BaseEntity {


    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "completed_date")
    private LocalDate completedDate;

    @Column(name = "status", nullable = false) // e.g., "Pending", "In Progress", "Completed"
    private String status;

    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "suite_id", nullable = false)
    private Suite suite;

    @ManyToOne
    @JoinColumn(name = "housing_building_id", nullable = false)
    private HousingBuilding building;

    @ManyToOne
    @JoinColumn(name = "maintenance_id", nullable = false)
    private Maintenance maintenance;

    // Getters and Setters
}

