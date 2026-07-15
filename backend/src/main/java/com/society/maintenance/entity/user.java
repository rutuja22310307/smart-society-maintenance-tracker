package com.society.maintenance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String name;


    @Column(nullable = false, unique = true)
    private String email;


    @Column(nullable = false)
    private String password;


    private String phone;


    private String apartmentNumber;


    @Enumerated(EnumType.STRING)
    private Role role;


    private LocalDateTime createdAt;



    @PrePersist
    public void prePersist(){

        createdAt = LocalDateTime.now();

        if(role == null){
            role = Role.RESIDENT;
        }
    }



    public enum Role{

        ADMIN,
        RESIDENT,
        STAFF

    }

}