package com.batchproject.jobs.services;

import com.batchproject.jobs.models.address.Address;
import com.batchproject.jobs.models.address.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class AddressService {


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
    @Transactional
    public CompletableFuture<Address> saveAddress(Address address) {
        return CompletableFuture.completedFuture(addressRepository.save(address));
    }
//

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

}
