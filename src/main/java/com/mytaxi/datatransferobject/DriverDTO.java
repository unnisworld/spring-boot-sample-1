package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverDTO
{
    @JsonIgnore
    private Long id;

    @NotNull(message = "Username can not be null!")
    private String username;

    @NotNull(message = "Password can not be null!")
    private String password;
    
    private OnlineStatus onlineStatus;

    private GeoCoordinate coordinate;
    
    private CarDTO car;
    

    private DriverDTO()
    {
    }


    private DriverDTO(Long id, String username, String password, OnlineStatus onlineStatus, GeoCoordinate coordinate, CarDTO car)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.onlineStatus = onlineStatus;
        this.coordinate = coordinate;
        this.car = car;
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

    
    
    public CarDTO getCar() {
		return car;
	}


	@Override
	public String toString() {
		return "DriverDTO [id=" + id + ", username=" + username + ", password=" + password + ", onlineStatus="
				+ onlineStatus + ", coordinate=" + coordinate + ", car=" + car + "]";
	}


	public static class DriverDTOBuilder
    {
        private Long id;
        private String username;
        private String password;
        private OnlineStatus onlineStatus;
        private GeoCoordinate coordinate;
        private CarDTO car;


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
        
        
        public DriverDTOBuilder setOnlineStatus(OnlineStatus onlineStatus)
        {
            this.onlineStatus = onlineStatus;
            return this;
        }


        public DriverDTOBuilder setCoordinate(GeoCoordinate coordinate)
        {
            this.coordinate = coordinate;
            return this;
        }

        public DriverDTOBuilder setCar(CarDTO car) 
        {
        	this.car = car;
        	return this;
        }

        public DriverDTO createDriverDTO()
        {
            return new DriverDTO(id, username, password, onlineStatus, coordinate, car);
        }

    }
}
