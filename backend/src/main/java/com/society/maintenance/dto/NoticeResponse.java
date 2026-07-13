package com.society.maintenance.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NoticeResponse {

    private Long id;

    private String title;

    private String message;

    private LocalDateTime createdAt;

    private LocalDateTime expiryDate;

    private String createdByName;

}