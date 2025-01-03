package com.batchproject.jobs.models.rent;

import com.batchproject.jobs.models.housing.Suite;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@Entity(name = "rent_price")
public class RentPrice {

    @Column(name = "effective_start_date")
    private LocalDate effectiveStartDate;

    @Column(name = "effective_end_date")
    private LocalDate effectiveEndDate;

    @Column(name = "rent_amount")
    private Double rentAmt;

    @JoinColumn(name = "suite_id")
    @ManyToOne
    private Suite suite;
}
