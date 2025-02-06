package com.batchproject.housingservice.externalservice;

import com.batchproject.housingservice.configs.FeignClientConfig;
import com.batchproject.housingservice.models.BulkIdPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "rent-service", url = "http://localhost:9090", configuration = FeignClientConfig.class)
public interface RentServiceClient {
    @GetMapping("/api/rent-price/get-latest-rent-price/{suiteId}")
    RentPriceExternal getLatestRentPriceBySuite(@PathVariable("suiteId") Long suiteId);

    @PostMapping("/api/rent-price/get-latest-rent-price-bulk")
    List<RentPriceExternal> getLatestRentPriceMultipleSuites(@RequestBody BulkIdPayload payload);

    @PostMapping("/api/rent-price")
    RentPriceExternal setNewRentPrice(@RequestBody RentPriceExternalDTO requestBody);
}
