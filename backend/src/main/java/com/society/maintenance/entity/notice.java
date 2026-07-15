package com.society.maintenance.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name="notices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notice {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable=false)
    private String title;


    @Column(nullable=false,length=2000)
    private String message;


    private LocalDateTime createdAt;


    private LocalDateTime expiryDate;



    @ManyToOne
    @JoinColumn(name="created_by")
    private User createdBy;



    @PrePersist
    public void prePersist(){

        createdAt = LocalDateTime.now();

    }

}