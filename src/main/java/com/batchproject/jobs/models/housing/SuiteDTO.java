package com.batchproject.jobs.models.housing;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SuiteDTO {

    private Long addressId;
    private LocalDate builtOn;
    private LocalDate lastRenovatedOn;
    private Integer noOfBedRooms;
    private Integer noOfBathRooms;
    private Boolean haveDedicatedLaundry;
    private Integer floorNo;
    private Long buildingId;
}
