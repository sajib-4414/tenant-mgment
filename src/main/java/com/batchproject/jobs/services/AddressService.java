package com.batchproject.jobs.services;

import com.batchproject.jobs.models.Address;
import com.batchproject.jobs.models.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }
}
