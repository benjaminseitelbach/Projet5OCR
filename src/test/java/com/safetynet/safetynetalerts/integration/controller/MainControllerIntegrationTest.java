package com.safetynet.safetynetalerts.integration.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import com.safetynet.safetynetalerts.init.Initialization;
import com.safetynet.safetynetalerts.integration.service.DataBasePrepareService;


@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	private static DataBasePrepareService dataBasePrepareService;
	
	@BeforeAll
	private static void setUp() throws Exception {
		System.out.println("Set up");
		dataBasePrepareService = new DataBasePrepareService();
		dataBasePrepareService.clearDataBaseEntries();
		Initialization.init();
	}
	
	@Test
	public void getPersonsByStationNumber1Test() throws Exception {
		mockMvc.perform(get("/firestation?stationNumber=1"))
			.andExpect(status().isOk());

	}
	
	@Test
	public void getChildAlertByAddress1509CulverSt () throws Exception {
		mockMvc.perform(get("/childAlert?address=1509 Culver St"))
		.andExpect(status().isOk());
	}
		
	@Test
	public void getPhoneAlertByStation1 () throws Exception {
		mockMvc.perform(get("/phoneAlert?firestation=1"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void getPersonsByAddress1509CulverSt () throws Exception {
		mockMvc.perform(get("/fire?address=1509 Culver St"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void getPersonsByStations1 () throws Exception {
		mockMvc.perform(get("/flood/stations?stations=1"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void getPersonInfoJohnBoyd () throws Exception {
		mockMvc.perform(get("/personInfo?firstName=John&lastName=Boyd"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void getEmailsByCityCulver () throws Exception {
		mockMvc.perform(get("/communityEmail?city=Culver"))
		.andExpect(status().isOk());
	}
	
}

