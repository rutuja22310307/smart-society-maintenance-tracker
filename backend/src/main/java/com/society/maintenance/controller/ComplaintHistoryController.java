package com.society.maintenance.controller;


import com.society.maintenance.entity.Complaint;
import com.society.maintenance.entity.ComplaintHistory;

import com.society.maintenance.service.ComplaintHistoryService;


import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/history")
@CrossOrigin
public class ComplaintHistoryController {


    private final ComplaintHistoryService historyService;


    public ComplaintHistoryController(
            ComplaintHistoryService historyService
    ){

        this.historyService=historyService;

    }



    @GetMapping("/complaint/{id}")
    public List<ComplaintHistory> getHistory(
            @PathVariable Long id
    ){

        Complaint complaint =
                new Complaint();

        complaint.setId(id);


        return historyService
                .getComplaintHistory(complaint);

    }

}