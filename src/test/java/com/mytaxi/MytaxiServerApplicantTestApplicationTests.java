package com.mytaxi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
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
    public void testCreateCars() 
    {
    	
    }
    
    
    @Test
    public void testGetAllCars() throws IOException, ClassNotFoundException 
    {
    	String resultJson = this.restTemplate.getForObject("http://localhost:" + port + "/v1/cars",
                String.class);
    	
    	List<CarDTO> allCars = getCarDTOListFromJsonString(resultJson);
    	
    	assertTrue(allCars.size() == 3);
    	// TODO : Add more assertions.
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
