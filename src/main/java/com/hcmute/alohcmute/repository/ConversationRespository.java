package com.hcmute.alohcmute.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hcmute.alohcmute.entity.Conversation;

public interface ConversationRespository extends JpaRepository<Conversation, Long> {
    @Query("SELECT c FROM Conversation c WHERE (c.member1.userId=:user1 AND c.member2.userId=:user2) OR (c.member1.userId=:user2 AND c.member2.userId=:user1)")
    public Optional<Conversation> findConversationsOf(@Param("user1") Long user1, @Param("user2") Long user2);
}
