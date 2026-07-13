package com.society.maintenance.service;

import com.society.maintenance.dto.ComplaintRequest;
import com.society.maintenance.dto.ComplaintResponse;
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
    public Complaint createComplaint(ComplaintRequest request) {

        Complaint complaint = new Complaint();

        complaint.setTitle(request.getTitle());
        complaint.setDescription(request.getDescription());
        complaint.setCategory(request.getCategory());
        complaint.setPriority(request.getPriority());

        return complaintRepository.save(complaint);
    }

    // Convert Entity to DTO
    private ComplaintResponse convertToResponse(Complaint complaint) {

        ComplaintResponse response = new ComplaintResponse();

        response.setId(complaint.getId());
        response.setTitle(complaint.getTitle());
        response.setDescription(complaint.getDescription());

        // Category (String)
        response.setCategory(complaint.getCategory());

        // Status (Enum)
        if (complaint.getStatus() != null) {
            response.setStatus(complaint.getStatus().name());
        }

        // Priority (String)
        response.setPriority(complaint.getPriority());

        response.setCreatedAt(complaint.getCreatedAt());
        response.setUpdatedAt(complaint.getUpdatedAt());

        if (complaint.getCreatedBy() != null) {
            response.setResidentName(complaint.getCreatedBy().getName());
            response.setApartmentNumber(complaint.getCreatedBy().getApartmentNumber());
        }

        return response;
    }

    // Get all complaints
    public List<ComplaintResponse> getAllComplaints() {
        return complaintRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // Get complaint by ID
    public ComplaintResponse getComplaintById(Long id) {

        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        return convertToResponse(complaint);
    }

    // Get complaints created by user
    public List<Complaint> getComplaintsByUser(User user) {
        return complaintRepository.findByCreatedBy(user);
    }

    // Get complaints by status
    public List<Complaint> getComplaintsByStatus(Complaint.Status status) {
        return complaintRepository.findByStatus(status);
    }

    // Update complaint status
    public Complaint updateStatus(Long id, Complaint.Status status) {

        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        complaint.setStatus(status);

        return complaintRepository.save(complaint);
    }

    // Delete complaint
    public void deleteComplaint(Long id) {

        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        complaintRepository.delete(complaint);
    }
}