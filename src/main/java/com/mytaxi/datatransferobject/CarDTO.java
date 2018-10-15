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
    
    private CarDTO()
    {
    }


    private CarDTO(Long id, String licensePlate)
    {
        this.id = id;
        this.licensePlate = licensePlate;
    }
    
    public Long getId() 
    {
    	return id;
    }
    
	public String getLicensePlate() 
	{
		return this.licensePlate;
	}

	
	public static CarDTOBuilder newBuilder() 
	{
		return new CarDTOBuilder();
	}
	

	@Override
	public String toString() {
		return "CarDTO [id=" + id + ", licensePlate=" + licensePlate + "]";
	}


	public static class CarDTOBuilder 
	{
        private Long id;
        private String licensePlate;
        
        
		public Long getId() 
		{
			return id;
		}
		
		
		public CarDTOBuilder setId(Long id) 
		{
			this.id = id;
			
			return this;
		}
		
		
		public String getLicensePlate() 
		{
			return licensePlate;
		}
		
		
		public CarDTOBuilder setLicenseplate(String licenseplate) 
		{
			this.licensePlate = licenseplate;
			
			return this;
		}
		
        public CarDTO createCarDTO()
        {
            return new CarDTO(id, licensePlate);
        }
       
	}

}
