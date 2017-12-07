package com.concepttest.datatransferobject;

import javax.validation.constraints.NotNull;

import com.concepttest.domainvalue.GeoCoordinate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverDTO
{
    @JsonIgnore
    private Long id;

    @NotNull(message = "Username can not be null!")
    private String username;

    @NotNull(message = "Password can not be null!")
    private String password;

    private GeoCoordinate coordinate;

    private CarDTO carDTO;


    private DriverDTO()
    {}

    private DriverDTO(Long id, String username, String password, GeoCoordinate coordinate, CarDTO carDTO)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.coordinate = coordinate;
        this.carDTO = carDTO;
    }


    public static DriverDTOBuilder newBuilder()
    {
        return new DriverDTOBuilder();
    }


    @JsonProperty
    public Long getId()
    {
        return id;
    }


    public String getUsername()
    {
        return username;
    }


    public String getPassword()
    {
        return password;
    }


    public GeoCoordinate getCoordinate()
    {
        return coordinate;
    }


    public CarDTO getCarDTO()
    {
        return carDTO;
    }

    public static class DriverDTOBuilder
    {
        private Long id;
        private String username;
        private String password;
        private GeoCoordinate coordinate;
        private CarDTO carDTO;


        public DriverDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public DriverDTOBuilder setUsername(String username)
        {
            this.username = username;
            return this;
        }


        public DriverDTOBuilder setPassword(String password)
        {
            this.password = password;
            return this;
        }


        public DriverDTOBuilder setCoordinate(GeoCoordinate coordinate)
        {
            this.coordinate = coordinate;
            return this;
        }


        public void setCarDTO(CarDTO carDTO)
        {
            this.carDTO = carDTO;
        }


        public DriverDTO createDriverDTO()
        {
            return new DriverDTO(id, username, password, coordinate, carDTO);
        }
        
        

    }
}
