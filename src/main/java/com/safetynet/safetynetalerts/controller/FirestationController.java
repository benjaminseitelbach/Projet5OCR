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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.safetynet.safetynetalerts.dao.FirestationDao;
import com.safetynet.safetynetalerts.model.Firestation;

@RestController
public class FirestationController {

	private static final Logger logger = LogManager.getLogger("FirestationController");
	
	@Autowired
	private FirestationDao firestationDao;
	
	@PostMapping(value = "/firestation")
	public ResponseEntity<Void> addFirestation(@RequestBody Firestation firestation) {
		Firestation firestationAdded = firestationDao.saveFirestation(firestation);

		ResponseEntity<Void> response;
		if (firestationAdded != null) {
			
			//TODO PATH A CHECK
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(firestationAdded.getStation()).toUri();
			response = ResponseEntity.created(location).build();
			
			logger.info(response);
			return response;
		} else {
			response = ResponseEntity.noContent().build();
			logger.error(response);

			return response;
		}
	}
	
	@PutMapping(value = "/firestation")
	public ResponseEntity<Void> updateFirestation(@RequestBody Firestation firestation) {
		Firestation firestationUpdated = firestationDao.updateFirestation(firestation);
		ResponseEntity<Void> response;
		if (firestationUpdated != null) {
			
			//URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}")
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("")
					.buildAndExpand(firestationUpdated.getStation()).toUri();
			response = ResponseEntity.created(location).build();
			
			logger.info(response);
			return response;
		} else {
			response = ResponseEntity.noContent().build();
			logger.error(response);

			return response;
		}
	}

	@DeleteMapping(value = "/firestation")
	public ResponseEntity<Void> deleteFirestation(@RequestBody Firestation firestation) {
		Firestation firestationDeleted = firestationDao.deleteFirestation(firestation);
		ResponseEntity<Void> response;
		if (firestationDeleted != null) {
			
			//URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}")
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("")
					.buildAndExpand(firestationDeleted.getStation()).toUri();
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
