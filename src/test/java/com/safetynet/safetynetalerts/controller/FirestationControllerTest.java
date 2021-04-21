package com.safetynet.safetynetalerts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.safetynetalerts.dao.IFirestationRepository;
import com.safetynet.safetynetalerts.dao.FirestationRepository;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.service.FirestationService;

@WebMvcTest(controllers = FirestationController.class)
public class FirestationControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	//@MockBean
	//private IFirestationRepository iFirestationRepository;
	
	@MockBean
	private FirestationService FirestationService;
	
	@BeforeEach
	private void setUpPerTest() {
		//FirestationService = new FirestationService();
		/*
		try {
			when(iFirestationRepository.saveFirestation(any(Firestation.class)))
		}
		*/
	}
	
	@Test
	public void testPostFirestation() throws Exception {
		//GIVEN
	    String firestation = "{ \"address\":\"addressTest\",\"station\":5}";
	      
	    //WHEN, THEN
	    mockMvc.perform(post("/firestation")
	    		.contentType(MediaType.APPLICATION_JSON)
	    		.content(firestation)
	    		.characterEncoding("utf-8"))
				.andExpect(status().isCreated()).andReturn();
	    		
	}
	
	@Test
	public void testPutFirestation() throws Exception {
		//GIVEN
	    String firestation1 = "{ \"address\":\"addressTest\",\"station\":5}";
	    String firestation2 = "{ \"address\":\"addressTest\",\"station\":6}";
	    
	    mockMvc.perform(post("/firestation")
	    		.contentType(MediaType.APPLICATION_JSON)
	    		.content(firestation1)
	    		.characterEncoding("utf-8"));
	    
	      
	    //WHEN, THEN
	    mockMvc.perform(put("/firestation")
	    		.contentType(MediaType.APPLICATION_JSON)
	    		.content(firestation2).characterEncoding("utf-8"))
	    		.andExpect(status().isCreated()).andReturn();
	    		
	}
	
	
	@Test
	public void testDeleteFirestation() throws Exception {
		//GIVEN
	    String firestation = "{ \"address\":\"addressTest\",\"station\":6}";
	     
	    mockMvc.perform(post("/firestation")
	    		.contentType(MediaType.APPLICATION_JSON)
	    		.content(firestation)
	    		.characterEncoding("utf-8"));
	    
	    //WHEN, THEN
	    mockMvc.perform(delete("/firestation")
	    		.contentType(MediaType.APPLICATION_JSON)
	    		.content(firestation)
	    		.characterEncoding("utf-8"))
	    		.andExpect(status().isOk()).andReturn();
	    		
	}
	
	
	
}
