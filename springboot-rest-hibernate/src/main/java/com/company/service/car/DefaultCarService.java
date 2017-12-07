package com.company.service.car;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.company.dataaccessobject.CarRepository;
import com.company.domainobject.CarDO;
import com.company.exception.EntityNotFoundException;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some car specific things.
 * <p/>
 */
@Service
public class DefaultCarService implements CarService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultCarService.class);

    private final CarRepository carRepository;


    public DefaultCarService(final CarRepository carRepository)
    {
        this.carRepository = carRepository;
    }


    @Override
    public CarDO find(Long carId) throws EntityNotFoundException
    {
        return findCarChecked(carId);
    }


    private CarDO findCarChecked(Long carId) throws EntityNotFoundException
    {
        CarDO carDO = carRepository.findOne(carId);
        if (carDO == null)
        {
            LOG.debug("No car info");
            throw new EntityNotFoundException("Could not find entity with id: " + carRepository);
        }
        return carDO;
    }

}
