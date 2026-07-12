package com.society.maintenance.service;


import com.society.maintenance.entity.Notice;
import com.society.maintenance.repository.NoticeRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NoticeService {


    private final NoticeRepository noticeRepository;


    public NoticeService(
            NoticeRepository noticeRepository
    ) {

        this.noticeRepository = noticeRepository;

    }



    // Create Notice
    public Notice createNotice(Notice notice){

        return noticeRepository.save(notice);

    }



    // Get all notices
    public List<Notice> getAllNotices(){

        return noticeRepository
                .findAllByOrderByCreatedAtDesc();

    }



    // Get notice by ID
    public Notice getNoticeById(Long id){

        return noticeRepository.findById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Notice not found"
                    )
                );

    }



    // Update Notice
    public Notice updateNotice(
            Long id,
            Notice updatedNotice
    ){

        Notice existingNotice = getNoticeById(id);


        existingNotice.setTitle(
                updatedNotice.getTitle()
        );


        existingNotice.setMessage(
                updatedNotice.getMessage()
        );


        existingNotice.setExpiryDate(
                updatedNotice.getExpiryDate()
        );


        return noticeRepository.save(existingNotice);

    }



    // Delete Notice
    public void deleteNotice(Long id){

        Notice notice = getNoticeById(id);

        noticeRepository.delete(notice);

    }


}