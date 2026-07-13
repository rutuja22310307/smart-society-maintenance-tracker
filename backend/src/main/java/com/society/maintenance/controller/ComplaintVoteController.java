package com.society.maintenance.controller;


import com.society.maintenance.entity.*;

import com.society.maintenance.service.ComplaintVoteService;


import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/votes")
@CrossOrigin
public class ComplaintVoteController {


    private final ComplaintVoteService voteService;


    public ComplaintVoteController(
            ComplaintVoteService voteService
    ){

        this.voteService=voteService;

    }



    @PostMapping
    public ComplaintVote vote(
            @RequestBody ComplaintVote vote
    ){

        return voteService.addVote(
                vote.getComplaint(),
                vote.getUser()
        );

    }


@GetMapping("/complaint/{complaintId}")
public java.util.List<ComplaintVote> getVotesByComplaint(
        @PathVariable Long complaintId
){

    Complaint complaint = new Complaint();
    complaint.setId(complaintId);

    return voteService.getVotesByComplaint(complaint);

}


@GetMapping("/count/{complaintId}")
public long countVotes(
        @PathVariable Long complaintId
){

    Complaint complaint = new Complaint();
    complaint.setId(complaintId);

    return voteService.countVotes(complaint);

}
    @DeleteMapping("/{id}")
    public String removeVote(
            @PathVariable Long id
    ){

        voteService.removeVote(id);

        return "Vote removed";

    }

}