package com.hcmute.alohcmute.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcmute.alohcmute.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    Optional<User> findById(Long userId);
    Optional<User> findByEmail(String email);  
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    void deleteById(Long userId);

}
