package com.mytaxi.service.car;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

public interface CarService 
{

	public CarDO find(Long carId) throws EntityNotFoundException;

	public CarDO create(CarDO carDO) throws ConstraintsViolationException;

	public void delete(Long carId) throws EntityNotFoundException;

	public Iterable<CarDO> getCars();

}
