package com.safetynet.safetynetalerts.service;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.safetynet.safetynetalerts.dao.IFirestationRepository;
import com.safetynet.safetynetalerts.model.Firestation;

@Service
public class FirestationService {
	
	private static final Logger logger = LogManager.getLogger("FirestationService");
	
	@Autowired
	private IFirestationRepository iFirestationRepository;
	
	public ResponseEntity<Void> saveFirestation(Firestation firestation) {
		Firestation savedFirestation = iFirestationRepository.saveFirestation(firestation);
		
		ResponseEntity<Void> response;
		if (savedFirestation != null) {
			
			//TODO PATH A CHECK
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedFirestation.getStation()).toUri();
			response = ResponseEntity.created(location).build();
			
			logger.info(response);
			return response;
		} else {
			response = ResponseEntity.noContent().build();
			logger.error(response);

			return response;
		}
	}
	
	public ResponseEntity<Void> updateFirestation(Firestation firestation) {
		Firestation updatedFirestation = iFirestationRepository.updateFirestation(firestation);
		
		ResponseEntity<Void> response;
		if (updatedFirestation != null) {
			
			//URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}")
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("")
					.buildAndExpand(updatedFirestation.getStation()).toUri();
			response = ResponseEntity.created(location).build();
			
			logger.info(response);
			return response;
		} else {
			response = ResponseEntity.noContent().build();
			logger.error(response);

			return response;
		}
	}
	
	public ResponseEntity<Void> deleteFirestation(Firestation firestation) {
		Firestation deletedFirestation = iFirestationRepository.deleteFirestation(firestation);
		
		ResponseEntity<Void> response;
		if (deletedFirestation != null) {
			
			//URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}")
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("")
					.buildAndExpand(deletedFirestation.getStation()).toUri();
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
