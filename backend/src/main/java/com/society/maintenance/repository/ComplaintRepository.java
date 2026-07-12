package com.society.maintenance.repository;


import com.society.maintenance.entity.Complaint;
import com.society.maintenance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {


    // Find complaints by status
    List<Complaint> findByStatus(Complaint.Status status);


    // Find complaints created by a user
    List<Complaint> findByCreatedBy(User user);


    // Find complaints by category
    List<Complaint> findByCategory(String category);


}