package com.batchproject.jobs.models.rent;

import com.batchproject.jobs.models.housing.Suite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentPriceRepository extends JpaRepository<RentPrice, Long> {
    List<RentPrice> findAllBySuite_Id(Long suiteId);
}
