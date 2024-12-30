package com.batchproject.jobs.services;

import com.batchproject.jobs.models.address.Address;
import com.batchproject.jobs.models.address.AddressRepository;
import com.batchproject.jobs.models.housing.HousingBuilding;
import com.batchproject.jobs.models.housing.HousingBuildingRepository;
import com.batchproject.jobs.models.housing.HousingDTO;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class HousingService {

    private HousingBuildingRepository housingBuildingRepository;
    private AddressRepository addressRepository;

    @Async
    public CompletableFuture<List<HousingBuilding>> getAllBuildings() {
        return CompletableFuture.completedFuture(housingBuildingRepository.findAll());
    }

    @Async
    public CompletableFuture<Optional<HousingBuilding>> getBuildingById(Long id) {
        return CompletableFuture.completedFuture(housingBuildingRepository.findById(id));
    }

    @Async
    @Transactional
    public CompletableFuture<HousingBuilding> saveBuilding(HousingDTO payload) {
        HousingBuilding newBuilding = HousingBuilding.builder()
                .name(payload.getName())
                .address(addressRepository.findById(payload.getAddress_id())
                        .orElseThrow(() -> new RuntimeException("Address not found with id " + payload.getAddress_id())))
                .hasInHouseLaundry(payload.getHasInHouseLaundry())
                .possessedOn(payload.getPossessedOn())
                .builtOn(payload.getBuiltOn())
                .build();

        return CompletableFuture.completedFuture(housingBuildingRepository.save(newBuilding));
    }


    @Async
    @Transactional
    public CompletableFuture<HousingBuilding> updateBuilding(Long id, HousingDTO payload){
        HousingBuilding building =  housingBuildingRepository.findById(id)
                .map(housingBuilding ->{
                            housingBuilding.setName(payload.getName());
                            Address address = addressRepository.findById(payload.getAddress_id()).orElseThrow(()-> new RuntimeException("address not found with id " + id));
                            housingBuilding.setAddress(address);
                            housingBuilding.setHasInHouseLaundry(payload.getHasInHouseLaundry());
                            housingBuilding.setPossessedOn(payload.getPossessedOn());
                            housingBuilding.setBuiltOn(payload.getBuiltOn());
                            return housingBuildingRepository.save(housingBuilding);
                        })
                .orElseThrow(()-> new RuntimeException("Building not found with id " + id));
        return CompletableFuture.completedFuture(building);
    }

    @Async
    @Transactional
    public CompletableFuture<Void> deleteBuilding(Long id) {
        if (housingBuildingRepository.existsById(id)) {
            housingBuildingRepository.deleteById(id);
            return CompletableFuture.completedFuture(null);
        } else {
            throw new RuntimeException("Building not found with id " + id);
        }
    }


}
