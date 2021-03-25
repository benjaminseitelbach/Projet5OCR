package com.safetynet.safetynetalerts;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.safetynetalerts.controller.PersonController;
import com.safetynet.safetynetalerts.dao.PersonDao;

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PersonDao personDao;
	
	/*
	@Test
	public void testPostPerson() throws Exception {
		//GIVEN
	    String person = "{ \"firstName\":\"newFirstName\",\"lastName\":\"newLastName\"}";
	    //WHEN, THEN
	    mockMvc.perform(
	    		post("/person").contentType(MediaType.APPLICATION_JSON).content(person).characterEncoding("utf-8"))
				.andExpect(status().isCreated()).andReturn();
	    		//.andExpect(status().isOk()).andReturn();
	    		
	}
	*/
	
}