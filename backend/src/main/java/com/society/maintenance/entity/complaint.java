package com.society.maintenance.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name="complaints")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Complaint {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String title;


    @Column(nullable = false,length = 1000)
    private String description;


    private String category;


    @Enumerated(EnumType.STRING)
    private Status status;


    private String priority;


    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;



    @ManyToOne
    @JoinColumn(name="user_id")
    private User createdBy;



    @PrePersist
    public void prePersist(){

        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();

        if(status == null){
            status = Status.PENDING;
        }

    }



    @PreUpdate
    public void preUpdate(){

        updatedAt = LocalDateTime.now();

    }



    public enum Status{

        PENDING,
        IN_PROGRESS,
        RESOLVED,
        REJECTED

    }

}