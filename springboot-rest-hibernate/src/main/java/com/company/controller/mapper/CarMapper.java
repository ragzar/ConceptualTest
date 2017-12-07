package com.company.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.company.datatransferobject.CarDTO;
import com.company.domainobject.CarDO;

public class CarMapper
{
    public static CarDO makeDriverDO(CarDTO carDTO)
    {
        return new CarDO(carDTO.getLicensePlate(), carDTO.getSeatCount(), carDTO.getConvertible(), carDTO.getRating(), carDTO.getEngineType());
    }


    public static CarDTO makeCarDTO(CarDO carDO)
    {
        CarDTO.CarDTOBuilder carDTOBuilder =
            CarDTO
                .newBuilder().setId(carDO.getId())
                .setEngineType(carDO.getEngineType())
                .setColor(carDO.getColor())
                .setConvertible(carDO.getConvertible())
                .setLicensePlate(carDO.getLicensePlate())
                .setModelAge(carDO.getModelAge())
                .setRating(carDO.getRating())
                .setSeatCount(carDO.getSeatCount());
        return carDTOBuilder.createCarDTO();
    }


    public static List<CarDTO> makeDriverDTOList(Collection<CarDO> cars)
    {
        return cars
            .stream()
            .map(CarMapper::makeCarDTO)
            .collect(Collectors.toList());
    }
}
