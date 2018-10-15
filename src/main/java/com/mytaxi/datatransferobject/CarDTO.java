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

    @NotNull(message = "licenseplate can not be null!")
    private String licenseplate;
    
    private CarDTO()
    {
    }


    private CarDTO(Long id, String licenseplate)
    {
        this.id = id;
        this.licenseplate = licenseplate;
    }
    
    public Long getId() 
    {
    	return id;
    }
    
	public String getLicenseplate() 
	{
		return this.licenseplate;
	}

	
	public static CarDTOBuilder newBuilder() 
	{
		return new CarDTOBuilder();
	}
	
	
	public static class CarDTOBuilder 
	{
        private Long id;
        private String licenseplate;
        
        
		public Long getId() 
		{
			return id;
		}
		
		
		public CarDTOBuilder setId(Long id) 
		{
			this.id = id;
			
			return this;
		}
		
		
		public String getLicenseplate() 
		{
			return licenseplate;
		}
		
		
		public CarDTOBuilder setLicenseplate(String licenseplate) 
		{
			this.licenseplate = licenseplate;
			
			return this;
		}
		
        public CarDTO createCarDTO()
        {
            return new CarDTO(id, licenseplate);
        }
       
	}

}
