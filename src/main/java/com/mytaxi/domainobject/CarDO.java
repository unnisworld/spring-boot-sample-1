package com.mytaxi.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.domainvalue.Manufacturer;

@Entity
@Table(
    name = "car",
    uniqueConstraints = @UniqueConstraint(name = "uc_licenseplate", columnNames = {"licensePlate"})
)
public class CarDO 
{
    @Id
    @SequenceGenerator(name = "carSeqGen", sequenceName = "carSeq", initialValue=100, allocationSize=1)
    @GeneratedValue(generator = "carSeqGen")
    private Long id;
    
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();
    
    @Column(nullable = false)
    @NotNull(message = "License can not be null!")
    private String licensePlate;
    
    @Column(nullable = false)
    @NotNull(message = "Seat count can not be null!")
    private Short seatCount;
    
    @Column(nullable = false)
    private Boolean convertible;
    
    @Column
    private Float rating;
    
    @Enumerated(EnumType.STRING)
    @Column
    private EngineType engineType;
    
    @Embedded
    private Manufacturer manufacturer;
    
    @Column(nullable = false)
    private Boolean deleted = false;
    
    
    public CarDO()
    {
    }
    
    
    public CarDO(String licensePlate, Float rating, EngineType engineType, Manufacturer manufacturer, Boolean convertible, Short seatCount)
    {
        this.licensePlate = licensePlate;
        this.rating = rating;
        this.engineType = engineType;
        this.manufacturer = manufacturer;
        this.convertible = convertible;
        this.seatCount = seatCount;
        this.deleted = false;
    }
    
    
    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }
    
    
    public String getLicensePlate()
    {
        return licensePlate;
    }
 
    
    public Short getSeatCount() 
    {
		return seatCount;
	}


	public void setSeatCount(Short seatCount) 
	{
		this.seatCount = seatCount;
	}


	public Float getRating() 
	{
		return rating;
	}


	public void setRating(Float rating) 
	{
		this.rating = rating;
	}


	public Boolean getConvertible() 
	{
		return convertible;
	}


	public EngineType getEngineType() 
	{
		return engineType;
	}
	

	public Manufacturer getManufacturer() {
		return manufacturer;
	}


	public Boolean getDeleted()
    {
        return deleted;
    }


    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
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
		CarDO other = (CarDO) obj;
		if (licensePlate == null) {
			if (other.licensePlate != null)
				return false;
		} else if (!licensePlate.equals(other.licensePlate))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "CarDO [id=" + id + ", dateCreated=" + dateCreated + ", licensePlate=" + licensePlate + ", seatCount="
				+ seatCount + ", convertible=" + convertible + ", rating=" + rating + ", engineType=" + engineType
				+ ", manufacturer=" + manufacturer + ", deleted=" + deleted + "]";
	}
    
    
   
}
