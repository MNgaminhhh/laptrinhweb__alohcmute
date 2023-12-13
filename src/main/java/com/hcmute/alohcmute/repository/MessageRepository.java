package com.hcmute.alohcmute.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hcmute.alohcmute.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
    // Các truy vấn cụ thể có thể được thêm ở đây nếu cần
    // @Query("SELECT m FROM Message m WHERE sender.userId = :senderId AND receiver.userId = :receiverId")
    // public List<Message> findMessage(@Param ("senderId") Long senderId, @Param("receiverId") Long receiverId);
}
