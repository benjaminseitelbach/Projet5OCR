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

import com.safetynet.safetynetalerts.dao.IPersonRepository;
import com.safetynet.safetynetalerts.dao.PersonRepository;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.PersonService;

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	//@MockBean
	//private IPersonRepository iPersonRepository;
	
	@MockBean
	private PersonService personService;
	
	@BeforeEach
	private void setUpPerTest() {
		//personService = new PersonService();
		/*
		try {
			when(iPersonRepository.savePerson(any(Person.class)))
		}
		*/
	}
	
	@Test
	public void testPostPerson() throws Exception {
		//GIVEN
	    String person = "{ \"firstName\":\"newFirstName\",\"lastName\":\"newLastName\"}";
	      
	    //WHEN, THEN
	    mockMvc.perform(post("/person").
	    		contentType(MediaType.APPLICATION_JSON).
	    		content(person).
	    		characterEncoding("utf-8"))
				//.andExpect(status().isCreated()).andReturn();
	    		.andExpect(status().isOk()).andReturn();
	    		
	}
	
	@Test
	public void testPutPerson() throws Exception {
		//GIVEN
	    String person = "{ \"firstName\":\"newFirstName\",\"lastName\":\"newLastName\"}";
	      
	    //WHEN, THEN
	    mockMvc.perform(put("/person").
	    		contentType(MediaType.APPLICATION_JSON).
	    		content(person).characterEncoding("utf-8"))
	    		.andExpect(status().isOk()).andReturn();
	    		
	}
	
	@Test
	public void testDeletePerson() throws Exception {
		//GIVEN
	    String person = "{ \"firstName\":\"newFirstName\",\"lastName\":\"newLastName\"}";
	     
	    mockMvc.perform(post("/person").
	    		contentType(MediaType.APPLICATION_JSON).
	    		content(person).
	    		characterEncoding("utf-8"));
	    
	    //WHEN, THEN
	    mockMvc.perform(delete("/person/{firstName}{lastName}", "newFirstName", "newLastName").
	    		contentType(MediaType.APPLICATION_JSON).
	    		characterEncoding("utf-8"))
	    		.andExpect(status().isOk()).andReturn();
	    		
	}
	
	
}
