package com.company.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.company.controller.mapper.DriverMapper;
import com.company.datatransferobject.DriverDTO;
import com.company.domainobject.DriverDO;
import com.company.domainvalue.EngineTypes;
import com.company.domainvalue.OnlineStatus;
import com.company.exception.CarAlreadyInUseException;
import com.company.exception.ConstraintsViolationException;
import com.company.exception.EntityNotFoundException;
import com.company.exception.ValidationException;
import com.company.service.driver.DriverService;
import com.company.util.SearchCriteria;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController
{

    private final DriverService driverService;


    @Autowired
    public DriverController(final DriverService driverService)
    {
        this.driverService = driverService;
    }


    @GetMapping("/{driverId}")
    public DriverDTO getDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException
    {
        return DriverMapper.makeDriverDTO(driverService.find(driverId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException
    {
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        return DriverMapper.makeDriverDTO(driverService.create(driverDO));
    }


    @DeleteMapping("/{driverId}")
    public void deleteDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException
    {
        driverService.delete(driverId);
    }


    @PutMapping("/{driverId}")
    public void updateLocation(
        @Valid @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        driverService.updateLocation(driverId, longitude, latitude);
    }


    @GetMapping
    public List<DriverDTO> findDrivers(@RequestParam OnlineStatus onlineStatus)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        return DriverMapper.makeDriverDTOList(driverService.find(onlineStatus));
    }


    @PutMapping("{driverId}/vehicles/{carId}")
    public DriverDTO selectCar(
        @Valid @PathVariable long driverId, @PathVariable long carId)
        throws ValidationException, EntityNotFoundException, CarAlreadyInUseException
    {
        return DriverMapper.makeDriverDTO(driverService.selectCar(driverId, carId));
    }


    @DeleteMapping("{driverId}/vehicles")
    public DriverDTO unselectCar(
        @Valid @PathVariable long driverId)
        throws ValidationException, EntityNotFoundException
    {
        return DriverMapper.makeDriverDTO(driverService.unselectCar(driverId));
    }


    @GetMapping("vehicles/search")
    public List<DriverDTO> findDriversByCriteria(
        @RequestParam(required = false) Integer seatCount,
        @RequestParam(required = false) Boolean convertible,
        @RequestParam(required = false) Integer rating,
        @RequestParam(required = false) String color,
        @RequestParam(required = false) EngineTypes engineType,
        @RequestParam(required = false) String modelAge)
    {
        List<SearchCriteria> criteriaParams = new ArrayList<>();
        criteriaParams.add(new SearchCriteria("seatCount", seatCount));
        criteriaParams.add(new SearchCriteria("convertible", convertible));
        criteriaParams.add(new SearchCriteria("rating", rating));
        criteriaParams.add(new SearchCriteria("color", color));
        criteriaParams.add(new SearchCriteria("engineType", engineType));
        criteriaParams.add(new SearchCriteria("modelAge", modelAge));

        return DriverMapper.makeDriverDTOList(driverService.findByCriteria(criteriaParams));

    }

}
