package com.society.maintenance.controller;

import com.society.maintenance.dto.NoticeRequest;
import com.society.maintenance.dto.NoticeResponse;
import com.society.maintenance.entity.Notice;
import com.society.maintenance.service.NoticeService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notices")
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    // ==========================
    // Create Notice
    // ==========================
    @PostMapping
    public Notice createNotice(
            @Valid @RequestBody NoticeRequest request) {

        return noticeService.createNotice(request);

    }

    // ==========================
    // Get All Notices
    // ==========================
    @GetMapping
    public List<NoticeResponse> getAllNotices() {

        return noticeService.getAllNotices();

    }

    // ==========================
    // Get Notice By ID
    // ==========================
    @GetMapping("/{id}")
    public NoticeResponse getNoticeById(
            @PathVariable Long id) {

        return noticeService.getNoticeById(id);

    }

    // ==========================
    // Update Notice
    // ==========================
    @PutMapping("/{id}")
    public Notice updateNotice(
            @PathVariable Long id,
            @Valid @RequestBody NoticeRequest request) {

        return noticeService.updateNotice(id, request);

    }

    // ==========================
    // Delete Notice
    // ==========================
    @DeleteMapping("/{id}")
    public String deleteNotice(
            @PathVariable Long id) {

        noticeService.deleteNotice(id);

        return "Notice deleted successfully";

    }

}