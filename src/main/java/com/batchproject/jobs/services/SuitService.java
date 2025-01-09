package com.batchproject.jobs.services;

import com.batchproject.jobs.configs.exceptions.customexceptions.BadDataException;
import com.batchproject.jobs.configs.exceptions.customexceptions.ItemNotFoundException;
import com.batchproject.jobs.models.address.Address;
import com.batchproject.jobs.models.address.AddressRepository;
import com.batchproject.jobs.models.housing.*;
import com.batchproject.jobs.models.rent.RentPriceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class SuitService {
    private final SuiteRepository suiteRepository;
    private final HousingBuildingRepository housingBuildingRepository;
    private final RentPriceRepository rentPriceRepository;
    private final AddressRepository addressRepository;
    private ModelMapper modelMapper;

    @Async
    public CompletableFuture<List<Suite>> getAllSuites() {
        return CompletableFuture.completedFuture(suiteRepository.findAll());
    }

    @Async
    public CompletableFuture<SuiteOutputDTO> getSuiteDetails(Long id)  {
        Suite suite = suiteRepository.findById(id).orElseThrow(()->new ItemNotFoundException("suite was not found"));
        SuiteOutputDTO outputDTO = modelMapper.map(suite, SuiteOutputDTO.class);
        outputDTO.setRent(rentPriceRepository.getLatestRentPrice(id));
        return CompletableFuture.completedFuture(outputDTO);
    }

    @Async
    @Transactional
    public CompletableFuture<Suite> createSuite(SuiteDTO payload) throws CloneNotSupportedException {
        // Fetch associated entities by IDs

        HousingBuilding building = housingBuildingRepository.findById(payload.getBuildingId())
                .orElseThrow(() -> new BadDataException("Building not found with id " + payload.getBuildingId()));

        //also see if the suite exists already

        Address buildingAddress = building.getAddress();

        Address suiteAddress;
        if (!buildingAddress.getIsHouse()){
            if(payload.getApartmentNo() == null)
                throw new BadDataException("apartment no is not provided");
            suiteAddress = Address
                    .builder()
                    .isHouse(false)
                    .province(buildingAddress.getProvince())
                    .postCode(buildingAddress.getPostCode())
                    .streetName(buildingAddress.getStreetName())
                    .streetNo(buildingAddress.getStreetNo())
                    .apartmentNo(payload.getApartmentNo())
                    .build();
            Address existingAddress = addressRepository.findByAddressHash(suiteAddress.getAddressHash());
            if(existingAddress!=null)
                suiteAddress.setId(existingAddress.getId());//it will not recreate now
        }
        else{
            if(payload.getApartmentNo() != null)
                throw new BadDataException("apartment no cannot be added to house");
            suiteAddress = buildingAddress;
        }

        // Create Suite entity and populate fields
        Suite suite = new Suite();
        suite.setAddress(suiteAddress);
        suite.setBuiltOn(payload.getBuiltOn());
        suite.setLastRenovatedOn(payload.getLastRenovatedOn());
        suite.setNoOfBedRooms(payload.getNoOfBedRooms());
        suite.setNoOfBathRooms(payload.getNoOfBathRooms());
        suite.setHaveDedicatedLaundry(payload.getHaveDedicatedLaundry());
        suite.setFloorNo(payload.getFloorNo());
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
