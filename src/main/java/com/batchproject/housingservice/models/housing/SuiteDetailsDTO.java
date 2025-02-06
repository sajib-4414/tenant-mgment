package com.batchproject.housingservice.models.housing;


import com.batchproject.housingservice.externalservice.RentPriceExternal;
import com.batchproject.housingservice.models.address.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private RentPriceExternal rent;
}
