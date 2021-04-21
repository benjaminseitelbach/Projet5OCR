package com.safetynet.safetynetalerts.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.safetynet.safetynetalerts.dao.IPersonRepository;
import com.safetynet.safetynetalerts.dao.PersonRepository;
import com.safetynet.safetynetalerts.model.Person;

import static org.mockito.Mockito.*;

import java.net.URI;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
	
	private static PersonService personService;
	
	@MockBean
	@Autowired
	private static IPersonRepository iPersonRepository;
	//private static PersonRepository personRepository;
	
	@BeforeEach
	private void setUpPerTest() {
		personService = new PersonService();
		//personRepository = new PersonRepository();
		/*
		try {
			when(iPersonRepository.savePerson(any(Person.class)))
		}
		*/
	}
	
	/*
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
		
		Person savedPerson = new Person();
		try {
			//when(personRepository.savePerson(person)).thenReturn(person);
			savedPerson = personService.savePerson(person);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		assertEquals(person.getFirstName(),savedPerson.getFirstName());
		
	}
	*/
	
}
