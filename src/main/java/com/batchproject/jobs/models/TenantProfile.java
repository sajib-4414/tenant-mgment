package com.batchproject.jobs.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tenant_profile")
public class TenantProfile extends BaseEntity{
    private String email;
    private String phoneNo;
    private String notes;//notes like if he has been ever evicted like this.
}
