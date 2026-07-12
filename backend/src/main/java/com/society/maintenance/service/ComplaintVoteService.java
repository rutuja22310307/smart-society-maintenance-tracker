package com.society.maintenance.service;


import com.society.maintenance.entity.Complaint;
import com.society.maintenance.entity.ComplaintVote;
import com.society.maintenance.entity.User;

import com.society.maintenance.repository.ComplaintVoteRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ComplaintVoteService {


    private final ComplaintVoteRepository voteRepository;


    public ComplaintVoteService(
            ComplaintVoteRepository voteRepository
    ){

        this.voteRepository = voteRepository;

    }



    // Add vote to complaint
    public ComplaintVote addVote(
            Complaint complaint,
            User user
    ){


        if(voteRepository
                .existsByComplaintAndUser(
                        complaint,
                        user
                )){

            throw new RuntimeException(
                    "User already voted for this complaint"
            );

        }


        ComplaintVote vote = new ComplaintVote();


        vote.setComplaint(complaint);

        vote.setUser(user);


        return voteRepository.save(vote);

    }





    // Get all votes of complaint
    public List<ComplaintVote> getVotesByComplaint(
            Complaint complaint
    ){

        return voteRepository
                .findByComplaint(complaint);

    }




    // Count votes
    public long countVotes(
            Complaint complaint
    ){

        return voteRepository
                .findByComplaint(complaint)
                .size();

    }




    // Remove vote
    public void removeVote(
            Long id
    ){

        ComplaintVote vote =
                voteRepository.findById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                            "Vote not found"
                    )
                );


        voteRepository.delete(vote);

    }


}