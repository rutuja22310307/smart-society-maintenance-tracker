package com.society.maintenance.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ComplaintRequest {

    @NotBlank(message = "Complaint title is required")
    private String title;

    @NotBlank(message = "Complaint description is required")
    private String description;

    @NotBlank(message = "Complaint category is required")
    private String category;

    @NotBlank(message = "Priority is required")
    private String priority;
}