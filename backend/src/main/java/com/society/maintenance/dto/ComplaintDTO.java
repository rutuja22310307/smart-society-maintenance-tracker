package com.society.maintenance.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintDTO {


    private Long id;


    private String title;


    private String description;


    private String category;


    private String status;


    private String priority;


    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;


    private String createdBy;

}