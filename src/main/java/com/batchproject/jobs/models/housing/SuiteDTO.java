package com.batchproject.jobs.models.housing;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SuiteDTO {

    private Integer apartmentNo;
    private LocalDate builtOn;
    private LocalDate lastRenovatedOn;
    private Integer noOfBedRooms;
    private Integer noOfBathRooms;
    private Boolean haveDedicatedLaundry;
    private Integer floorNo;
    private Long buildingId;
    private Double rent;//this will be the active new rent price from today
}
