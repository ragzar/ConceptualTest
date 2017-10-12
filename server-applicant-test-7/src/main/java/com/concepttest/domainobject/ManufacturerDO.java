package com.concepttest.domainobject;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "manufacturer")
public class ManufacturerDO
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String headquarters;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime foundedDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "manufacter_id")
    private List<CarDO> cars = new ArrayList<>();


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


    public String getName()
    {
        return name;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    public String getHeadquarters()
    {
        return headquarters;
    }


    public void setHeadquarters(String headquarters)
    {
        this.headquarters = headquarters;
    }


    public ZonedDateTime getFoundedDate()
    {
        return foundedDate;
    }


    public void setFoundedDate(ZonedDateTime foundedDate)
    {
        this.foundedDate = foundedDate;
    }


    public List<CarDO> getCars()
    {
        return cars;
    }


    public void setCars(List<CarDO> cars)
    {
        this.cars = cars;
    }

}
