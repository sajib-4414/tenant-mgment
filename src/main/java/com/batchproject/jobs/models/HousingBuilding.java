package com.batchproject.jobs.models;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

//for a house, it will be one building, and one associated suite.
public class HousingBuilding {
    private String name;
    private LocalDate builtOn;
    private LocalDate possessedOn;
    private List<Suite> suites;
    private Address address;
    private Integer buildingLaundryWashPrice;
    private Integer buildingLaundryDryPrice;
    private Integer noOfSharedLaundryRooms;
    private Integer storeysInBuilding;
}
