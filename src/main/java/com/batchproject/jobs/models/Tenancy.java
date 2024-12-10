package com.batchproject.jobs.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tenancy {
    private Suite suite;
    private TenantProfile tenantProfile;
    private LocalDate startDate;
    private LocalDate endDate;
}
