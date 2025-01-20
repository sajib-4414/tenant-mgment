package com.batchproject.jobs.externalservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "rent-service", url = "http://localhost:9090")
public interface RentServiceClient {
    @GetMapping("/api/rent-price/get-latest-rent-price/{suiteId}")
    RentPriceExternal getLatestRentPriceBySuite(@PathVariable("suiteId") Long suiteId);
}
