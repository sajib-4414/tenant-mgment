package com.batchproject.jobs.models.housing;

import com.batchproject.jobs.models.address.Address;
import com.batchproject.jobs.models.rent.RentPrice;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SuiteDetailsDTO {
    private Long id;
    private LocalDate builtOn;
    private LocalDate lastRenovatedOn;
    private Integer noOfBedRooms;
    private Integer noOfBathRooms;
    private Boolean haveDedicatedLaundry;
    private Integer floorNo;
    private Address address;
    private HousingBuilding building;
    private RentPrice rent;
}
