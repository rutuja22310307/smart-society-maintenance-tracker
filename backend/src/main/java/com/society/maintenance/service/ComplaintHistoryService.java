package com.society.maintenance.service;


import com.society.maintenance.entity.Complaint;
import com.society.maintenance.entity.ComplaintHistory;
import com.society.maintenance.entity.User;

import com.society.maintenance.repository.ComplaintHistoryRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ComplaintHistoryService {


    private final ComplaintHistoryRepository historyRepository;


    public ComplaintHistoryService(
            ComplaintHistoryRepository historyRepository
    ) {

        this.historyRepository = historyRepository;

    }



    // Create history record
    public ComplaintHistory createHistory(
            Complaint complaint,
            String oldStatus,
            String newStatus,
            String remark,
            User changedBy
    ){


        ComplaintHistory history = new ComplaintHistory();


        history.setComplaint(complaint);

        history.setOldStatus(oldStatus);

        history.setNewStatus(newStatus);

        history.setRemark(remark);

        history.setChangedBy(changedBy);



        return historyRepository.save(history);

    }




    // Get complete history of a complaint
    public List<ComplaintHistory> getComplaintHistory(
            Complaint complaint
    ){

        return historyRepository.findByComplaint(complaint);

    }




    // Get history by ID
    public ComplaintHistory getHistoryById(Long id){


        return historyRepository.findById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                        "History record not found"
                    )
                );

    }



    // Delete history record
    public void deleteHistory(Long id){

        ComplaintHistory history = getHistoryById(id);

        historyRepository.delete(history);

    }


}