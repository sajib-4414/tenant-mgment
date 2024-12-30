package com.batchproject.jobs.models.user;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    public Optional<UserProfile> findByKeycloakUserId(String keyCloakId);
}
