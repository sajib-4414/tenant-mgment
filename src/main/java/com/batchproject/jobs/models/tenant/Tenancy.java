package com.batchproject.jobs.models.tenant;

import com.batchproject.jobs.models.BaseEntity;
import com.batchproject.jobs.models.housing.Suite;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tenancy")
public class Tenancy extends BaseEntity {
    @JoinColumn(name = "suite_id")
    @ManyToOne
    private Suite suite;

    @JoinColumn(name = "tenant_profile_id")
    @ManyToOne
    private TenantProfile tenantProfile;

    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
}
