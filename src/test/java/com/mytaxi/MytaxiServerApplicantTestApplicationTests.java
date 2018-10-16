package com.mytaxi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytaxi.controller.CarController;
import com.mytaxi.datatransferobject.CarDTO;


@RunWith(SpringJUnit4ClassRunner.class)
// TODO : Fix - this annotation is causing exception.
//@JsonTest
@SpringBootTest(classes = MytaxiServerApplicantTestApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class MytaxiServerApplicantTestApplicationTests
{
	@LocalServerPort
    private int port;
	
	@Autowired
    private TestRestTemplate restTemplate;

	@Autowired
    private CarController controller;

//	@Autowired private JacksonTester<CarDTO> json;
	
    @Test
    public void contextLoads()
    {
    	assertThat(controller).isNotNull();
    }
    
    
    @Test
    public void testGetAllCars() throws IOException, ClassNotFoundException 
    {
    	List<CarDTO> expected = createExpectedCarsListForTestGetAllCars();
    			
    	String resultJson = this.restTemplate.getForObject("http://localhost:" + port + "/v1/cars",
                String.class);
    	
    	List<CarDTO> actual = getCarDTOListFromJsonString(resultJson);
    	
    	assertThat(actual, is(expected));
    }
    
    
    
    private List<CarDTO> createExpectedCarsListForTestGetAllCars() 
    {
    	List<CarDTO> cars = new ArrayList<CarDTO>(3);
    	CarDTO car1 = CarDTO.newBuilder().setId(Long.valueOf("1")).setLicenseplate("KL 07 AS 4444").createCarDTO();
    	CarDTO car2 = CarDTO.newBuilder().setId(Long.valueOf("2")).setLicenseplate("KA 26 BC 1123").createCarDTO();
    	CarDTO car3 = CarDTO.newBuilder().setId(Long.valueOf("3")).setLicenseplate("TN 03 CE 6657").createCarDTO();
    	
    	cars.add(car1);
    	cars.add(car2);
    	cars.add(car3);
    	
    	return cars;
    }
    
    
    // TODO : Temporary fix for @JsonTest issue.
    private List<CarDTO> getCarDTOListFromJsonString(String jsonString) 
    		throws ClassNotFoundException, JsonParseException, JsonMappingException, IOException 
    {
    	ObjectMapper mapper = new ObjectMapper();
    	Class<?> clz = Class.forName("com.mytaxi.datatransferobject.CarDTO");
    	JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, clz);
    	
    	List<CarDTO> resultList = mapper.readValue(jsonString, type);
    	
    	return resultList;
    }

}
