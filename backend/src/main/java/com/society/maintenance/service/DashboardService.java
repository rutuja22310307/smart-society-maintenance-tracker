package com.society.maintenance.service;

import com.society.maintenance.dto.DashboardResponse;
import com.society.maintenance.entity.Complaint;
import com.society.maintenance.entity.User;
import com.society.maintenance.repository.ComplaintRepository;
import com.society.maintenance.repository.NoticeRepository;
import com.society.maintenance.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.society.maintenance.dto.ComplaintResponse;
import com.society.maintenance.dto.NoticeResponse;
import com.society.maintenance.entity.Notice;
@Service
public class DashboardService {

    private final UserRepository userRepository;
    private final ComplaintRepository complaintRepository;
    private final NoticeRepository noticeRepository;

    public DashboardService(
            UserRepository userRepository,
            ComplaintRepository complaintRepository,
            NoticeRepository noticeRepository) {

        this.userRepository = userRepository;
        this.complaintRepository = complaintRepository;
        this.noticeRepository = noticeRepository;
    }

    public DashboardResponse getDashboardSummary() {

        DashboardResponse response = new DashboardResponse();

        // ==========================
        // User Statistics
        // ==========================

        response.setTotalResidents(
                userRepository.countByRole(User.Role.RESIDENT));

        response.setTotalStaff(
                userRepository.countByRole(User.Role.STAFF));

        response.setTotalAdmins(
                userRepository.countByRole(User.Role.ADMIN));

        // ==========================
        // Complaint Statistics
        // ==========================

        response.setTotalComplaints(
                complaintRepository.count());

        response.setPendingComplaints(
                complaintRepository.countByStatus(
                        Complaint.Status.PENDING));

        response.setInProgressComplaints(
                complaintRepository.countByStatus(
                        Complaint.Status.IN_PROGRESS));

        response.setResolvedComplaints(
                complaintRepository.countByStatus(
                        Complaint.Status.RESOLVED));

        // ==========================
        // Notice Statistics
        // ==========================

        response.setTotalNotices(
                noticeRepository.count());
                // ==========================
// Recent Complaints
// ==========================

response.setRecentComplaints(

        complaintRepository
                .findTop5ByOrderByCreatedAtDesc()
                .stream()
                .map(this::convertComplaint)
                .toList()

);

// ==========================
// Recent Notices
// ==========================

response.setRecentNotices(

        noticeRepository
                .findTop5ByOrderByCreatedAtDesc()
                .stream()
                .map(this::convertNotice)
                .toList()

);

        return response;
    }
    private ComplaintResponse convertComplaint(Complaint complaint) {

    ComplaintResponse response = new ComplaintResponse();

    response.setId(complaint.getId());
    response.setTitle(complaint.getTitle());
    response.setDescription(complaint.getDescription());

    if (complaint.getCategory() != null) {
        response.setCategory(complaint.getCategory().toString());
    }

    if (complaint.getPriority() != null) {
        response.setPriority(complaint.getPriority().toString());
    }

    if (complaint.getStatus() != null) {
        response.setStatus(complaint.getStatus().toString());
    }

    response.setCreatedAt(complaint.getCreatedAt());
    response.setUpdatedAt(complaint.getUpdatedAt());

    if (complaint.getCreatedBy() != null) {
        response.setResidentName(complaint.getCreatedBy().getName());
        response.setApartmentNumber(
                complaint.getCreatedBy().getApartmentNumber()
        );
    }

    return response;
}

private NoticeResponse convertNotice(Notice notice) {

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
}