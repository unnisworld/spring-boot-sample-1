package com.mytaxi.datatransferobject;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.GeoCoordinate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO 
{
    //@JsonIgnore
    private Long id;

    @NotNull(message = "license plate can not be null!")
    private String licensePlate;
    
    private Float rating;
    
    private CarDTO()
    {
    }


    private CarDTO(Long id, String licensePlate, Float rating)
    {
        this.id = id;
        this.licensePlate = licensePlate;
        this.rating = rating;
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
		
        public CarDTO createCarDTO()
        {
            return new CarDTO(id, licensePlate, rating);
        }
       
	}

}
