package com.mytaxi.controller.mapper;

import com.mytaxi.datatransferobject.DriverSearchDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;

public class DriverSearchMapper 
{
	
    public static DriverDO makeExampleDriverDO(DriverSearchDTO driverSearchDTO)
    {
        return new DriverDO(driverSearchDTO.getUsernameContains(), driverSearchDTO.getOnlineStatus(), 
        						new CarDO(driverSearchDTO.getLicensePlateContains(), driverSearchDTO.getRating())
        				   );
    }

}
