package com.society.maintenance.service;


import com.society.maintenance.entity.Complaint;
import com.society.maintenance.entity.User;
import com.society.maintenance.repository.ComplaintRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ComplaintService {


    private final ComplaintRepository complaintRepository;


    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }



    // Create Complaint
    public Complaint createComplaint(Complaint complaint) {

        return complaintRepository.save(complaint);

    }



    // Get all complaints
    public List<Complaint> getAllComplaints(){

        return complaintRepository.findAll();

    }



    // Get complaint by ID
    public Complaint getComplaintById(Long id){

        return complaintRepository.findById(id)
                .orElseThrow(
                    () -> new RuntimeException("Complaint not found")
                );

    }



    // Get complaints created by user
    public List<Complaint> getComplaintsByUser(User user){

        return complaintRepository.findByCreatedBy(user);

    }



    // Get complaints by status
    public List<Complaint> getComplaintsByStatus(
            Complaint.Status status
    ){

        return complaintRepository.findByStatus(status);

    }



    // Update complaint status
    public Complaint updateStatus(
            Long id,
            Complaint.Status status
    ){

        Complaint complaint = getComplaintById(id);


        complaint.setStatus(status);


        return complaintRepository.save(complaint);

    }



    // Delete complaint
    public void deleteComplaint(Long id){

        Complaint complaint = getComplaintById(id);

        complaintRepository.delete(complaint);

    }


}