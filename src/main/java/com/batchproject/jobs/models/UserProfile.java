package com.batchproject.jobs.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//this is for
// admininistrave/client center person
// HR/official person
// Maintenance guy person
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    private String userType;
}
