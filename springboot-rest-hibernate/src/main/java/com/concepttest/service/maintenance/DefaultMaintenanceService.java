package com.concepttest.service.maintenance;

import java.time.ZonedDateTime;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.concepttest.dataaccessobject.MaintenanceRepository;
import com.concepttest.domainobject.CarDO;
import com.concepttest.domainobject.MaintenanceDO;
import com.concepttest.domainvalue.CarStatus;
import com.concepttest.domainvalue.MaintenanceStatus;
import com.concepttest.exception.ConstraintsViolationException;
import com.concepttest.exception.EntityNotFoundException;
import com.concepttest.service.car.CarService;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultMaintenanceService implements MaintenanceService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultMaintenanceService.class);

    private final MaintenanceRepository maintenanceRepository;

    @Autowired
    private CarService carService;


    public DefaultMaintenanceService(final MaintenanceRepository maintenanceRepository)
    {
        this.maintenanceRepository = maintenanceRepository;
    }


    @Override
    @Transactional
    public void deliverVehicle(Long maintenanceId) throws EntityNotFoundException
    {
        MaintenanceDO maintenanceDO = findMaintenanceChecked(maintenanceId);
        maintenanceDO.setMaintenanceStatus(MaintenanceStatus.WORK_FINISHED);
        CarDO car = maintenanceDO.getCar();
        if (car.getDriverCar() == null)
            maintenanceDO.getCar().setCarStatus(CarStatus.AVAILABLE);
        else
            maintenanceDO.getCar().setCarStatus(CarStatus.SELECTED);
        maintenanceDO.setFinishedDate(ZonedDateTime.now());
    }


    @Override
    @Transactional
    public MaintenanceDO enterVehicle(Long carId) throws ConstraintsViolationException, EntityNotFoundException
    {
        MaintenanceDO maintenance = new MaintenanceDO();
        CarDO carDo = carService.find(carId);

        maintenance.setCar(carDo);
        maintenance.setDateCreated(ZonedDateTime.now());
        maintenance.setEstimedFinishDate(ZonedDateTime.now().plusDays(3));
        maintenance.setMaintenanceStatus(MaintenanceStatus.INSPECTION);

        try
        {
            maintenance = maintenanceRepository.save(maintenance);
            carDo.setCarStatus(CarStatus.MANTAINANCE);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return maintenance;
    }


    @Override
    @Transactional
    public MaintenanceDO addDays(Long maintenanceId, Long days) throws EntityNotFoundException
    {
        MaintenanceDO maintenanceDO = findMaintenanceChecked(maintenanceId);
        ZonedDateTime estimated = maintenanceDO.getEstimedFinishDate();
        maintenanceDO.setEstimedFinishDate(estimated.plusDays(days));
        return maintenanceDO;
    }


    @Override
    public List<MaintenanceDO> find(MaintenanceStatus status) throws EntityNotFoundException
    {
        return maintenanceRepository.findByMaintenanceStatus(status);
    }


    @Override
    public List<MaintenanceDO> findCar(Long carId) throws EntityNotFoundException
    {
        List<MaintenanceDO> maintenance = maintenanceRepository.findByCarId(carId);
        if (maintenance.isEmpty())
        {
            throw new EntityNotFoundException("Could not find maintenance entity with  car id: " + carId);
        }
        return maintenance;
    }


    private MaintenanceDO findMaintenanceChecked(Long maintenanceId) throws EntityNotFoundException
    {
        MaintenanceDO maintenanceDO = maintenanceRepository.findOne(maintenanceId);
        if (maintenanceDO == null)
        {
            throw new EntityNotFoundException("Could not find entity with id: " + maintenanceId);
        }
        return maintenanceDO;
    }


    @Override
    @Transactional
    public void updateStatus(Long maintenanceId, MaintenanceStatus status) throws EntityNotFoundException
    {
        MaintenanceDO maintenanceDO = findMaintenanceChecked(maintenanceId);
        maintenanceDO.setMaintenanceStatus(status);
    }

}
