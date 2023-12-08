package com.hcmute.alohcmute.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcmute.alohcmute.enums.FriendshipStatus;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Friendships")
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friendship_ID")
    private Long friendshipId;

    @ManyToOne
    @JoinColumn(name = "user_ID1", nullable = false)
    @JsonIgnore
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user_ID2", nullable = false)
    @JsonIgnore
    private User user2;

    @Enumerated(EnumType.STRING)
    private FriendshipStatus status;

    @Column(name = "created_At")
    private LocalDateTime createdAt;
}
