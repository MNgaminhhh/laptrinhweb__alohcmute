package com.hcmute.alohcmute.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_ID")
    private Long userId;

    private String username;

    @Column(unique = true)
    private String email;

    private String password;
    @Column(name = "is_admin", columnDefinition = "String DEFAULT 'USER'")
    private String isAdmin;
    @Column(name = "created_At")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Comment> comments;

    @OneToMany(mappedBy = "user1")
    @JsonIgnore
    private List<Friendship> friendshipsSent;

    @OneToMany(mappedBy = "user2")
    @JsonIgnore
    private List<Friendship> friendshipsReceived;

    @OneToMany(mappedBy = "sender")
    @JsonIgnore
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "receiver")
    @JsonIgnore
    private List<Message> receivedMessages;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Like> likes;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Profile profile;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Image> images;
}
