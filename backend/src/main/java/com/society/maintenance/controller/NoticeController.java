package com.society.maintenance.controller;


import com.society.maintenance.entity.Notice;
import com.society.maintenance.service.NoticeService;


import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/notices")
@CrossOrigin
public class NoticeController {


    private final NoticeService noticeService;


    public NoticeController(
            NoticeService noticeService
    ){

        this.noticeService=noticeService;

    }



    @PostMapping
    public Notice create(
            @RequestBody Notice notice
    ){

        return noticeService
                .createNotice(notice);

    }



    @GetMapping
    public List<Notice> getAll(){

        return noticeService
                .getAllNotices();

    }



    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id
    ){

        noticeService.deleteNotice(id);

        return "Notice deleted";

    }

}