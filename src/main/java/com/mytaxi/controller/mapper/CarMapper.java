package com.mytaxi.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.GeoCoordinate;

public class CarMapper 
{

	public static CarDO makeCarDO(CarDTO carDTO) 
	{
		return new CarDO(carDTO.getLicenseplate());
	}
	
	
	public static CarDTO makeCarDTO(CarDO carDO) 
	{
		
        CarDTO.CarDTOBuilder carDTOBuilder = CarDTO.newBuilder()
                .setId(carDO.getId())
                .setLicenseplate(carDO.getLicenseplate());

            CarDTO carDTO = carDTOBuilder.createCarDTO();
            
            return carDTO;
	}



	public static List<CarDTO> makeCarDTOList(Collection<CarDO> cars) 
	{
        return cars.stream()
                .map(CarMapper::makeCarDTO)
                .collect(Collectors.toList());
	}


}
