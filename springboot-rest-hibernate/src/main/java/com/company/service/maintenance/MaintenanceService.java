package com.company.service.maintenance;

import java.util.List;

import com.company.domainobject.MaintenanceDO;
import com.company.domainvalue.MaintenanceStatus;
import com.company.exception.ConstraintsViolationException;
import com.company.exception.EntityNotFoundException;

public interface MaintenanceService
{

    void deliverVehicle(Long maintenanceId) throws EntityNotFoundException;


    MaintenanceDO enterVehicle(Long carId) throws ConstraintsViolationException, EntityNotFoundException;


    MaintenanceDO addDays(Long maintenanceId, Long days) throws EntityNotFoundException;


    List<MaintenanceDO> find(MaintenanceStatus status) throws EntityNotFoundException;


    List<MaintenanceDO> findCar(Long carId) throws EntityNotFoundException;


    void updateStatus(Long maintenanceId, MaintenanceStatus status) throws EntityNotFoundException;

}
