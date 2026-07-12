package com.society.maintenance.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String title;


    @Column(nullable = false, length = 1000)
    private String message;


    private boolean isRead;


    private LocalDateTime createdAt;



    // User who receives notification
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    @PrePersist
    public void prePersist(){

        createdAt = LocalDateTime.now();
        isRead = false;

    }

}