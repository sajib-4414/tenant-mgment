package com.batchproject.jobs.externalservice;

import com.batchproject.jobs.configs.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "rent-service", url = "http://localhost:9090", configuration = FeignClientConfig.class)
public interface RentServiceClient {
    @GetMapping("/api/rent-price/get-latest-rent-price/{suiteId}")
    RentPriceExternal getLatestRentPriceBySuite(@PathVariable("suiteId") Long suiteId);

    @PostMapping("/api/rent-price")
    RentPriceExternal setNewRentPrice(@PathVariable("suiteId") Long suiteId);
}
