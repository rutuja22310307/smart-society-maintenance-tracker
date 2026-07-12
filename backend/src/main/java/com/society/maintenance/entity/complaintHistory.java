package com.society.maintenance.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "complaint_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintHistory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String oldStatus;


    private String newStatus;



    private String remark;



    private LocalDateTime changedAt;



    // Complaint reference
    @ManyToOne
    @JoinColumn(name = "complaint_id")
    private Complaint complaint;



    // User who changed status
    @ManyToOne
    @JoinColumn(name = "changed_by")
    private User changedBy;



    @PrePersist
    public void prePersist(){

        changedAt = LocalDateTime.now();

    }

}