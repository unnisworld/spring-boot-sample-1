package com.mytaxi.domainvalue;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Manufacturer 
{
    @Column(name = "manufacturer")
    private String manufacturer;

    protected Manufacturer() {
    }

    public Manufacturer(String manufacturer) 
    {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() 
    {
        return manufacturer;
    }

    
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
		return result;
	}

	
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manufacturer other = (Manufacturer) obj;
		if (manufacturer == null) {
			if (other.manufacturer != null)
				return false;
		} else if (!manufacturer.equals(other.manufacturer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Manufacturer [manufacturer=" + manufacturer + "]";
	}


}