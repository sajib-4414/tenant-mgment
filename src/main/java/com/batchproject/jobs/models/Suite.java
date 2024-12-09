package com.batchproject.jobs.models;

import java.time.LocalDate;

public class Suite {
    private Address address;
    private LocalDate builtOn;
    private LocalDate lastRenovatedOn;
    private Integer NoOfBedRooms;
    private Integer NoOfBathRooms;
    private Boolean haveDedicatedLaundry;
    private Integer floorNo;
    //even if its a house, it will be attached to a building
    private HousingBuilding building;

}
