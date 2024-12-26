package com.batchproject.jobs.models;

import jakarta.persistence.Column;
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
public class UserProfile extends BaseEntity{
    @Column(name = "keycloak_user_id", unique = true, nullable = false)
    private String keycloakUserId; // This will link to the Keycloak user's ID

    @Column(name = "first_name")
    private String fullName;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "email")
    private String email;

}
