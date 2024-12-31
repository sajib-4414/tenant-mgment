package com.batchproject.jobs.models.tenant;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TenancyRepository extends JpaRepository<Tenancy,Long> {
}
