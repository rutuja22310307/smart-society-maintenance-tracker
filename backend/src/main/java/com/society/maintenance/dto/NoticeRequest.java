package com.society.maintenance.dto;


import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeRequest {


    @NotBlank(message = "Title required")
    private String title;


    @NotBlank(message = "Message required")
    private String message;


    private LocalDateTime expiryDate;

}