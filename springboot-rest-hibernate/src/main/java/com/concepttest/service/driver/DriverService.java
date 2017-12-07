package com.concepttest.service.driver;

import com.concepttest.domainobject.DriverDO;
import com.concepttest.domainvalue.OnlineStatus;
import com.concepttest.exception.CarAlreadyInUseException;
import com.concepttest.exception.ConstraintsViolationException;
import com.concepttest.exception.EntityNotFoundException;
import com.concepttest.exception.ValidationException;
import com.concepttest.util.SearchCriteria;

import java.util.List;

public interface DriverService
{

    DriverDO find(Long driverId) throws EntityNotFoundException;


    DriverDO create(DriverDO driverDO) throws ConstraintsViolationException;


    void delete(Long driverId) throws EntityNotFoundException;


    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;


    List<DriverDO> find(OnlineStatus onlineStatus);


    DriverDO selectCar(long driverId, long carId) throws ValidationException, EntityNotFoundException, CarAlreadyInUseException;


    DriverDO unselectCar(long driverId) throws ValidationException, EntityNotFoundException;


    List<DriverDO> findByCriteria(List<SearchCriteria> params);

}
