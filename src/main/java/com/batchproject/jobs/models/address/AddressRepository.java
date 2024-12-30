package com.batchproject.jobs.models.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
