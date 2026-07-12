package com.society.maintenance.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintVoteDTO {


    private Long id;


    private Long complaintId;


    private String userName;


    private LocalDateTime votedAt;

}