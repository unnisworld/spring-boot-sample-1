package com.mytaxi.controller;

import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.controller.mapper.DriverSearchMapper;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.datatransferobject.DriverSearchDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.CarNotFoundException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DriverNotOnlineException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.driver.DriverService;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController
{

    private final DriverService driverService;


    @Autowired
    public DriverController(final DriverService driverService)
    {
        this.driverService = driverService;
    }


    @GetMapping("/{driverId}")
    public DriverDTO getDriver(@PathVariable long driverId) throws EntityNotFoundException
    {
        return DriverMapper.makeDriverDTO(driverService.find(driverId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException
    {
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        return DriverMapper.makeDriverDTO(driverService.create(driverDO));
    }


    @DeleteMapping("/{driverId}")
    public void deleteDriver(@PathVariable long driverId) throws EntityNotFoundException
    {
        driverService.delete(driverId);
    }


    @PutMapping("/{driverId}")
    public void updateLocation(
        @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
        throws EntityNotFoundException
    {
        driverService.updateLocation(driverId, longitude, latitude);
    }


    @GetMapping
    public List<DriverDTO> findDrivers(@RequestParam OnlineStatus onlineStatus)
    {
        return DriverMapper.makeDriverDTOList(driverService.find(onlineStatus));
    }
    
    
    @PutMapping("/{driverId}/car")
    public void selectCar(@PathVariable long driverId, @RequestParam long carId) throws CarAlreadyInUseException, CarNotFoundException, DriverNotOnlineException
    {
    	driverService.selectCar(driverId, carId);
    }
    
    @DeleteMapping("/{driverId}/car")
    public void deselectCar(@PathVariable long driverId) throws DriverNotOnlineException
    {
    	driverService.deselectCar(driverId);
    }
    
    @PostMapping("/search")
    public List<DriverDTO> search(@Valid @RequestBody DriverSearchDTO driverSearchDTO) 
    {
    	System.out.println(driverSearchDTO);
    	DriverDO exampleDriverDO = DriverSearchMapper.makeExampleDriverDO(driverSearchDTO);
    	Iterable<DriverDO> matchingDrivers = driverService.search(exampleDriverDO);
    	
    	// DriverMapper requires a List.
    	List<DriverDO> drivers = new ArrayList<>();
    	matchingDrivers.forEach(drivers::add);
    	
    	return DriverMapper.makeDriverDTOList(drivers);
    }
    
}
