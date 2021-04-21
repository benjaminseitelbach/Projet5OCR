package com.safetynet.safetynetalerts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.safetynetalerts.service.URLsService;

@WebMvcTest(controllers = MainController.class)
public class MainControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private URLsService urlsService;

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