package com.batchproject.jobs.models.housing;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HousingDTO {
    private String name;
    private Boolean hasInHouseLaundry;
    private LocalDate possessedOn;
    private LocalDate builtOn;
    private Long address_id;
}
