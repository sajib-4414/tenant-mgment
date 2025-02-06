package com.batchproject.housingservice.services;

import com.batchproject.housingservice.models.address.Address;
import com.batchproject.housingservice.models.address.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class AddressService {
    private final PlatformTransactionManager transactionManager;


    private final AddressRepository addressRepository;

//    public Address createAddress(Address address) {
//        return addressRepository.save(address);
//    }

    @Async
    public CompletableFuture<List<Address>> getAllAddresses() {
        return  CompletableFuture.completedFuture(addressRepository.findAll());
    }

    @Async
    public CompletableFuture<Address> getAddressById(Long id) {
        return  CompletableFuture.completedFuture(addressRepository.findById(id).orElseThrow(()->new RuntimeException("address could not be found")));
    }

    @Async
    public CompletableFuture<Address> createAddress(Address payload) {
        TransactionTemplate template = new TransactionTemplate(transactionManager);
        return template.execute(status -> {
            Address newAddress = createAddressObject(payload);

            if (newAddress.getId() ==null)
                addressRepository.save(newAddress);
            return CompletableFuture.completedFuture(newAddress);
        });


    }

    public Address createAddressObject(Address payload){
        payload.setId(null);
        payload.setStreetName(payload.getStreetName().toLowerCase());
        payload.setProvince(payload.getProvince().toUpperCase());
        payload.setPostCode(payload.getPostCode().toUpperCase());
        List<String> addressParts = Arrays.stream(payload.getPostCode().split(" ")).toList();
        if(addressParts.size()!=2 || addressParts.get(0).length()!=3 ||addressParts.get(1).length()!=3  )
            throw new RuntimeException("address invalid postcode");
        Address existingSameAddress = doesExist(payload);
        if(existingSameAddress !=null)
            return existingSameAddress;
        return payload;//it will do the new address creation
    }

    @Async
    @Transactional
    public CompletableFuture<Address> updateAddress(Long addressId, Address payload) {


        try{
            Address dbAddress = addressRepository.findById(addressId).orElseThrow(()->new RuntimeException("address not found"));
            dbAddress.setStreetName(payload.getStreetName()!=null? payload.getStreetName().toLowerCase():dbAddress.getStreetName());
            dbAddress.setProvince(payload.getProvince()!=null? payload.getProvince().toUpperCase():dbAddress.getProvince());
            dbAddress.setStreetNo(payload.getStreetNo()!=null? payload.getStreetNo():dbAddress.getStreetNo());
            dbAddress.setApartmentNo(payload.getApartmentNo()!=null? payload.getApartmentNo():dbAddress.getApartmentNo());
            dbAddress.setIsHouse(payload.getIsHouse()!=null? payload.getIsHouse():dbAddress.getIsHouse());
            dbAddress.setPostCode(payload.getPostCode()!=null? payload.getPostCode().toUpperCase():dbAddress.getPostCode());
            List<String> addressParts = Arrays.stream(payload.getPostCode().split(" ")).toList();
            if(addressParts.size()!=2 || addressParts.get(0).length()!=3 ||addressParts.get(1).length()!=3  )
                throw new RuntimeException("address invalid postcode");
            addressRepository.save(dbAddress);
//            return dbAddress;
            return CompletableFuture.completedFuture(dbAddress);
        }catch (Exception exception){
            System.out.println("error in updating....."+exception);
            throw exception;
        }

    }


    @Async
    @Transactional
    public CompletableFuture<Void> deleteAddress(Long id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return CompletableFuture.completedFuture(null);
        } else {
            throw new RuntimeException("address not found with id " + id);
        }
    }

    public Address doesExist(Address newAddress) {
        try{
            String newAddressHash = newAddress.generateHash();
            Address existingAddress = addressRepository.findByAddressHash(newAddressHash);
            return existingAddress;
        } catch (Exception e) {
            System.out.println("exception actually here");
            throw new RuntimeException(e);
        }

    }

}
