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
//this rent will be created by system, at the end of month, this is another job.
public class Rent extends BaseEntity{
    private int amount;
    private LocalDate dueDate;
    private LocalDate paidDate;
    private String status;
    private TenantProfile tenantProfile;
    private Suite suite;
}
