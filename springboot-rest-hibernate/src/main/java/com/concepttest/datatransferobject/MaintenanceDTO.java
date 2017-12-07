package com.concepttest.datatransferobject;

import java.time.ZonedDateTime;

import com.concepttest.domainvalue.MaintenanceStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MaintenanceDTO
{
    @JsonIgnore
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private ZonedDateTime estimedFinishDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private ZonedDateTime finishedDate;
    private MaintenanceStatus maintenanceStatus;
    private CarDTO carDTO;


    private MaintenanceDTO()
    {}


    private MaintenanceDTO(Long id, ZonedDateTime estimedFinishDate, ZonedDateTime finishedDate, CarDTO carDTO, MaintenanceStatus maintenanceStatus)
    {
        this.id = id;
        this.estimedFinishDate = estimedFinishDate;
        this.finishedDate = finishedDate;
        this.maintenanceStatus = maintenanceStatus;
        this.carDTO = carDTO;
    }


    public static MaintenanceDTOBuilder newBuilder()
    {
        return new MaintenanceDTOBuilder();
    }


    @JsonProperty
    public Long getId()
    {
        return id;
    }


    public ZonedDateTime getEstimedFinishDate()
    {
        return estimedFinishDate;
    }


    public ZonedDateTime getFinishedDate()
    {
        return finishedDate;
    }


    public CarDTO getCarDTO()
    {
        return carDTO;
    }


    public MaintenanceStatus getMaintenanceStatus()
    {
        return maintenanceStatus;
    }

    public static class MaintenanceDTOBuilder
    {
        private Long id;
        private ZonedDateTime estimedFinishDate;

        private ZonedDateTime finishedDate;
        private MaintenanceStatus maintenanceStatus;
        private CarDTO carDTO;


        public MaintenanceDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public MaintenanceDTOBuilder setEstimedFinishDate(ZonedDateTime estimedFinishDate)
        {
            this.estimedFinishDate = estimedFinishDate;
            return this;
        }


        public MaintenanceDTOBuilder setFinishedDate(ZonedDateTime finishedDate)
        {
            this.finishedDate = finishedDate;
            return this;
        }


        public MaintenanceDTOBuilder setCarDTO(CarDTO carDTO)
        {
            this.carDTO = carDTO;
            return this;
        }


        public MaintenanceDTOBuilder setMaintenanceStatus(MaintenanceStatus maintenanceStatus)
        {
            this.maintenanceStatus = maintenanceStatus;
            return this;
        }


        public MaintenanceDTO createMaintenaceDTO()
        {
            return new MaintenanceDTO(id, estimedFinishDate, finishedDate, carDTO, maintenanceStatus);
        }

    }

}
