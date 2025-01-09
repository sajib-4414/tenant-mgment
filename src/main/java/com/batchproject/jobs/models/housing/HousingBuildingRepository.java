package com.batchproject.jobs.models.housing;

import com.batchproject.jobs.models.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HousingBuildingRepository extends JpaRepository<HousingBuilding, Long> {
    boolean existsByAddress(Address address);
}
