package com.society.maintenance.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.society.maintenance.dto.MaintenanceResponse;
import com.society.maintenance.entity.Maintenance;
import com.society.maintenance.service.MaintenanceService;


@RestController
@RequestMapping("/api/maintenance")
public class MaintenanceController {


    @Autowired
    private MaintenanceService maintenanceService;



    @PostMapping
    public Maintenance createMaintenance(
            @RequestBody Maintenance maintenance) {

        return maintenanceService.createMaintenance(maintenance);
    }



    @GetMapping
    public List<MaintenanceResponse> getAllMaintenance() {

        return maintenanceService.getAllMaintenance();
    }



    @GetMapping("/{id}")
    public MaintenanceResponse getMaintenanceById(
            @PathVariable Long id) {

        return maintenanceService.getMaintenanceById(id);
    }



    @DeleteMapping("/{id}")
    public String deleteMaintenance(
            @PathVariable Long id){

        maintenanceService.deleteMaintenance(id);

        return "Maintenance deleted successfully";
    }

}