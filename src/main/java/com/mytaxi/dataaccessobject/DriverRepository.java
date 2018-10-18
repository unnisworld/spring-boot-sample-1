package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import java.util.List;

import javax.persistence.criteria.Join;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverDO, Long>, JpaSpecificationExecutor<DriverDO>

//, QueryByExampleExecutor<DriverDO>
{

    List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);
    
    
    
//    @Query("SELECT d from DriverDO d WHERE UPPER(d.username) LIKE CONCAT(UPPER(:username), '%') OR d.onlineStatus = :onlineStatus OR d.car.licensePlate = :licensePlate")
//    List<DriverDO> findByAttributes(@Param("username") String username, 
//    		@Param("onlineStatus") OnlineStatus onlineStatus, 
//    		@Param("licensePlate") String licensePlate);
    
    
    class Specifications {
        public static Specification<DriverDO> usernameLike(String username) 
        {
        	System.out.println("Value of username for like is "+ username);
            return (root, query, cb) -> cb.like(root.get("username"), username + "%");
        }
        
        public static Specification<DriverDO> licensePlateLike(String licensePlate) 
        {
        	System.out.println("Value of licensePlate for like is "+ licensePlate);
        	
        	Specification<DriverDO> spec = (root, query, cb) -> {
        		
        		Join<DriverDO, CarDO> car = root.join("car");
        		return cb.like(car.get("licensePlate"), licensePlate + "%");
        	};
        	
            //return (root, query, cb) -> cb.like(root.get("car.licensePlate"), licensePlate + "%");
        	return spec;
        }

//        public Sort orderByEnrollmentDesc() {
//          return new Sort(Sort.Direction.DESC, "enrollment");
//        }
    }    
}
