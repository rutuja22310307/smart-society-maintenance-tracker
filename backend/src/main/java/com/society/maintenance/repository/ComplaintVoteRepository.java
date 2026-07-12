package com.society.maintenance.repository;


import com.society.maintenance.entity.Complaint;
import com.society.maintenance.entity.ComplaintVote;
import com.society.maintenance.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface ComplaintVoteRepository 
        extends JpaRepository<ComplaintVote, Long> {


    List<ComplaintVote> findByComplaint(
            Complaint complaint
    );


    boolean existsByComplaintAndUser(
            Complaint complaint,
            User user
    );


}