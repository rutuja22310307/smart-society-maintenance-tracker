package com.society.maintenance.service;


import com.society.maintenance.dto.ComplaintRequest;
import com.society.maintenance.dto.ComplaintResponse;
import com.society.maintenance.entity.Complaint;
import com.society.maintenance.entity.User;
import com.society.maintenance.repository.ComplaintRepository;
import com.society.maintenance.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ComplaintService {


    private final ComplaintRepository complaintRepository;

    private final UserRepository userRepository;



    public ComplaintService(
            ComplaintRepository complaintRepository,
            UserRepository userRepository
    ) {

        this.complaintRepository = complaintRepository;
        this.userRepository = userRepository;

    }



    // Create Complaint
    public Complaint createComplaint(
            ComplaintRequest request
    ) {


        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();


        String email = authentication.getName();


        User user =
                userRepository.findByEmail(email)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "User not found"
                                )
                        );



        Complaint complaint = new Complaint();


        complaint.setTitle(
                request.getTitle()
        );


        complaint.setDescription(
                request.getDescription()
        );


        complaint.setCategory(
                request.getCategory()
        );


        complaint.setPriority(
                request.getPriority()
        );


        complaint.setCreatedBy(user);



        return complaintRepository.save(complaint);

    }





    // Convert Entity to Response DTO
    private ComplaintResponse convertToResponse(
            Complaint complaint
    ) {


        ComplaintResponse response =
                new ComplaintResponse();



        response.setId(
                complaint.getId()
        );


        response.setTitle(
                complaint.getTitle()
        );


        response.setDescription(
                complaint.getDescription()
        );


        response.setCategory(
                complaint.getCategory()
        );



        if(complaint.getStatus() != null){

            response.setStatus(
                    complaint.getStatus().name()
            );

        }



        response.setPriority(
                complaint.getPriority()
        );



        response.setCreatedAt(
                complaint.getCreatedAt()
        );


        response.setUpdatedAt(
                complaint.getUpdatedAt()
        );



        if(complaint.getCreatedBy() != null){


            response.setResidentName(
                    complaint.getCreatedBy()
                            .getName()
            );


            response.setApartmentNumber(
                    complaint.getCreatedBy()
                            .getApartmentNumber()
            );

        }



        return response;

    }





    // Get All Complaints
    public List<ComplaintResponse> getAllComplaints(){


        return complaintRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();

    }





    // Get Complaint By ID
    public ComplaintResponse getComplaintById(
            Long id
    ){


        Complaint complaint =
                complaintRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Complaint not found"
                                )
                        );


        return convertToResponse(complaint);

    }





    // Get Complaints By User
    public List<Complaint> getComplaintsByUser(
            User user
    ){

        return complaintRepository.findByCreatedBy(user);

    }





    // Get Complaints By Status
    public List<Complaint> getComplaintsByStatus(
            Complaint.Status status
    ){

        return complaintRepository.findByStatus(status);

    }





    // Update Complaint Status
    public Complaint updateStatus(
            Long id,
            Complaint.Status status
    ){


        Complaint complaint =
                complaintRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Complaint not found"
                                )
                        );



        if(status == null){

            throw new RuntimeException(
                    "Status cannot be null"
            );

        }



        complaint.setStatus(status);



        return complaintRepository.save(complaint);

    }





    // Delete Complaint
    public void deleteComplaint(
            Long id
    ){


        Complaint complaint =
                complaintRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Complaint not found"
                                )
                        );



        complaintRepository.delete(complaint);

    }


}