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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	private static final Logger logger = LogManager.getLogger("PersonController");

	@Autowired
	private PersonService personService;

	@PostMapping
	public ResponseEntity<Void> savePerson(/*@Valid */@RequestBody /* TODO @Valid NE FONCTIONNE PAS*/Person person) {
		System.out.println("person before dao:" + person);
		
		ResponseEntity<Void> response;
		
		try {
			//Person savedPerson = personService.savePerson(person);
			personService.savePerson(person);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}{lastName}")
					.buildAndExpand(person.getFirstName(), person.getLastName()).toUri();
			response = ResponseEntity.created(location).build();
			logger.info(response);
		} catch (Exception e) {
			
			e.printStackTrace();
			response = ResponseEntity.badRequest().build();
			logger.error(response);
		}
		return response;
		

	}

	@PutMapping
	public ResponseEntity<Void> updatePerson(@RequestBody Person person) {
		ResponseEntity<Void> response;

		try {
			personService.updatePerson(person);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}{lastName}")
					.buildAndExpand(person.getFirstName(), person.getLastName()).toUri();
			response = ResponseEntity.created(location).build();
			logger.info(response);
		} catch (Exception e) {
			
			e.printStackTrace();
			response = ResponseEntity.badRequest().build();
			logger.error(response);
		}
		return response;
	}

	@DeleteMapping
	public ResponseEntity<Void> deletePerson(@RequestBody Person person) {
		ResponseEntity<Void> response;

		try {
			personService.deletePerson(person);
			response = ResponseEntity.status(200).build();
			logger.info(response);
		} catch (Exception e) {
			
			e.printStackTrace();
			response = ResponseEntity.badRequest().build();
			logger.error(response);
		}
		return response;
	}
}

