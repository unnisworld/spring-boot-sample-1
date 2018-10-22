package com.mytaxi.dataaccessobject;


import com.mytaxi.domainobject.CarDO;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository  extends CrudRepository<CarDO, Long>{
	
	@EntityGraph("joined")
	Iterable<CarDO> findAll();
}
