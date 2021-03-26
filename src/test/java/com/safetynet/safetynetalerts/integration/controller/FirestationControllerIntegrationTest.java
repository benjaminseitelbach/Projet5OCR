package com.safetynet.safetynetalerts.integration.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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
public class FirestationControllerIntegrationTest {

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
	public void saveFirestationIT() throws Exception {
		// GIVEN
		Firestation firestation = new Firestation();
		firestation.setAddress("AddressTest");
		firestation.setStation(5);

		// WHEN, THEN
		mockMvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON).content(firestation.toJson())
				.characterEncoding("utf-8")).andExpect(status().isCreated()).andReturn();

	}
}
