package com.concepttest.service.car;

import com.concepttest.domainobject.CarDO;
import com.concepttest.exception.EntityNotFoundException;

public interface CarService
{

    CarDO find(Long carId) throws EntityNotFoundException;

}
