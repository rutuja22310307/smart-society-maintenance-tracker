package com.society.maintenance.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceResponse {


    private Long id;


    private Double amount;


    private String month;


    private Integer year;


    private String status;


    private LocalDateTime createdAt;


    private String residentName;


    private String apartmentNumber;

}