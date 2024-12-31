package com.batchproject.jobs.controllers;

import com.batchproject.jobs.models.address.Address;
import com.batchproject.jobs.services.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PutMapping
    public CompletableFuture<ResponseEntity<Address>> updateAddress(@PathVariable Long addressId, @RequestBody Address address) {
        address.setId(addressId);
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

    @PutMapping
    public CompletableFuture<ResponseEntity<Void>> deleteAddress(@PathVariable Long addressId) {

        CompletableFuture<ResponseEntity<Void>> future =
                addressService.deleteAddress(addressId)
                        .thenApply(unused->{
                            return ResponseEntity.status(HttpStatus.NO_CONTENT).<Void>build();
                        })
                        .exceptionally(ex->{
                            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                        });
        return future;
    }

}
