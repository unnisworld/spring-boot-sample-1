package com.mytaxi.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(
    name = "car"
)
public class CarDO 
{
    @Id
    @SequenceGenerator(name = "carSeqGen", sequenceName = "carSeq", initialValue=100, allocationSize=100)
    @GeneratedValue(generator = "carSeqGen")
    private Long id;
    
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();
    
    @Column(nullable = false)
    @NotNull(message = "License can not be null!")
    private String licensePlate;
    
    @Column(nullable = false)
    private Boolean deleted = false;
    
    
    private CarDO()
    {
    }
    
    
    public CarDO(String licensePlate)
    {
        this.licensePlate = licensePlate;
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
    
    public Boolean getDeleted()
    {
        return deleted;
    }


    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
    }
   
}
