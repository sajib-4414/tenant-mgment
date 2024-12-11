package com.batchproject.jobs.models;

import com.batchproject.jobs.models.address.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
