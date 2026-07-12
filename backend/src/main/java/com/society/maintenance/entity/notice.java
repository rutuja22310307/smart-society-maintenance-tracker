package com.society.maintenance.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "notices")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String title;


    @Column(nullable = false, length = 2000)
    private String message;


    private LocalDateTime createdAt;


    private LocalDateTime expiryDate;



    // Admin who created notice
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;



    @PrePersist
    public void prePersist(){

        createdAt = LocalDateTime.now();

    }

}