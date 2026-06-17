package com.example.idcard_management.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String registrationNumber;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private LocalDate dateOfBirth;

    private String department;

    @Column(columnDefinition = "LONGTEXT")
    private String photoPath;

    @Column(columnDefinition = "LONGTEXT")
    private String qrCodePath;

    @Column(columnDefinition = "LONGTEXT")
    private String barcodePath;

    @Enumerated(EnumType.STRING)
    private ProfileType profileType;
}
