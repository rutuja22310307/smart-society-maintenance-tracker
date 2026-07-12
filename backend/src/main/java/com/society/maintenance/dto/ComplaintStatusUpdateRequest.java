package com.society.maintenance.dto;


import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintStatusUpdateRequest {


    @NotBlank
    private String status;


    private String remark;

}