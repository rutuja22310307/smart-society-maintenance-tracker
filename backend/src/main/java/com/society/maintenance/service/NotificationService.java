package com.society.maintenance.service;


import com.society.maintenance.entity.Notification;
import com.society.maintenance.entity.User;

import com.society.maintenance.repository.NotificationRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NotificationService {


    private final NotificationRepository notificationRepository;


    public NotificationService(
            NotificationRepository notificationRepository
    ){

        this.notificationRepository = notificationRepository;

    }



    // Create notification
    public Notification createNotification(
            Notification notification
    ){

        return notificationRepository.save(notification);

    }



    // Get notifications of user
    public List<Notification> getUserNotifications(
            User user
    ){

        return notificationRepository.findByUser(user);

    }




    // Get unread notifications
    public List<Notification> getUnreadNotifications(
            User user
    ){

        return notificationRepository
                .findByUserAndIsRead(user, false);

    }



    // Mark notification as read
    public Notification markAsRead(Long id){

        Notification notification =
                notificationRepository.findById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Notification not found"
                    )
                );


        notification.setRead(true);


        return notificationRepository.save(notification);

    }



    // Delete notification
    public void deleteNotification(Long id){

        Notification notification =
                notificationRepository.findById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Notification not found"
                    )
                );


        notificationRepository.delete(notification);

    }


}