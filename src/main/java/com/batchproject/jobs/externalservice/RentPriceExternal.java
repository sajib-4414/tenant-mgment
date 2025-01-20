package com.batchproject.jobs.externalservice;

import com.batchproject.jobs.models.BaseEntity;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RentPriceExternal extends BaseEntity {
    private LocalDate effectiveStartDate;
    private LocalDate effectiveEndDate;
    private Double rentAmt;
    private Long suiteId;
}