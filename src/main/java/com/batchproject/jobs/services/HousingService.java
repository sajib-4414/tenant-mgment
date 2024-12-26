package com.batchproject.jobs.services;

import com.batchproject.jobs.models.housing.HousingBuildingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HousingService {

    private HousingBuildingRepository housingBuildingRepository;

}
