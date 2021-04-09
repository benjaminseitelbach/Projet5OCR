package com.safetynet.safetynetalerts.service;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.safetynet.safetynetalerts.dao.IMedicalRecordRepository;
import com.safetynet.safetynetalerts.model.MedicalRecord;

@Service
public class MedicalRecordService {
	
	private static final Logger logger = LogManager.getLogger("MedicalRecordService");
	
	@Autowired
	private IMedicalRecordRepository iMedicalRecordRepository;

	public ResponseEntity<Void> saveMedicalRecord(MedicalRecord MedicalRecord) {
		MedicalRecord savedMedicalRecord = iMedicalRecordRepository.saveMedicalRecord(MedicalRecord);
		
		ResponseEntity<Void> response;
		if (savedMedicalRecord != null) {
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}{lastName}")
					.buildAndExpand(MedicalRecord.getFirstName(), MedicalRecord.getLastName()).toUri();
			response = ResponseEntity.created(location).build();

			logger.info(response);

			return response;

		} else {
			response = ResponseEntity.noContent().build();
			logger.error(response);

			return response;
		}
	}
	
	public ResponseEntity<Void> updateMedicalRecord(MedicalRecord MedicalRecord) {
		MedicalRecord updatedMedicalRecord = iMedicalRecordRepository.updateMedicalRecord(MedicalRecord);
		ResponseEntity<Void> response;
		if (updatedMedicalRecord != null) {

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}{lastName}")
					.buildAndExpand(updatedMedicalRecord.getFirstName(), MedicalRecord.getLastName()).toUri();
			response = ResponseEntity.created(location).build();

			logger.info(response);
			return response;
		} else {
			response = ResponseEntity.noContent().build();
			logger.error(response);

			return response;
		}
	}
	
	public ResponseEntity<Void> deleteMedicalRecord(String firstName, String lastName) {
		MedicalRecord deletedMedicalRecord = iMedicalRecordRepository.deleteMedicalRecord(firstName, lastName);
		ResponseEntity<Void> response;
		if (deletedMedicalRecord != null) {

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}{lastName}")
					.buildAndExpand(deletedMedicalRecord.getFirstName(), deletedMedicalRecord.getLastName()).toUri();
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
