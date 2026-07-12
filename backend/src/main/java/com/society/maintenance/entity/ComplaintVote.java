package com.society.maintenance.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(
    name = "complaint_votes",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"complaint_id", "user_id"}
        )
    }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintVote {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDateTime votedAt;



    // Complaint being voted
    @ManyToOne
    @JoinColumn(name = "complaint_id")
    private Complaint complaint;



    // User who voted
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    @PrePersist
    public void prePersist(){

        votedAt = LocalDateTime.now();

    }

}