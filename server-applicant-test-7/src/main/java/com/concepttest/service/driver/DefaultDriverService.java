package com.concepttest.service.driver;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.concepttest.dataaccessobject.DriverRepository;
import com.concepttest.domainobject.CarDO;
import com.concepttest.domainobject.DriverDO;
import com.concepttest.domainvalue.CarStatus;
import com.concepttest.domainvalue.GeoCoordinate;
import com.concepttest.domainvalue.OnlineStatus;
import com.concepttest.exception.CarAlreadyInUseException;
import com.concepttest.exception.ConstraintsViolationException;
import com.concepttest.exception.EntityNotFoundException;
import com.concepttest.exception.ValidationException;
import com.concepttest.service.car.CarService;
import com.concepttest.util.SearchCriteria;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverService implements DriverService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final DriverRepository driverRepository;

    @Autowired
    private CarService carService;


    public DefaultDriverService(final DriverRepository driverRepository)
    {
        this.driverRepository = driverRepository;
    }


    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverDO find(Long driverId) throws EntityNotFoundException
    {
        return findDriverChecked(driverId);
    }


    /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    public DriverDO create(DriverDO driverDO) throws ConstraintsViolationException
    {
        DriverDO driver;
        try
        {
            driver = driverRepository.save(driverDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setDeleted(true);
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverDO> find(OnlineStatus onlineStatus)
    {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }


    private DriverDO findDriverChecked(Long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = driverRepository.findOne(driverId);
        if (driverDO == null)
        {
            throw new EntityNotFoundException("Could not find entity with id: " + driverId);
        }
        return driverDO;
    }


    @Override
    @Transactional
    public DriverDO selectCar(long driverId, long carId) throws EntityNotFoundException, CarAlreadyInUseException, ValidationException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        if (!OnlineStatus.ONLINE.equals(driverDO.getOnlineStatus()))
        {
            throw new ValidationException("Driver not online");
        }

        CarDO carDo = carService.find(carId);

        if (!CarStatus.AVAILABLE.equals(carDo.getCarStatus()))
        {
            throw new CarAlreadyInUseException("Sorry, this car was selected by other dirver");
        }
        else
        {
            carDo.setCarStatus(CarStatus.SELECTED);
            carDo.setDriverCar(driverDO);
            driverDO.setCar(carDo);
        }
        return driverDO;
    }


    @Override
    @Transactional
    public DriverDO unselectCar(long driverId) throws EntityNotFoundException, ValidationException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        CarDO carDO = findDriverCarChecked(driverDO);
        carDO.setCarStatus(CarStatus.AVAILABLE);
        carDO.setDriverCar(null);
        driverDO.setCar(null);
        return driverDO;
    }


    private CarDO findDriverCarChecked(DriverDO driverDO) throws ValidationException
    {
        if (driverDO.getCar() == null)
        {
            throw new ValidationException("Could not find car entity with driver: " + driverDO.getId());
        }
        return driverDO.getCar();
    }


    @Override
    public List<DriverDO> findByCriteria(List<SearchCriteria> params)
    {
        return driverRepository.searchByCarProperties(params);
    }

}
