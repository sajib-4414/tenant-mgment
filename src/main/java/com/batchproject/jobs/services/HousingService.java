package com.batchproject.jobs.services;

import com.batchproject.jobs.configs.exceptions.customexceptions.BadDataException;
import com.batchproject.jobs.configs.exceptions.customexceptions.ItemNotFoundException;
import com.batchproject.jobs.models.address.Address;
import com.batchproject.jobs.models.address.AddressRepository;
import com.batchproject.jobs.models.housing.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
@AllArgsConstructor
public class HousingService {
    private final PlatformTransactionManager transactionManager;

    private HousingBuildingRepository housingBuildingRepository;
    private AddressService addressService;
    private ModelMapper modelMapper;

    @Async
    public CompletableFuture<List<HousingBuilding>> getAllBuildings() {
        return CompletableFuture.completedFuture(housingBuildingRepository.findAll());
    }

    @Async
    public CompletableFuture<HousingBuilding> getBuildingById(Long id) {
        return CompletableFuture.completedFuture(housingBuildingRepository.findById(id).orElseThrow(()->new ItemNotFoundException("Housing building not found")));
    }

    @Async
    @Transactional
    public CompletableFuture<HousingBuilding> createBuilding(HousingDTO payload) {
        TransactionTemplate template = new TransactionTemplate(transactionManager);
        return template.execute(status -> {
            //check if there is already a housing building there, if yes, throw error as one address cnnot have more than one building
            Address addressObject = addressService.createAddressObject(payload.getAddress());
            if(addressObject.getId() !=null){
                Boolean isHouseExists = housingBuildingRepository.existsByAddress(addressObject);
                if(isHouseExists)
                    throw new BadDataException("There is already a house there, change the address");
            }
            else{
                //for apartments we will keep the building's addresses' apartment No 0, and ishosue=false
                //whatever we pass will be ignored.
                if(!addressObject.getIsHouse())
                    addressObject.setApartmentNo(0);
            }
            HousingBuilding newBuilding = HousingBuilding.builder()
                    .name(payload.getName())
                    .address(addressObject)//this returns the db fetched address if exists otherwise just object as it is with some processing
                    .hasInHouseLaundry(payload.getHasInHouseLaundry())
                    .possessedOn(payload.getPossessedOn())
                    .builtOn(payload.getBuiltOn())
                    .build();
//            housingBuildingRepository.flush();

            return CompletableFuture.completedFuture(housingBuildingRepository.save(newBuilding));
        });

    }


    @Async
    @Transactional
    public CompletableFuture<HousingBuilding> updateBuilding(Long id, HousingDTO payload){
        HousingBuilding building =  housingBuildingRepository.findById(id)
                .map(housingBuilding ->{
                            housingBuilding.setName(payload.getName());
                            //address will be still sent by UI, but we will ignore
                            //for address update, we will use the address update endpoint.
                            housingBuilding.setHasInHouseLaundry(payload.getHasInHouseLaundry());
                            housingBuilding.setPossessedOn(payload.getPossessedOn());
                            housingBuilding.setBuiltOn(payload.getBuiltOn());
                            return housingBuildingRepository.save(housingBuilding);
                        })
                .orElseThrow(()-> new ItemNotFoundException("Building not found with id " + id));
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


    @Async
    public CompletableFuture<HousingDetailsDTO> getFullBuildingDetails(Long buildingId) {

        //first building
        HousingBuilding housingBuilding = housingBuildingRepository.findById(buildingId).orElseThrow(()->new ItemNotFoundException("Building not found with ID"));
        HousingDetailsDTO output = new HousingDetailsDTO();
        modelMapper.map(housingBuilding,output);
        //then suites
        //lets get the suite list
        List<Suite> suiteList = housingBuilding.getSuites();
        List<SuiteDetailsDTO> suiteDetailsDTOList = suiteList.stream()
                .map(suite -> {

                })
    }
}
