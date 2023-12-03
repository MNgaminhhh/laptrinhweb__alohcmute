package com.hcmute.alohcmute.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcmute.alohcmute.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
    // Các truy vấn cụ thể có thể được thêm ở đây nếu cần
}
