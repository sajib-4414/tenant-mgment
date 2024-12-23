package com.batchproject.jobs.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name="tenant_history")
@Data
public class TenantHistory extends BaseEntity{

    @Column(name = "tenant_profile_id")
    private TenantProfile tenantProfile;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate; //no end date means its current
    @Column(name = "is_current_tenant")
    private Boolean isCurrentTenant; //also yes means currently renting
    @JoinColumn(name = "housing_building_stayed")
    private HousingBuilding buildingStayed;
}
