package com.company.domainobject;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.company.domainvalue.CarStatus;
import com.company.domainvalue.EngineTypes;

@Entity
@Table(
    name = "car",
    uniqueConstraints = @UniqueConstraint(name = "uc_licenseplate", columnNames = {"licensePlate"}))
public class CarDO
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    @NotNull(message = "License plate cannot be null")
    private String licensePlate;

    @Column(nullable = false)
    private Integer seatCount;

    @Column(nullable = false)
    private Boolean convertible;

    @Column(nullable = false)
    private Integer rating;

    @Column
    private String color;

    @Column
    private String modelAge;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EngineTypes engineType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarStatus carStatus;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private List<MaintenanceDO> maintenance = new ArrayList<>();

    @ManyToOne
    private ManufacturerDO manufacter;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id")
    private DriverDO driverCar;


    public CarDO()
    {}


    public CarDO(String licensePlate, Integer seatCount, Boolean convertible, Integer rating, EngineTypes engineType)
    {
        super();
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.rating = rating;
        this.engineType = engineType;
        this.carStatus = CarStatus.AVAILABLE;
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


    public String getLicensePlate()
    {
        return licensePlate;
    }


    public void setLicensePlate(String licensePlate)
    {
        this.licensePlate = licensePlate;
    }


    public Integer getSeatCount()
    {
        return seatCount;
    }


    public void setSeatCount(Integer seatCount)
    {
        this.seatCount = seatCount;
    }


    public Boolean getConvertible()
    {
        return convertible;
    }


    public void setConvertible(Boolean convertible)
    {
        this.convertible = convertible;
    }


    public Integer getRating()
    {
        return rating;
    }


    public void setRating(Integer rating)
    {
        this.rating = rating;
    }


    public String getColor()
    {
        return color;
    }


    public void setColor(String color)
    {
        this.color = color;
    }


    public String getModelAge()
    {
        return modelAge;
    }


    public void setModelAge(String modelAge)
    {
        this.modelAge = modelAge;
    }


    public EngineTypes getEngineType()
    {
        return engineType;
    }


    public void setEngineType(EngineTypes engineType)
    {
        this.engineType = engineType;
    }


    public CarStatus getCarStatus()
    {
        return carStatus;
    }


    public void setCarStatus(CarStatus carStatus)
    {
        this.carStatus = carStatus;
    }


    public List<MaintenanceDO> getMaintenance()
    {
        return maintenance;
    }


    public void setMaintenance(List<MaintenanceDO> maintenance)
    {
        this.maintenance = maintenance;
    }


    public ManufacturerDO getManufacter()
    {
        return manufacter;
    }


    public void setManufacter(ManufacturerDO manufacter)
    {
        this.manufacter = manufacter;
    }


    public DriverDO getDriverCar()
    {
        return driverCar;
    }


    public void setDriverCar(DriverDO driverCar)
    {
        this.driverCar = driverCar;
    }
}
