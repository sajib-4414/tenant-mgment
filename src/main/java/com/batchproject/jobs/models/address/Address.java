package com.batchproject.jobs.models.address;

import com.batchproject.jobs.configs.exceptions.customexceptions.BadDataException;
import com.batchproject.jobs.models.BaseEntity;
import com.batchproject.jobs.models.housing.HousingBuilding;
import com.batchproject.jobs.models.housing.Suite;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
@Entity
public class Address extends BaseEntity implements Cloneable{

    @Nullable
    public Integer getApartmentNo() {
        return apartmentNo;
    }

    public void setApartmentNo(@Nullable Integer apartmentNo) {
        this.apartmentNo = apartmentNo;
    }

    @Column(name = "apartment_no", nullable = true)
    @Nullable
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

    @JsonIgnore
    @Column(name = "address_hash", unique = true)
    private String addressHash;

    @OneToMany(mappedBy = "address")
    @JsonIgnore
    private List<HousingBuilding> buildings;

    @OneToMany(mappedBy = "address")
    @JsonIgnore
    private List<Suite> suites;

    @PrePersist
    @PreUpdate
    public void generateAndSetHash() {
        this.addressHash = generateHash();

        //also check if the address is properly marked as apartment
        if (this.apartmentNo!=null && isHouse)
            throw new BadDataException("House cannot have apartment No");
        if (this.apartmentNo==null && !isHouse)
            throw new BadDataException("Apartment must have house No");
    }



    public String generateHash() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String input = (apartmentNo!=null ? apartmentNo:"") + (streetNo + streetName + postCode + province + isHouse);
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
    private String bytesToHex(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        System.out.println("populated hash is....."+formatter.toString());
        return formatter.toString();
    }

//    public Integer getApartmentNo() {
//        return 2;
//    }
//    public void setApartmentNo(Integer apartmentNo){
//        this.apartmentNo = apartmentNo;
//    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // Returning a clone of the current object
        return super.clone();
    }
}
