package com.batchproject.housingservice.externalservice;

import lombok.Data;

import java.time.LocalDate;

//dont extend the base model here, it creates problem as that one is entity, its not entity
@Data
public class RentPriceExternal  {
    private Long id;
    private LocalDate effectiveStartDate;
    private LocalDate effectiveEndDate;
    private Double rentAmt;
    private Long suiteId;
}