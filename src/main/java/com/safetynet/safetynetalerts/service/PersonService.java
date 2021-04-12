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
import com.safetynet.safetynetalerts.model.Person;

@Service
public class PersonService {

	private static final Logger logger = LogManager.getLogger("PersonService");
	
	//@Autowired
	private PersonRepository personRepository = new PersonRepository();

	public ResponseEntity<Void> savePerson(Person person) {
		Person savedPerson = personRepository.savePerson(person);
		
		ResponseEntity<Void> response;
		if (savedPerson != null) {
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}{lastName}")
					.buildAndExpand(person.getFirstName(), person.getLastName()).toUri();
			response = ResponseEntity.created(location).build();

			logger.info(response);

			return response;

		} else {
			response = ResponseEntity.noContent().build();
			logger.error(response);

			return response;
		}
	}
	
	public ResponseEntity<Void> updatePerson(Person person) {
		Person updatedPerson = iPersonRepository.updatePerson(person);
		ResponseEntity<Void> response;
		if (updatedPerson != null) {

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}{lastName}")
					.buildAndExpand(updatedPerson.getFirstName(), person.getLastName()).toUri();
			response = ResponseEntity.created(location).build();

			logger.info(response);
			return response;
		} else {
			response = ResponseEntity.noContent().build();
			logger.error(response);

			return response;
		}
	}
	
	public ResponseEntity<Void> deletePerson(String firstName, String lastName) {
		Person deletedPerson = iPersonRepository.deletePerson(firstName, lastName);
		ResponseEntity<Void> response;
		if (deletedPerson != null) {

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}{lastName}")
					.buildAndExpand(deletedPerson.getFirstName(), deletedPerson.getLastName()).toUri();
			response = ResponseEntity.created(location).build();

			logger.info(response);
			return response;
		} else {
			response = ResponseEntity.noContent().build();
			logger.error(response);

			return response;
		}
	}
}
