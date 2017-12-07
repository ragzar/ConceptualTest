package com.company.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.company.datatransferobject.MaintenanceDTO;
import com.company.domainobject.MaintenanceDO;

public class MaintenanceMapper
{
    public static MaintenanceDO makeMaintenanceDO(MaintenanceDTO maintenanceDTO)
    {
        return new MaintenanceDO(maintenanceDTO.getEstimedFinishDate(), maintenanceDTO.getEstimedFinishDate(), maintenanceDTO.getMaintenanceStatus());
    }


    public static MaintenanceDTO makeMaintenanceDTO(MaintenanceDO maintenanceDO)
    {
        MaintenanceDTO.MaintenanceDTOBuilder maintenanceDTOBuilder =
            MaintenanceDTO
                .newBuilder()
                .setId(maintenanceDO.getId())
                .setEstimedFinishDate(maintenanceDO.getEstimedFinishDate())
                .setFinishedDate(maintenanceDO.getFinishedDate())
                .setMaintenanceStatus(maintenanceDO.getMaintenanceStatus());

        if (maintenanceDO.getCar() != null)
        {
            maintenanceDTOBuilder.setCarDTO(CarMapper.makeCarDTO(maintenanceDO.getCar()));
        }

        return maintenanceDTOBuilder.createMaintenaceDTO();
    }


    public static List<MaintenanceDTO> makeMaintenanceDTOList(Collection<MaintenanceDO> maintenence)
    {
        return maintenence
            .stream()
            .map(MaintenanceMapper::makeMaintenanceDTO)
            .collect(Collectors.toList());
    }
}
