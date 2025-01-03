package com.batchproject.jobs.models.rent;

import com.batchproject.jobs.models.BaseEntity;
import com.batchproject.jobs.models.tenant.TenantProfile;
import com.batchproject.jobs.models.housing.Suite;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
//this rent will be created by system, at the end of month, this is another job.
@Entity
@Table(name = "rent")
public class Rent extends BaseEntity {
    @Column(name = "amount")
    private int amount;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @Column(name = "paid_date")
    private LocalDate paidDate;
    @Column(name = "status")
    private String status;

    @JoinColumn(name = "tenant_profile")
    @ManyToOne
    private TenantProfile tenantProfile;

    @JoinColumn(name = "suite_id")
    @ManyToOne
    private Suite suite;
}
