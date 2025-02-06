package com.batchproject.housingservice.models.housing;

import com.batchproject.housingservice.models.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HousingBuildingRepository extends JpaRepository<HousingBuilding, Long> {
    boolean existsByAddress(Address address);
}
