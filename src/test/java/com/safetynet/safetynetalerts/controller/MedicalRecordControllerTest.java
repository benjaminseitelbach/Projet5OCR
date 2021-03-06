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

import com.safetynet.safetynetalerts.dao.IMedicalRecordRepository;
import com.safetynet.safetynetalerts.dao.MedicalRecordRepository;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.MedicalRecordService;

@WebMvcTest(controllers = MedicalRecordController.class)
public class MedicalRecordControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	//@MockBean
	//private IMedicalRecordRepository iMedicalRecordRepository;
	
	@MockBean
	private MedicalRecordService MedicalRecordService;
	
	
	@Test
	public void testPostMedicalRecord() throws Exception {
		//GIVEN
	    String medicalRecord = "{ \"firstName\":\"newFirstName\",\"lastName\":\"newLastName\"}";
	      
	    //WHEN, THEN
	    mockMvc.perform(post("/medicalrecord")
	    		.contentType(MediaType.APPLICATION_JSON)
	    		.content(medicalRecord)
	    		.characterEncoding("utf-8"))
				.andExpect(status().isCreated()).andReturn();

	    		
	}
	
	@Test
	public void testPutMedicalRecord() throws Exception {
		//GIVEN
	    String medicalRecord = "{ \"firstName\":\"newFirstName\",\"lastName\":\"newLastName\"}";
	     
	    mockMvc.perform(post("/medicalrecord")
	    		.contentType(MediaType.APPLICATION_JSON)
	    		.content(medicalRecord)
	    		.characterEncoding("utf-8"));
	    
	    //WHEN, THEN
	    mockMvc.perform(put("/medicalrecord")
	    		.contentType(MediaType.APPLICATION_JSON)
	    		.content(medicalRecord).characterEncoding("utf-8"))
	    		.andExpect(status().isCreated()).andReturn();
	    		
	}
	
	
	
	@Test
	public void testDeleteMedicalRecord() throws Exception {
		//GIVEN
	    String medicalRecord = "{ \"firstName\":\"newFirstName\",\"lastName\":\"newLastName\"}";
	     
	    mockMvc.perform(post("/medicalrecord")
	    		.contentType(MediaType.APPLICATION_JSON)
	    		.content(medicalRecord)
	    		.characterEncoding("utf-8"));
	    
	    //WHEN, THEN
	    mockMvc.perform(delete("/medicalrecord")
	    		.contentType(MediaType.APPLICATION_JSON)
	    		.content(medicalRecord)
	    		.characterEncoding("utf-8"))
	    		.andExpect(status().isOk()).andReturn();
	    		
	}
	
	
	
}
