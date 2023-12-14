package com.hcmute.alohcmute.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hcmute.alohcmute.enums.Gender;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Profiles")
public class Profile {
    @Id
    @Column(name = "user_ID")
    private Long userId;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_ID")
    @JsonBackReference
    private User user;

    @Column(name = "first_Name")
    private String firstName;

    @Column(name = "last_Name")
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String bio;
}