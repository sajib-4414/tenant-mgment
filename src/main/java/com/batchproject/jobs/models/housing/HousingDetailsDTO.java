package com.batchproject.jobs.models.housing;

import com.batchproject.jobs.models.address.Address;
import com.batchproject.jobs.models.maintenance.WorkOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
