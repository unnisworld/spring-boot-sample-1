package com.mytaxi.service.driver;

import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.datatransferobject.DriverSearchDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.CarNotFoundException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DriverNotOnlineException;
import com.mytaxi.exception.EntityNotFoundException;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.mytaxi.dataaccessobject.DriverRepository.Specifications.*;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverService implements DriverService
{

    private static final Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final DriverRepository driverRepository;
    private final CarRepository carRepository;


    public DefaultDriverService(final DriverRepository driverRepository, final CarRepository carRepository)
    {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
    }


    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverDO find(Long driverId) throws EntityNotFoundException
    {
        return findDriverChecked(driverId);
    }


    /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    public DriverDO create(DriverDO driverDO) throws ConstraintsViolationException
    {
        DriverDO driver;
        try
        {
            driver = driverRepository.save(driverDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("ConstraintsViolationException while creating a driver: {}", driverDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setDeleted(true);
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverDO> find(OnlineStatus onlineStatus)
    {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }

    /**
     * Select a car for the driver.	
     * @throws CarAlreadyInUseException 
     * @throws CarNotFoundException 
     * @throws DriverNotOnlineException 
     */
    @Transactional
    public void selectCar(long driverId, long carId) throws CarAlreadyInUseException, CarNotFoundException, DriverNotOnlineException 
    {
    	CarDO car = carRepository.findById(carId).get();
        if (car == null) 
        {
            throw new CarNotFoundException("Car with Id [" + carId + "] not found.");
        }
        
        DriverDO driver = driverRepository.findById(driverId).get();
        
//        List<DriverDO> drivers = driverRepository.findByAttributes(driver.getUsername(), driver.getOnlineStatus(), car.getLicensePlate());
//        System.out.println("FindByAttribute returned "+ drivers);
        
        // TODO : handle driver == null scenario
        
        if ( !(OnlineStatus.ONLINE.equals(driver.getOnlineStatus())) ) 
        {
        	throw new DriverNotOnlineException("Status of driver with Id ["+ driverId +" is not ONLINE.");
        }
        
        if(car.getDriver() !=null && car.getDriver().getId() != driverId)
        {
            throw new CarAlreadyInUseException("Car with Id [" + carId + "] is already assigned to another driver.");
        }
        
        if(driver.getCar() != null)
        {
        	// TODO : throw exception
        	LOG.warn("TODO: Handle unassignCar case.");
        }
        
        driver.setCar(car);
        driverRepository.save(driver);
        
        LOG.info("Assigned car to driver  : {}", driver);
    }
    
    @Transactional
    public void deselectCar(long driverId) throws DriverNotOnlineException
    { 
        DriverDO driver = driverRepository.findById(driverId).get();
        
        System.out.println("Got driver from repo "+ driver);
        
        // TODO : handle driver == null
        
        if ( !(OnlineStatus.ONLINE.equals(driver.getOnlineStatus())) ) 
        {
        	throw new DriverNotOnlineException("Status of driver with Id ["+ driverId +" is not ONLINE.");
        }
        
        if (driver.getCar() != null) 
        {
        	driver.setCar(null);
        	driverRepository.save(driver);
        	LOG.info("Unassigned car");
        }
    }
    
    
    public List<DriverDO> search(DriverSearchDTO driverSearchDTO) 
    {
//    	ExampleMatcher matcher = ExampleMatcher.matching()
//                .withStringMatcher(StringMatcher.CONTAINING)   // Match string containing pattern   
//                .withIgnorePaths("dateCreated")
//                .withIgnoreCase();
//    	
//    	return driverRepository.findAll(Example.of(driverDO, matcher));
    	
    	// return driverRepository.findAll(usernameLike(driverSearchDTO.getUsernameContains()));
    	return driverRepository.findAll(licensePlateLike(driverSearchDTO.getLicensePlateContains()));
    }

    
    private DriverDO findDriverChecked(Long driverId) throws EntityNotFoundException
    {
        return driverRepository.findById(driverId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + driverId));
    }
    


}
