package com.mytaxi.dataaccessobject;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;

import com.mytaxi.datatransferobject.DriverSearchCriteriaDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverDO, Long>, JpaSpecificationExecutor<DriverDO>
{
	@EntityGraph("joinedDriver")
    List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);
	
	DriverDO findByCar(CarDO car);
    
    @EntityGraph("joinedDriver")
    List<DriverDO> findAll(@Nullable Specification<DriverDO> spec);
}
