package com.company.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.company.domainvalue.MaintenanceStatus;

@Entity
@Table(
    name = "maintenance")
public class MaintenanceDO
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime estimedFinishDate;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime finishedDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MaintenanceStatus maintenanceStatus;

    @ManyToOne
    private CarDO car;


    public MaintenanceDO()
    {}


    public MaintenanceDO(ZonedDateTime estimedFinishDate, ZonedDateTime finishedDate, MaintenanceStatus maintenanceStatus)
    {
        super();
        this.estimedFinishDate = estimedFinishDate;
        this.finishedDate = finishedDate;
        this.maintenanceStatus = maintenanceStatus;
    }


    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public ZonedDateTime getDateCreated()
    {
        return dateCreated;
    }


    public void setDateCreated(ZonedDateTime dateCreated)
    {
        this.dateCreated = dateCreated;
    }


    public ZonedDateTime getEstimedFinishDate()
    {
        return estimedFinishDate;
    }


    public void setEstimedFinishDate(ZonedDateTime estimedFinishDate)
    {
        this.estimedFinishDate = estimedFinishDate;
    }


    public ZonedDateTime getFinishedDate()
    {
        return finishedDate;
    }


    public void setFinishedDate(ZonedDateTime finishedDate)
    {
        this.finishedDate = finishedDate;
    }


    public MaintenanceStatus getMaintenanceStatus()
    {
        return maintenanceStatus;
    }


    public void setMaintenanceStatus(MaintenanceStatus maintenanceStatus)
    {
        this.maintenanceStatus = maintenanceStatus;
    }


    public CarDO getCar()
    {
        return car;
    }


    public void setCar(CarDO car)
    {
        this.car = car;
    }

}
