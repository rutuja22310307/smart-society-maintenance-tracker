package com.society.maintenance.service;


import com.society.maintenance.dto.MaintenanceResponse;
import com.society.maintenance.entity.Maintenance;
import com.society.maintenance.entity.User;
import com.society.maintenance.repository.MaintenanceRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MaintenanceService {


    private final MaintenanceRepository maintenanceRepository;



    public MaintenanceService(
            MaintenanceRepository maintenanceRepository
    ){

        this.maintenanceRepository = maintenanceRepository;

    }



    // Create maintenance record
    public Maintenance createMaintenance(
            Maintenance maintenance
    ){

        return maintenanceRepository.save(maintenance);

    }



    // Get all maintenance records
    public List<MaintenanceResponse> getAllMaintenance(){


        return maintenanceRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();

    }





    // Get maintenance by ID
    public MaintenanceResponse getMaintenanceById(
            Long id
    ){

        Maintenance maintenance =
                maintenanceRepository.findById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                            "Maintenance not found"
                    )
                );


        return convertToDTO(maintenance);

    }





    // Get maintenance of a user
    public List<MaintenanceResponse> getMaintenanceByUser(
            User user
    ){

        return maintenanceRepository
                .findByUser(user)
                .stream()
                .map(this::convertToDTO)
                .toList();

    }





    // Get maintenance by payment status
    public List<MaintenanceResponse> getByStatus(
            Maintenance.PaymentStatus status
    ){

        return maintenanceRepository
                .findByStatus(status)
                .stream()
                .map(this::convertToDTO)
                .toList();

    }





    // Update payment status
    public Maintenance updateStatus(
            Long id,
            Maintenance.PaymentStatus status
    ){

        Maintenance maintenance =
                maintenanceRepository.findById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                            "Maintenance not found"
                    )
                );


        maintenance.setStatus(status);


        return maintenanceRepository.save(
                maintenance
        );

    }





    // Delete maintenance
    public void deleteMaintenance(
            Long id
    ){

        Maintenance maintenance =
                maintenanceRepository.findById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                            "Maintenance not found"
                    )
                );


        maintenanceRepository.delete(
                maintenance
        );

    }






    // Entity -> DTO conversion
    private MaintenanceResponse convertToDTO(
            Maintenance maintenance
    ){


        return new MaintenanceResponse(

                maintenance.getId(),

                maintenance.getAmount(),

                maintenance.getMonth(),

                maintenance.getYear(),

                maintenance.getStatus().name(),

                maintenance.getCreatedAt(),

                maintenance.getUser() != null
                        ? maintenance.getUser().getName()
                        : null,

                maintenance.getUser() != null
                        ? maintenance.getUser().getApartmentNumber()
                        : null

        );

    }


}