package com.safetynet.safetynetalerts.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.safetynet.safetynetalerts.dao.IPersonRepository;
import com.safetynet.safetynetalerts.dao.PersonRepository;
import com.safetynet.safetynetalerts.model.Person;

import static org.mockito.Mockito.*;

import java.net.URI;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
	
	private static PersonService personService;
	
	@Mock
	//@Autowired
	//private static IPersonRepository iPersonRepository;
	private static PersonRepository personRepository;
	
	@BeforeEach
	private void setUpPerTest() {
		personService = new PersonService();
		personRepository = new PersonRepository();
		/*
		try {
			when(iPersonRepository.savePerson(any(Person.class)))
		}
		*/
	}
	
	@Test
	public void savePersonTest() {
		Person person = new Person();
		person.setFirstName("FirstNameTest");
		person.setLastName("LastNameTest");
		person.setAddress("AddressTest");
		person.setCity("CityTest");
		person.setZip("ZipTest");
		person.setPhone("PhoneTest");
		person.setEmail("EmailTest");
		
		try {
			//when(personRepository.savePerson(any(Person.class))).thenReturn(person);
			when(personRepository.savePerson(person)).thenReturn(person);
			
			URI location = new URI("localhost:8080/person/FirstNameTestLastNameTest");
			when(ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}{lastName}")
					.buildAndExpand(person.getFirstName(), person.getLastName()).toUri())
					.thenReturn(location);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ResponseEntity<Void> response = personService.savePerson(person);
		
		System.out.println(response);
		
	}
}
