package com.society.maintenance.controller;


import com.society.maintenance.entity.Notification;
import com.society.maintenance.entity.User;

import com.society.maintenance.service.NotificationService;


import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/notifications")
@CrossOrigin
public class NotificationController {


    private final NotificationService service;


    public NotificationController(
            NotificationService service
    ){

        this.service=service;

    }



    @GetMapping("/{userId}")
    public List<Notification> getNotifications(
            @PathVariable Long userId
    ){

        User user=new User();

        user.setId(userId);


        return service
                .getUserNotifications(user);

    }



    @PutMapping("/{id}/read")
    public Notification markRead(
            @PathVariable Long id
    ){

        return service.markAsRead(id);

    }

}