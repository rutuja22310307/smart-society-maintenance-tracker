package com.society.maintenance.repository;


import com.society.maintenance.entity.Complaint;
import com.society.maintenance.entity.ComplaintHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface ComplaintHistoryRepository 
        extends JpaRepository<ComplaintHistory, Long> {


    List<ComplaintHistory> findByComplaint(Complaint complaint);


}