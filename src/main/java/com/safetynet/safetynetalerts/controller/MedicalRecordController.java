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

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.MedicalRecordService;

@RestController
@RequestMapping("/medicalrecord")
public class MedicalRecordController {

	//private static final Logger logger = LogManager.getLogger("MedicalRecordController");
	
	@Autowired
	private MedicalRecordService medicalRecordService;
	
	@PostMapping
	public ResponseEntity<Void> saveMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		ResponseEntity<Void> response = medicalRecordService.saveMedicalRecord(medicalRecord);
		return response;
	}
	
	@PutMapping
	public ResponseEntity<Void> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		ResponseEntity<Void> response = medicalRecordService.updateMedicalRecord(medicalRecord);
		return response;
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) {
		ResponseEntity<Void> response = medicalRecordService.deleteMedicalRecord(firstName, lastName);
		return response;
	}
}
