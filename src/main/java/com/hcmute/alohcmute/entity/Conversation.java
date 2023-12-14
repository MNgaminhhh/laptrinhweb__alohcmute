package com.hcmute.alohcmute.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Conversations")
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conversation_ID")
    private Long conversationId;

    @ManyToOne
    @JoinColumn(name = "mem1_ID", nullable = false)
    private User member1;

    @ManyToOne
    @JoinColumn(name = "mem2_ID", nullable = false)
    private User member2;

    @OneToMany(mappedBy = "conversation")
    private List<Message> messages;
}
