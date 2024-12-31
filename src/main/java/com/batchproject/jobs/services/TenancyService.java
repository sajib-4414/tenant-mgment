package com.batchproject.jobs.services;

import com.batchproject.jobs.models.housing.Suite;
import com.batchproject.jobs.models.housing.SuiteRepository;
import com.batchproject.jobs.models.tenant.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class TenancyService {

    private final TenancyRepository tenancyRepository;
    private final SuiteRepository suiteRepository;
    private final TenantProfileRepository tenantProfileRepository;



    @Async
    public CompletableFuture<List<Tenancy>> getAllTenancies() {
        return CompletableFuture.completedFuture(
                tenancyRepository.findAll()

        );
    }

    @Async
    public CompletableFuture<Tenancy> createTenancy(TenancyDTO tenancyDTO) {
        Suite suite = suiteRepository.findById(tenancyDTO.getSuiteId())
                .orElseThrow(() -> new EntityNotFoundException("Suite not found with id: " + tenancyDTO.getSuiteId()));
        TenantProfile tenantProfile = tenantProfileRepository.findById(tenancyDTO.getTenantProfileId())
                .orElseThrow(() -> new EntityNotFoundException("TenantProfile not found with id: " + tenancyDTO.getTenantProfileId()));

        Tenancy tenancy = new Tenancy();
        tenancy.setSuite(suite);
        tenancy.setTenantProfile(tenantProfile);
        tenancy.setStartDate(tenancyDTO.getStartDate());
        tenancy.setEndDate(tenancyDTO.getEndDate());

        return CompletableFuture.completedFuture(tenancyRepository.save(tenancy));
    }

    @Async
    public CompletableFuture<Tenancy> getTenancyById(Long id) {
        return CompletableFuture.completedFuture(
                tenancyRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Tenancy not found with id: " + id))
        );
    }

    @Async
    public CompletableFuture<Tenancy> updateTenancy(Long id, TenancyDTO tenancyDTO) {
        Tenancy existingTenancy = tenancyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tenancy not found with id: " + id));

        Suite suite = suiteRepository.findById(tenancyDTO.getSuiteId())
                .orElseThrow(() -> new EntityNotFoundException("Suite not found with id: " + tenancyDTO.getSuiteId()));
        TenantProfile tenantProfile = tenantProfileRepository.findById(tenancyDTO.getTenantProfileId())
                .orElseThrow(() -> new EntityNotFoundException("TenantProfile not found with id: " + tenancyDTO.getTenantProfileId()));

        existingTenancy.setSuite(suite);
        existingTenancy.setTenantProfile(tenantProfile);
        existingTenancy.setStartDate(tenancyDTO.getStartDate());
        existingTenancy.setEndDate(tenancyDTO.getEndDate());

        return CompletableFuture.completedFuture(tenancyRepository.save(existingTenancy));
    }

    @Async
    public CompletableFuture<Void> deleteTenancy(Long id) {
        tenancyRepository.deleteById(id);
        return CompletableFuture.completedFuture(null);
    }
}
