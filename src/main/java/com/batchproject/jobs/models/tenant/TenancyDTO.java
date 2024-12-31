package com.batchproject.jobs.models.tenant;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TenancyDTO {

    private Long suiteId;
    private Long tenantProfileId;
    private LocalDate startDate;
    private LocalDate endDate;
}