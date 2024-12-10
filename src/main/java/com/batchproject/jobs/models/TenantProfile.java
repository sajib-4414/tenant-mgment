package com.batchproject.jobs.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TenantProfile {
    private String email;
    private String phoneNo;
    private String notes;//notes like if he has been ever evicted like this.
}
