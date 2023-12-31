package com.hcmute.alohcmute.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    
    @Column(name = "is_admin")
    private String isAdmin = "ROLE_USER";

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


    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Like> likes;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Profile profile;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Image> images;
}
