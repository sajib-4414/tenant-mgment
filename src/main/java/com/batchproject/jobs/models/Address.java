package com.batchproject.jobs.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
@Entity
public class Address extends BaseEntity{

    @Column(name = "apartment_no")
    private Integer apartmentNo;

    @Column(name = "street_no")
    private Integer streetNo;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "post_code")
    private String postCode;

    @Column(name = "province")
    private String province;

    @Column(name = "is_house")
    private Boolean isHouse;
}
