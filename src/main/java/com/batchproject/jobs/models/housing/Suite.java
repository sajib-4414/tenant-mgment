package com.batchproject.jobs.models.housing;

import com.batchproject.jobs.models.BaseEntity;
import com.batchproject.jobs.models.address.Address;
import com.batchproject.jobs.models.maintenance.WorkOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "suite")
@Entity
public class Suite extends BaseEntity {
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "fk_address"))
    private Address address;

    @Column(name = "built_on")
    private LocalDate builtOn;

    @Column(name = "last_renovated_on")
    private LocalDate lastRenovatedOn;

    @Column(name = "no_of_bedrooms")
    private Integer noOfBedRooms;

    @Column(name = "no_of_bathrooms")
    private Integer noOfBathRooms;

    @Column(name = "have_dedicated_laundry")
    private Boolean haveDedicatedLaundry;

    @Column(name = "floor_no")
    private Integer floorNo;

    //even if its a house, it will be attached to a building
    @ManyToOne
    @JoinColumn(name = "building_id", foreignKey = @ForeignKey(name = "fk_building"))
    private HousingBuilding building;

    @OneToMany(mappedBy = "suite")
    @JsonIgnore
    private List<WorkOrder> workOrderList;


}
