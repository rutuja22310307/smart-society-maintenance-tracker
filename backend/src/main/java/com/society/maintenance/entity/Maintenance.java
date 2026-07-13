package com.society.maintenance.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "maintenance")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Maintenance {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private Double amount;


    @Column(nullable = false)
    private String month;


    @Column(nullable = false)
    private Integer year;


    @Enumerated(EnumType.STRING)
    private PaymentStatus status;


    private LocalDateTime createdAt;



    // Resident who needs to pay
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    @PrePersist
    public void prePersist(){

        createdAt = LocalDateTime.now();

        if(status == null){
            status = PaymentStatus.PENDING;
        }

    }



    public enum PaymentStatus{

        PENDING,
        PAID

    }

}