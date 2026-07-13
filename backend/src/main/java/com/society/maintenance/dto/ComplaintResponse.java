package com.society.maintenance.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ComplaintResponse {

    private Long id;

    private String title;

    private String description;

    private String category;

    private String status;

    private String priority;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String residentName;

    private String apartmentNumber;

}