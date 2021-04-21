package com.safetynet.safetynetalerts.service;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.safetynet.safetynetalerts.dao.IPersonRepository;
import com.safetynet.safetynetalerts.dao.PersonRepository;
import com.safetynet.safetynetalerts.exception.MissingEntityException;
import com.safetynet.safetynetalerts.model.Person;

@Service
public class PersonService {

	private static final Logger logger = LogManager.getLogger("PersonService");
	
	@Autowired
	private IPersonRepository iPersonRepository;

	public Person savePerson(Person person) throws Exception {
		Person savedPerson = new Person();
		if(person != null) {
			savedPerson = iPersonRepository.savePerson(person);

		} else {
			throw new MissingEntityException();
		}
		return savedPerson;
		
	}
	
	public Person updatePerson(Person person) throws Exception {
		if(person != null) {
			Person updatedPerson = iPersonRepository.updatePerson(person);
			return updatedPerson;
		} else {
			throw new MissingEntityException();
		}
	}
	
	public Person deletePerson(Person person) throws Exception {
		
		if(person != null) {
			Person deletedPerson = iPersonRepository.deletePerson(person.getFirstName(), person.getLastName());
			return deletedPerson;
		} else {
			throw new MissingEntityException();
		}
	}
}
