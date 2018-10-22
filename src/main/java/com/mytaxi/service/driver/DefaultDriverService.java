package com.mytaxi.service.driver;

//import static com.mytaxi.dataaccessobject.DriverRepository.Specifications.licensePlateLike;
import static com.mytaxi.dataaccessobject.DriverQuerySpecifications.buildQuerySpecification;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.datatransferobject.DriverSearchCriteriaDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DriverNotOnlineException;
import com.mytaxi.exception.EmptySearchCriteriaException;
import com.mytaxi.exception.EntityNotFoundException;

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
     * @param driverId
     * @param carId
     * 
     * @throws EntityNotFoundException
     * @throws DriverNotOnlineException
     * @throws CarAlreadyInUseException
     */
    @Transactional
    public void selectCar(long driverId, long carId) 
    		throws EntityNotFoundException, DriverNotOnlineException, CarAlreadyInUseException 
    {
        DriverDO driver = findDriverChecked(driverId);
        
    	CarDO car = findCarChecked(carId);
        
        if ( isNotOnline(driver) ) 
        {
        	throw new DriverNotOnlineException("Status of driver with Id ["+ driverId +" is not ONLINE.");
        }
        
        if( carAlreadyAssignedToAnotherDriver(car, driverId) )
        {
            throw new CarAlreadyInUseException("Car with Id [" + carId + "] is already assigned to another driver.");
        }
        
        if( carAlreadyAssignedToThisDriver(car, driverId) )
        {
        	LOG.info("Skipping database update as the car is already assigned to this driver.");
        	return;
        }
        
        driver.setCar(car);
        driverRepository.save(driver);
    }

    
    
    /**
     * De-select a car.
     * @param driverId
     * 
     * @throws EntityNotFoundException
     */
    @Transactional
    public void deselectCar(long driverId) throws EntityNotFoundException
    { 
        DriverDO driver = findDriverChecked(driverId);
        
        if (driver.getCar() != null) 
        {
        	driver.setCar(null);
        	driverRepository.save(driver);
        	LOG.info("Unassigned car.");
        }
    }
    
    /**
     * Search for drivers matching the Search criteria.
     * @param driverSearchDTO
     * 
     * @throws EmptySearchCriteriaException
     */
    public List<DriverDO> search(DriverSearchCriteriaDTO driverSearchDTO) throws EmptySearchCriteriaException
    {
    	Specification<DriverDO> spec = buildQuerySpecification(driverSearchDTO).orElseThrow(EmptySearchCriteriaException::new);
    	
    	System.out.println("About to call search.");
    	List<DriverDO> searchResult = driverRepository.findAll(spec);
    	System.out.println("Search returned.");
    	return searchResult;
    }

    
    private DriverDO findDriverChecked(Long driverId) throws EntityNotFoundException
    {
        return driverRepository.findById(driverId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find driver with id: " + driverId));
    }
    
    
	private CarDO findCarChecked(long carId) throws EntityNotFoundException 
	{
		return carRepository.findById(carId)
				.orElseThrow(() -> new EntityNotFoundException("Could not find car with id: " + carId ));
	}
    
    
	private boolean carAlreadyAssignedToAnotherDriver(CarDO car, long driverId) 
	{
		return car.getDriver() !=null && car.getDriver().getId() != driverId;
	}
	
	
	private boolean carAlreadyAssignedToThisDriver(CarDO car, long driverId) 
	{
		return car.getDriver() !=null && car.getDriver().getId() == driverId;
	}


	private boolean isNotOnline(DriverDO driver) 
	{
		return !(OnlineStatus.ONLINE.equals(driver.getOnlineStatus()));
	}
    
}
