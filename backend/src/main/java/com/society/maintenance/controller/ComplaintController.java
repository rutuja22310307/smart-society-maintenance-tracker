package com.society.maintenance.controller;


import com.society.maintenance.entity.Complaint;
import com.society.maintenance.service.ComplaintService;


import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/complaints")
@CrossOrigin
public class ComplaintController {


    private final ComplaintService complaintService;


    public ComplaintController(
            ComplaintService complaintService
    ){

        this.complaintService = complaintService;

    }



    @PostMapping
    public Complaint createComplaint(
            @RequestBody Complaint complaint
    ){

        return complaintService
                .createComplaint(complaint);

    }



    @GetMapping
    public List<Complaint> getAll(){

        return complaintService
                .getAllComplaints();

    }



    @GetMapping("/{id}")
    public Complaint getComplaint(
            @PathVariable Long id
    ){

        return complaintService
                .getComplaintById(id);

    }



    @PutMapping("/{id}/status")
    public Complaint updateStatus(
            @PathVariable Long id,
            @RequestParam Complaint.Status status
    ){

        return complaintService
                .updateStatus(id,status);

    }



    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id
    ){

        complaintService.deleteComplaint(id);

        return "Complaint deleted";

    }

}