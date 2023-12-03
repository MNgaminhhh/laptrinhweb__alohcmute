package com.hcmute.alohcmute.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcmute.alohcmute.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
}
