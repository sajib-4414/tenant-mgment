package com.batchproject.jobs.models.housing;

import com.batchproject.jobs.models.address.Address;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HousingDTO {
    @NotNull(message = "name cannot be left empty")
    private String name;
    @NotNull(message = "hasInHouseLaundry cannot be left empty")
    private Boolean hasInHouseLaundry;
    @NotNull(message = "possessedOn cannot be left empty")
    private LocalDate possessedOn;
    @NotNull(message = "possessedOn cannot be left empty")
    private LocalDate builtOn;
    @NotNull(message = "address cannot be left empty")
    private Address address;
}
