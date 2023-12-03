package com.hcmute.alohcmute.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_ID")
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "sender_ID", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_ID", nullable = false)
    private User receiver;

    private String content;

    @Column(name = "created_At")
    private LocalDateTime createdAt;
}
