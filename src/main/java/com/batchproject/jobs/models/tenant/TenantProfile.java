package com.batchproject.jobs.models.tenant;

import com.batchproject.jobs.models.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tenant_profile")
public class TenantProfile extends BaseEntity {
    @Column(name = "email")
    private String email;
    @Column(name = "phone_no")
    private String phoneNo;
    @Column(name = "notes")
    private String notes;//notes like if he has been ever evicted like this.
}
