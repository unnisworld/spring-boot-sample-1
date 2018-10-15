package com.mytaxi.service.car;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceTest 
{
    @Autowired
    private CarService carService;
    
	 @Before
	 public void beforeEachTest() 
	 {
		 System.out.println("Inside beforeEachTest");
	 }

	 @Test
	 public void testCreateCarUsecase() 
	 {
		 System.out.println("Inside testCreateCarUsecase");
		 // TODO : Add code to test the service layer.
	 }
}
