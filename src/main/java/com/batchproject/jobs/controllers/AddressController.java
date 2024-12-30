package com.batchproject.jobs.controllers;

import com.batchproject.jobs.models.address.Address;
import com.batchproject.jobs.services.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/addresses")
@AllArgsConstructor
public class AddressController {

    private AddressService addressService;

    @PostMapping
    public CompletableFuture<ResponseEntity<Address>> createAddress(@RequestBody Address address) {
        CompletableFuture<ResponseEntity<Address>> future =
                addressService.saveAddress(address)
                        .thenApply(result->{
                            return ResponseEntity.ok(result);
                        })
                        .exceptionally(ex->{
                            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                        });
        return future;
    }
}
