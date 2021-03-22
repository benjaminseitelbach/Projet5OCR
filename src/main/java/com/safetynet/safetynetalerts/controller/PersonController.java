package com.safetynet.safetynetalerts.controller;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.safetynet.safetynetalerts.dao.PersonDao;
import com.safetynet.safetynetalerts.model.Person;

@RestController
public class PersonController {

	private static final Logger logger = LogManager.getLogger("PersonController");

	@Autowired
	private PersonDao personDao;


	@PostMapping(value = "/person")
	public ResponseEntity<Void> savePerson(@RequestBody Person person) {
		System.out.println("person before dao:" + person);
		Person personAdded = personDao.savePerson(person);
		System.out.println(personAdded);
		ResponseEntity<Void> response;
		if (personAdded != null) {
			
			//TODO AJOUTER LASTNAME
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}")
					.buildAndExpand(person.getFirstName()).toUri();
			response = ResponseEntity.created(location).build();

			logger.info(response);

			return response;

		} else {
			response = ResponseEntity.noContent().build();
			logger.error(response);

			return response;
		}
	}

	@PutMapping(value = "/person")
	public ResponseEntity<Void> updatePerson(@RequestBody Person person) {
		Person personUpdated = personDao.updatePerson(person);
		ResponseEntity<Void> response;
		if (personUpdated != null) {

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("")
					.buildAndExpand(personUpdated.getFirstName()).toUri();
			response = ResponseEntity.created(location).build();

			logger.info(response);
			return response;
		} else {
			response = ResponseEntity.noContent().build();
			logger.error(response);

			return response;
		}
	}

	@DeleteMapping(value = "/person")
	public ResponseEntity<Void> deletePerson(@RequestParam String firstName, @RequestParam String lastName) {
		Person personDeleted = personDao.deletePerson(firstName, lastName);
		System.out.println("PERSON DELETED:" + personDeleted);
		ResponseEntity<Void> response;
		if (personDeleted != null) {

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("")
					.buildAndExpand(personDeleted.getFirstName()).toUri();
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

