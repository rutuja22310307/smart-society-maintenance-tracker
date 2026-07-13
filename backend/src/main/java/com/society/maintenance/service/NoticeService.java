package com.society.maintenance.service;

import com.society.maintenance.dto.NoticeRequest;
import com.society.maintenance.dto.NoticeResponse;
import com.society.maintenance.entity.Notice;
import com.society.maintenance.repository.NoticeRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    // ==========================
    // Create Notice
    // ==========================
    public Notice createNotice(NoticeRequest request) {

        Notice notice = new Notice();

        notice.setTitle(request.getTitle());
        notice.setMessage(request.getMessage());
        if (request.getExpiryDate() != null) {
    notice.setExpiryDate(request.getExpiryDate().atStartOfDay());
}
        // Set created time
        notice.setCreatedAt(LocalDateTime.now());

        return noticeRepository.save(notice);
    }

    // ==========================
    // Convert Entity -> DTO
    // ==========================
    private NoticeResponse convertToResponse(Notice notice) {

        NoticeResponse response = new NoticeResponse();

        response.setId(notice.getId());
        response.setTitle(notice.getTitle());
        response.setMessage(notice.getMessage());
        response.setCreatedAt(notice.getCreatedAt());
        response.setExpiryDate(notice.getExpiryDate());

        if (notice.getCreatedBy() != null) {
            response.setCreatedByName(
                    notice.getCreatedBy().getName()
            );
        }

        return response;
    }

    // ==========================
    // Get All Notices
    // ==========================
    public List<NoticeResponse> getAllNotices() {

        return noticeRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // ==========================
    // Get Notice By ID
    // ==========================
    public NoticeResponse getNoticeById(Long id) {

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notice not found"));

        return convertToResponse(notice);
    }

    // ==========================
    // Update Notice
    // ==========================
    public Notice updateNotice(Long id, NoticeRequest request) {

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notice not found"));

        notice.setTitle(request.getTitle());
        notice.setMessage(request.getMessage());
       if (request.getExpiryDate() != null) {
    notice.setExpiryDate(request.getExpiryDate().atStartOfDay());
} else {
    notice.setExpiryDate(null);
}
        return noticeRepository.save(notice);
    }

    // ==========================
    // Delete Notice
    // ==========================
    public void deleteNotice(Long id) {

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notice not found"));

        noticeRepository.delete(notice);
    }
}