package com.company.service.car;

import com.company.domainobject.CarDO;
import com.company.exception.EntityNotFoundException;

public interface CarService
{

    CarDO find(Long carId) throws EntityNotFoundException;

}
