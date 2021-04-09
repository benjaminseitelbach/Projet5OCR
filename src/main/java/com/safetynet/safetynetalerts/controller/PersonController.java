package com.safetynet.safetynetalerts.controller;

import java.net.URI;

import javax.validation.Valid;

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

	//private static final Logger logger = LogManager.getLogger("PersonController");

	@Autowired
	private PersonService personService;


	@PostMapping
	public ResponseEntity<Void> savePerson(@RequestBody /* TODO @Valid NE FONCTIONNE PAS*/Person person) {
		System.out.println("person before dao:" + person);
		ResponseEntity<Void> response = personService.savePerson(person);
		return response;
	}

	@PutMapping
	public ResponseEntity<Void> updatePerson(@RequestBody Person person) {
		ResponseEntity<Void> response = personService.updatePerson(person);
		return response;
	}

	@DeleteMapping
	public ResponseEntity<Void> deletePerson(@RequestParam String firstName, @RequestParam String lastName) {
		ResponseEntity<Void> response = personService.deletePerson(firstName, lastName);
		return response;
	}
}

