package com.company.service.driver;

import com.company.domainobject.DriverDO;
import com.company.domainvalue.OnlineStatus;
import com.company.exception.CarAlreadyInUseException;
import com.company.exception.ConstraintsViolationException;
import com.company.exception.EntityNotFoundException;
import com.company.exception.ValidationException;
import com.company.util.SearchCriteria;

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
