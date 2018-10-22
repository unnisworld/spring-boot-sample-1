package com.mytaxi.datatransferobject;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.Manufacturer;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO 
{
    //@JsonIgnore
    private Long id;

    @NotNull(message = "license plate can not be null!")
    private String licensePlate;
    
    private Float rating;
    
    private Short seatCount;
    
    private Boolean convertible;
    
    private EngineType engineType;
    
    private Manufacturer manufacturer;
    
    private CarDTO()
    {
    }


    private CarDTO(Long id, String licensePlate, Float rating, Boolean convertible, EngineType engineType, Manufacturer manufacturer, Short seatCount)
    {
        this.id = id;
        this.licensePlate = licensePlate;
        this.rating = rating;
        this.convertible = convertible;
        this.engineType = engineType;
        this.manufacturer = manufacturer;
        this.seatCount = seatCount;
    }
    
    public Long getId() 
    {
    	return id;
    }
    
	public String getLicensePlate() 
	{
		return this.licensePlate;
	}
	
	public Float getRating() 
	{
		return this.rating;
	}
	
	
	public Short getSeatCount() {
		return seatCount;
	}


	public Boolean isConvertible() {
		return convertible;
	}


	public EngineType getEngineType() {
		return engineType;
	}


	public Manufacturer getManufacturer() {
		return manufacturer;
	}


	public static CarDTOBuilder newBuilder() 
	{
		return new CarDTOBuilder();
	}
	

	@Override
	public String toString() {
		return "CarDTO [id=" + id + ", licensePlate=" + licensePlate + ", rating=" + rating + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((licensePlate == null) ? 0 : licensePlate.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarDTO other = (CarDTO) obj;
		if (licensePlate == null) {
			if (other.licensePlate != null)
				return false;
		} else if (!licensePlate.equals(other.licensePlate))
			return false;
		return true;
	}



	public static class CarDTOBuilder 
	{
        private Long id;
        private String licensePlate;
        private Float rating;
        private Short seatCount;
        private Boolean convertible;
        private EngineType engineType;
        private Manufacturer manufacturer;
        
        
		public Long getId() 
		{
			return id;
		}
		
		
		public CarDTOBuilder setId(Long id) 
		{
			this.id = id;
			return this;
		}
		
		public CarDTOBuilder setLicenseplate(String licenseplate) 
		{
			this.licensePlate = licenseplate;
			return this;
		}
		
		public CarDTOBuilder setRating(Float rating) 
		{
			this.rating = rating;
			return this;
		}


		public CarDTOBuilder setConvertible(Boolean convertible) {
			this.convertible = convertible;
			return this;
		}


		public CarDTOBuilder setEngineType(EngineType engineType) {
			this.engineType = engineType;
			return this;
		}


		public CarDTOBuilder setManufacturer(Manufacturer manufacturer) {
			this.manufacturer = manufacturer;
			return this;
		}
		
		public CarDTOBuilder setSeatCount(Short seatCount) {
			this.seatCount = seatCount;
			return this;
		}


		public CarDTO createCarDTO()
        {
            return new CarDTO(id, licensePlate, rating, convertible, engineType, manufacturer, seatCount);
        }
       
	}

}
