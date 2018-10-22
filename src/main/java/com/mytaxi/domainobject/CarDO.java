package com.mytaxi.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.Manufacturer;

@Entity
@Table(
    name = "car",
    uniqueConstraints = @UniqueConstraint(name = "uc_licenseplate", columnNames = {"licensePlate"})
)
@NamedEntityGraph(name = "joined", includeAllAttributes = true)
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
    
    @JsonIgnore
    // @OneToOne(mappedBy = "car", fetch = FetchType.LAZY, cascade = CascadeType.DETACH, optional=true)
    @OneToOne(mappedBy = "car", optional=true)
    @Fetch(FetchMode.JOIN)
    private DriverDO driver;
    
    @Column(nullable = false)
    private Boolean deleted = false;
    
    
    public CarDO()
    {
    }
    
    
    public CarDO(String licensePlate)
    {
        this.licensePlate = licensePlate;
        this.deleted = false;
    }
    
    // Introduced for search usecase
    public CarDO(String licensePlate, Float rating)
    {
        this.licensePlate = licensePlate;
        this.rating = rating;
        this.dateCreated = null;
        this.deleted = false;
        this.engineType = null;
        this.manufacturer = null;
        this.seatCount = null;
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
	

	public DriverDO getDriver() 
	{
		return driver;
	}


	public void setDriver(DriverDO driver) 
	{
		this.driver = driver;
	}


	public Boolean getDeleted()
    {
        return deleted;
    }


    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
    }
   
}
