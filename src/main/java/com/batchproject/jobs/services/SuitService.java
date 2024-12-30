package com.batchproject.jobs.services;

import com.batchproject.jobs.models.address.Address;
import com.batchproject.jobs.models.address.AddressRepository;
import com.batchproject.jobs.models.housing.*;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class SuitService {
    private final SuiteRepository suiteRepository;
    private final AddressRepository addressRepository;
    private final HousingBuildingRepository housingBuildingRepository;

    @Async
    public CompletableFuture<List<Suite>> getAllSuites() {
        return CompletableFuture.completedFuture(suiteRepository.findAll());
    }

    @Async
    public CompletableFuture<Suite> getSuiteById(Long id)  {
        return CompletableFuture.completedFuture(suiteRepository.findById(id).orElseThrow(()->new RuntimeException("suite was not found")));
    }

    @Async
    @Transactional
    public CompletableFuture<Suite> saveSuite(SuiteDTO suiteDTO) {
        // Fetch associated entities by IDs
        Address address = addressRepository.findById(suiteDTO.getAddressId())
                .orElseThrow(() -> new RuntimeException("Address not found with id " + suiteDTO.getAddressId()));
        HousingBuilding building = housingBuildingRepository.findById(suiteDTO.getBuildingId())
                .orElseThrow(() -> new RuntimeException("Building not found with id " + suiteDTO.getBuildingId()));

        // Create Suite entity and populate fields
        Suite suite = new Suite();
        suite.setAddress(address);
        suite.setBuiltOn(suiteDTO.getBuiltOn());
        suite.setLastRenovatedOn(suiteDTO.getLastRenovatedOn());
        suite.setNoOfBedRooms(suiteDTO.getNoOfBedRooms());
        suite.setNoOfBathRooms(suiteDTO.getNoOfBathRooms());
        suite.setHaveDedicatedLaundry(suiteDTO.getHaveDedicatedLaundry());
        suite.setFloorNo(suiteDTO.getFloorNo());
        suite.setBuilding(building);

        // Save and return
        return CompletableFuture.completedFuture(suiteRepository.save(suite));
    }

    @Async
    @Transactional
    public CompletableFuture<Void> deleteSuite(Long id) {
        if (suiteRepository.existsById(id)) {
            suiteRepository.deleteById(id);
            return CompletableFuture.completedFuture(null);
        } else {
            throw new RuntimeException("Suite not found with id " + id);
        }
    }



}
