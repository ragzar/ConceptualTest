package com.concepttest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.concepttest.controller.mapper.MaintenanceMapper;
import com.concepttest.datatransferobject.MaintenanceDTO;
import com.concepttest.domainvalue.MaintenanceStatus;
import com.concepttest.exception.ConstraintsViolationException;
import com.concepttest.exception.EntityNotFoundException;
import com.concepttest.service.maintenance.MaintenanceService;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/maintenances")
public class MaintenanceController
{

    private final MaintenanceService maintenanceService;


    @Autowired
    public MaintenanceController(final MaintenanceService maintenanceService)
    {
        this.maintenanceService = maintenanceService;
    }


    @GetMapping
    public List<MaintenanceDTO> findMaintenanceCars(@RequestParam MaintenanceStatus status)
        throws EntityNotFoundException
    {
        return MaintenanceMapper.makeMaintenanceDTOList(maintenanceService.find(status));
    }


    @PostMapping("vehicles/{carId}")
    @ResponseStatus(HttpStatus.CREATED)
    public MaintenanceDTO enterVehicle(@Valid @PathVariable long carId)
        throws EntityNotFoundException, ConstraintsViolationException
    {
        return MaintenanceMapper.makeMaintenanceDTO(maintenanceService.enterVehicle(carId));
    }


    @GetMapping("vehicles/{carId}")
    public List<MaintenanceDTO> getVehicle(@Valid @PathVariable long carId) throws EntityNotFoundException
    {
        return MaintenanceMapper.makeMaintenanceDTOList(maintenanceService.findCar(carId));
    }


    @PutMapping("/{maintenanceId}")
    public void updateStatus(@Valid @PathVariable long maintenanceId, @RequestParam MaintenanceStatus newStatus)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        maintenanceService.updateStatus(maintenanceId, newStatus);
    }


    @DeleteMapping("{maintenanceId}")
    public void deliverVehicle(@Valid @PathVariable long maintenanceId)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        maintenanceService.deliverVehicle(maintenanceId);
    }


    @PutMapping("/{maintenanceId}/{days}")
    public MaintenanceDTO addMaintenanceDays(@Valid @PathVariable long maintenanceId, @PathVariable long days)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        return MaintenanceMapper.makeMaintenanceDTO(maintenanceService.addDays(maintenanceId, days));
    }

}
