package com.batchproject.jobs.models;

import com.batchproject.jobs.models.address.Address;

import java.time.LocalDate;
import java.util.List;

//for a house, it will be one building, and one associated suite.
public class HousingBuilding {
    private String name;
    private List<Suite> suites;
    private Address address;
    private Boolean hasInHouseLaundry;
    private List<Metadata> metadataList;
    private LocalDate possessedOn;
    private LocalDate builtOn;
}
