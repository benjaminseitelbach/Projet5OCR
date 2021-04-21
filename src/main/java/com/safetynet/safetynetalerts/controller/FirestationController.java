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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.service.FirestationService;

@RestController
@RequestMapping("/firestation")
public class FirestationController {

	private static final Logger logger = LogManager.getLogger("FirestationController");
	
	@Autowired
	private FirestationService firestationService;
	
	@PostMapping
	public ResponseEntity<Void> addFirestation(@RequestBody Firestation firestation) {
		
		ResponseEntity<Void> response;
		
		try {
			//Person savedPerson = personService.savePerson(person);
			firestationService.saveFirestation(firestation);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{address}{station}")
					.buildAndExpand(firestation.getAddress(), firestation.getStation()).toUri();
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
	public ResponseEntity<Void> updateFirestation(@RequestBody Firestation firestation) {
		ResponseEntity<Void> response;
		
		try {
			firestationService.updateFirestation(firestation);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{address}{station}")
					.buildAndExpand(firestation.getAddress(), firestation.getStation()).toUri();
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
	public ResponseEntity<Void> deleteFirestation(@RequestBody Firestation firestation) {
		ResponseEntity<Void> response;
		
		try {
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
