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

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.MedicalRecordService;

@RestController
@RequestMapping("/medicalrecord")
public class MedicalRecordController {

	private static final Logger logger = LogManager.getLogger("MedicalRecordController");
	
	@Autowired
	private MedicalRecordService medicalRecordService;
	
	@PostMapping
	public ResponseEntity<Void> saveMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		ResponseEntity<Void> response;
		
		try {
			medicalRecordService.saveMedicalRecord(medicalRecord);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}{lastName}")
					.buildAndExpand(medicalRecord.getFirstName(), medicalRecord.getLastName()).toUri();
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
	public ResponseEntity<Void> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		ResponseEntity<Void> response;
		
		try {

			medicalRecordService.saveMedicalRecord(medicalRecord);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}{lastName}")
					.buildAndExpand(medicalRecord.getFirstName(), medicalRecord.getLastName()).toUri();
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
	public ResponseEntity<Void> deleteMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		ResponseEntity<Void> response;
		
		try {

			medicalRecordService.saveMedicalRecord(medicalRecord);

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
