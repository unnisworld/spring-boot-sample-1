package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverDO, Long>, QueryByExampleExecutor<DriverDO>
{

    List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);
    
//    @Query("SELECT d from DriverDO d WHERE UPPER(d.username) LIKE CONCAT(UPPER(:username), '%') OR d.onlineStatus = :onlineStatus OR d.car.licensePlate = :licensePlate")
//    List<DriverDO> findByAttributes(@Param("username") String username, 
//    		@Param("onlineStatus") OnlineStatus onlineStatus, 
//    		@Param("licensePlate") String licensePlate);
}
