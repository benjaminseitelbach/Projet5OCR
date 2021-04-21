package com.safetynet.safetynetalerts.integration.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.safetynetalerts.integration.service.DataBasePrepareService;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.Person;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class FirestationControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	private static DataBasePrepareService dataBasePrepareService;

	private static String addressTest;

	@BeforeAll
	private static void setUp() throws Exception {
		System.out.println("Set up");
		dataBasePrepareService = new DataBasePrepareService();
		dataBasePrepareService.clearDataBaseEntries();
		addressTest = "AddressTest";
	}
	
	@AfterAll
	private static void tearDown() {
		dataBasePrepareService.clearDataBaseEntries();
	}

	@Test
	@Order(1)
	public void saveFirestationIT() throws Exception {
		// GIVEN
		Firestation firestation = new Firestation();
		firestation.setAddress(addressTest);
		firestation.setStation(5);

		// WHEN, THEN
		mockMvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON).content(firestation.toJson())
				.characterEncoding("utf-8")).andExpect(status().isCreated()).andReturn();

	}
	
	@Test
	@Order(2)
	public void updateFirestationIT() throws Exception {
		// GIVEN
		Firestation firestation = new Firestation();
		firestation.setAddress(addressTest);
		firestation.setStation(6);

		// WHEN, THEN
		mockMvc.perform(put("/firestation").contentType(MediaType.APPLICATION_JSON).content(firestation.toJson())
				.characterEncoding("utf-8")).andExpect(status().isCreated()).andReturn();

	}
	
	@Test
	@Order(3)
	public void deleteFirestationIT() throws Exception {
		// GIVEN
		Firestation firestation = new Firestation();
		firestation.setAddress(addressTest);
		firestation.setStation(6);

		// WHEN, THEN
		mockMvc.perform(delete("/firestation").contentType(MediaType.APPLICATION_JSON).content(firestation.toJson())
				.characterEncoding("utf-8")).andExpect(status().isOk()).andReturn();

	}
}
