package com.society.maintenance.repository;


import com.society.maintenance.entity.Maintenance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MaintenanceRepository
        extends JpaRepository<Maintenance, Long> {


    List<Maintenance> findByUser(
            com.society.maintenance.entity.User user
    );


    List<Maintenance> findByStatus(
        Maintenance.PaymentStatus status
);
}