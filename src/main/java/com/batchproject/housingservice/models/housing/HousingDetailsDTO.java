package com.batchproject.housingservice.models.housing;

import com.batchproject.housingservice.models.address.Address;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class HousingDetailsDTO {

    private Integer id;

    private String name;

    private List<SuiteDetailsDTO> suiteList;

    private Address address;

    private Boolean hasInHouseLaundry;

    private LocalDate possessedOn;

    private LocalDate builtOn;

}
