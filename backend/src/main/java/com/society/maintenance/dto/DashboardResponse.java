package com.society.maintenance.dto;

import lombok.Data;
import java.util.List;

@Data
public class DashboardResponse {

    // ==========================
    // User Statistics
    // ==========================

    private long totalResidents;
    private long totalStaff;
    private long totalAdmins;

    // ==========================
    // Complaint Statistics
    // ==========================

    private long totalComplaints;
    private long pendingComplaints;
    private long inProgressComplaints;
    private long resolvedComplaints;

    // ==========================
    // Notice Statistics
    // ==========================

    private long totalNotices;

    // ==========================
    // Recent Activity
    // ==========================

    private List<ComplaintResponse> recentComplaints;

    private List<NoticeResponse> recentNotices;
}