package com.batchproject.housingservice.externalservice;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class RentPriceExternalDTO {

    private LocalDate effectiveStartDate;
    private LocalDate effectiveEndDate;
    private Double rentAmt;
    private Long suiteId;
}