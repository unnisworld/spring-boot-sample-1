package com.mytaxi.service.driver;

import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.datatransferobject.DriverSearchCriteriaDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.CarNotFoundException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DriverNotOnlineException;
import com.mytaxi.exception.EmptySearchCriteriaException;
import com.mytaxi.exception.EntityNotFoundException;

import java.util.List;

import javax.validation.Valid;

public interface DriverService
{

    DriverDO find(Long driverId) throws EntityNotFoundException;

    DriverDO create(DriverDO driverDO) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    List<DriverDO> find(OnlineStatus onlineStatus);

	void selectCar(long driverId, long carId) throws CarAlreadyInUseException, CarNotFoundException, DriverNotOnlineException;

	void deselectCar(long driverId) throws DriverNotOnlineException;

	List<DriverDO> search(DriverSearchCriteriaDTO driverSearchDTO) throws EmptySearchCriteriaException;

}
