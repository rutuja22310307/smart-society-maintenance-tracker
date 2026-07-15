package com.society.maintenance.controller;


import com.society.maintenance.dto.ComplaintRequest;
import com.society.maintenance.dto.ComplaintResponse;
import com.society.maintenance.entity.Complaint;
import com.society.maintenance.entity.User;
import com.society.maintenance.service.ComplaintService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {


    private final ComplaintService complaintService;


    public ComplaintController(
            ComplaintService complaintService
    ) {

        this.complaintService = complaintService;

    }



    // ==========================
    // Create Complaint
    // ==========================
    @PostMapping
    public Complaint createComplaint(
            @Valid @RequestBody ComplaintRequest request
    ) {

        return complaintService.createComplaint(request);

    }





    // ==========================
    // Get All Complaints
    // ==========================
    @GetMapping
    public List<ComplaintResponse> getAllComplaints() {

        return complaintService.getAllComplaints();

    }





    // ==========================
    // Get Complaint By ID
    // ==========================
    @GetMapping("/{id}")
    public ComplaintResponse getComplaintById(
            @PathVariable Long id
    ) {

        return complaintService.getComplaintById(id);

    }





    // ==========================
    // Get Complaints By User
    // ==========================
    @PostMapping("/user")
    public List<Complaint> getComplaintsByUser(
            @RequestBody User user
    ) {

        return complaintService.getComplaintsByUser(user);

    }





    // ==========================
    // Get Complaints By Status
    // ==========================
    @GetMapping("/status/{status}")
    public List<Complaint> getComplaintsByStatus(
            @PathVariable Complaint.Status status
    ) {

        return complaintService.getComplaintsByStatus(status);

    }





    // ==========================
    // Update Complaint Status
    // ==========================
    @PutMapping("/{id}/status")
    public Complaint updateComplaintStatus(
            @PathVariable Long id,
            @RequestParam Complaint.Status status
    ) {

        return complaintService.updateStatus(id, status);

    }





    // ==========================
    // Delete Complaint
    // ==========================
    @DeleteMapping("/{id}")
    public String deleteComplaint(
            @PathVariable Long id
    ) {

        complaintService.deleteComplaint(id);

        return "Complaint deleted successfully";

    }

}