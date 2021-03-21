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

import com.safetynet.safetynetalerts.dao.MedicalRecordDao;
import com.safetynet.safetynetalerts.model.MedicalRecord;

@RestController
public class MedicalRecordController {

	private static final Logger logger = LogManager.getLogger("MedicalRecordController");
	
	@Autowired
	private MedicalRecordDao medicalRecordDao;
	
	@PostMapping(value = "/medicalRecord")
	public ResponseEntity<Void> saveMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		MedicalRecord medicalRecordAdded = medicalRecordDao.saveMedicalRecord(medicalRecord);

		ResponseEntity<Void> response;
		if (medicalRecordAdded != null) {
			
			//URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}")
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(medicalRecord.getFirstName()).toUri();
			response = ResponseEntity.created(location).build();
			
			logger.info(response);
			return response;
		} else {
			response = ResponseEntity.noContent().build();
			logger.error(response);

			return response;
		}
	}
	
	@PutMapping(value = "/medicalRecord")
	public ResponseEntity<Void> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		MedicalRecord medicalRecordUpdated = medicalRecordDao.updateMedicalRecord(medicalRecord);
		ResponseEntity<Void> response;
		if (medicalRecordUpdated != null) {
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("")
					.buildAndExpand(medicalRecordUpdated.getFirstName()).toUri();
			response = ResponseEntity.created(location).build();
			
			logger.info(response);
			return response;
		} else {
			response = ResponseEntity.noContent().build();
			logger.error(response);

			return response;
		}
	}

	@DeleteMapping(value = "/medicalRecord")
	public ResponseEntity<Void> deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) {
		MedicalRecord medicalRecordDeleted = medicalRecordDao.deleteMedicalRecord(firstName, lastName);
		ResponseEntity<Void> response;
		if (medicalRecordDeleted != null) {
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("")
					.buildAndExpand(medicalRecordDeleted.getFirstName()).toUri();
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
