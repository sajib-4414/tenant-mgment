package com.batchproject.jobs.models;

import com.batchproject.jobs.models.address.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

//for a house, it will be one building, and one associated suite.
@Table(name = "housing_building")
@Entity
public class HousingBuilding extends BaseEntity{
    @Column(name = "name")
    private String name;

//    @OneToMany(fetch = FetchType.EAGER)
//    @JsonIgnore
//    private List<Suite> suites;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Column(name = "has_in_house_laundry")
    private Boolean hasInHouseLaundry;


    @Temporal(TemporalType.DATE)
    @Column(name = "possessed_on")
    private LocalDate possessedOn;

    @Temporal(TemporalType.DATE)
    @Column(name = "built_on")
    private LocalDate builtOn;
}
