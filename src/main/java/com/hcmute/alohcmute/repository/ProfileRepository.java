package com.hcmute.alohcmute.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hcmute.alohcmute.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query("Select p.userId, p.firstname, p.lastname from Profile p")
    List<Profile> findNameOfUser();
}
