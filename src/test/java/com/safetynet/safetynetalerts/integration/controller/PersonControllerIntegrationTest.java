package com.safetynet.safetynetalerts.integration.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.safetynetalerts.integration.service.DataBasePrepareService;
import com.safetynet.safetynetalerts.model.Person;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	private static DataBasePrepareService dataBasePrepareService;

	private static String firstNameTest;
	private static String lastNameTest;

	@BeforeAll
	private static void setUp() throws Exception {
		System.out.println("Set up");
		dataBasePrepareService = new DataBasePrepareService();
		dataBasePrepareService.clearDataBaseEntries();
		firstNameTest = "FirstNameTest";
		lastNameTest = "LastNameTest";
	}

	@Test
	public void savePersonIT() throws Exception {
		// GIVEN
		Person person = new Person();
		person.setFirstName(firstNameTest);
		person.setLastName(lastNameTest);
		person.setAddress("AddressTest");
		person.setCity("CityTest");
		person.setZip("ZipTest");
		person.setPhone("PhoneTest");
		person.setEmail("EmailTest");

		// WHEN, THEN
		mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(person.toJson())
				.characterEncoding("utf-8")).andExpect(status().isCreated()).andReturn();

	}

	/*
	@Test 
	public void updatePersonIT() throws Exception{ 
			
	  Person person = new Person(); 
	  person.setFirstName(firstNameTest);
	  person.setLastName(lastNameTest); 
	  person.setAddress("NewAddressTest");
	  person.setCity("NewCityTest"); 
	  person.setZip("NewZipTest");
	  person.setPhone("NewPhoneTest"); 
	  person.setEmail("NewEmailTest");
	  
	  //WHEN, THEN 
	  mockMvc.perform(
	  put("/person").contentType(MediaType.APPLICATION_JSON).content(person.toJson()).characterEncoding("utf-8"))
	  	.andExpect(status().isOk()).andReturn();
	  
	  
	}

	@Test
	public void deletePersonIT() throws Exception {
		// GIVEN
		Person person = new Person();
		person.setFirstName(firstNameTest);
		person.setLastName(lastNameTest);
		person.setAddress("AddressTest");
		person.setCity("CityTest");
		person.setZip("ZipTest");
		person.setPhone("PhoneTest");
		person.setEmail("EmailTest");
		
		mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(person.toJson())
				.characterEncoding("utf-8"));
		
		// WHEN, THEN
		mockMvc.perform(delete("/person/FirstNameTestLastNameTest").contentType(MediaType.APPLICATION_JSON))
			//.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andReturn();
	}
	*/

}
