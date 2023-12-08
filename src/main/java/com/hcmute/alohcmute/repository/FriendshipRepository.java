package com.hcmute.alohcmute.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.api.services.storage.Storage.BucketAccessControls.List;
import com.hcmute.alohcmute.entity.Friendship;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    List<Friendship> findAll();
}
