package com.society.maintenance.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintHistoryDTO {


    private Long id;


    private String oldStatus;


    private String newStatus;


    private String remark;


    private String changedBy;


    private LocalDateTime changedAt;

}