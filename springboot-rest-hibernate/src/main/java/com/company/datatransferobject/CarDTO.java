package com.company.datatransferobject;

import javax.validation.constraints.NotNull;

import com.company.domainvalue.EngineTypes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO
{
    @JsonIgnore
    private Long id;

    @NotNull(message = "License plate cannot be null")
    private String licensePlate;

    private Integer seatCount;

    private Boolean convertible;

    private Integer rating;

    private String color;

    private String modelAge;

    private EngineTypes engineType;


    private CarDTO()
    {}


    private CarDTO(Long id, String licensePlate, Integer seatCount, Boolean convertible, Integer rating, EngineTypes engineType, String color, String modelAge)
    {
        super();
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.rating = rating;
        this.engineType = engineType;
        this.color = color;
        this.modelAge = modelAge;
    }


    public static CarDTOBuilder newBuilder()
    {
        return new CarDTOBuilder();
    }


    @JsonProperty
    public Long getId()
    {
        return id;
    }


    public String getLicensePlate()
    {
        return licensePlate;
    }


    public Integer getSeatCount()
    {
        return seatCount;
    }


    public Boolean getConvertible()
    {
        return convertible;
    }


    public Integer getRating()
    {
        return rating;
    }


    public String getColor()
    {
        return color;
    }


    public String getModelAge()
    {
        return modelAge;
    }


    public EngineTypes getEngineType()
    {
        return engineType;
    }

    public static class CarDTOBuilder
    {
        private Long id;
        private String licensePlate;
        private Integer seatCount;
        private Boolean convertible;
        private Integer rating;
        private String color;
        private String modelAge;
        private EngineTypes engineType;


        public CarDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public CarDTOBuilder setLicensePlate(String licensePlate)
        {
            this.licensePlate = licensePlate;
            return this;
        }


        public CarDTOBuilder setSeatCount(Integer seatCount)
        {
            this.seatCount = seatCount;
            return this;
        }


        public CarDTOBuilder setConvertible(Boolean convertible)
        {
            this.convertible = convertible;
            return this;
        }


        public CarDTOBuilder setRating(Integer rating)
        {
            this.rating = rating;
            return this;
        }


        public CarDTOBuilder setColor(String color)
        {
            this.color = color;
            return this;
        }


        public CarDTOBuilder setModelAge(String modelAge)
        {
            this.modelAge = modelAge;
            return this;
        }


        public CarDTOBuilder setEngineType(EngineTypes engineType)
        {
            this.engineType = engineType;
            return this;
        }


        public CarDTO createCarDTO()
        {
            return new CarDTO(id, licensePlate, seatCount, convertible, rating, engineType, color, modelAge);
        }
    }

}
