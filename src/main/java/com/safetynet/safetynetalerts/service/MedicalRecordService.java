package com.safetynet.safetynetalerts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.dao.IMedicalRecordRepository;
import com.safetynet.safetynetalerts.exception.MissingEntityException;
import com.safetynet.safetynetalerts.model.MedicalRecord;

@Service
public class MedicalRecordService {
	
	@Autowired
	private IMedicalRecordRepository iMedicalRecordRepository;

	public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) throws Exception {
		MedicalRecord savedMedicalRecord = new MedicalRecord();
		if(medicalRecord != null) {
			savedMedicalRecord = iMedicalRecordRepository.saveMedicalRecord(medicalRecord);

		} else {
			throw new MissingEntityException();
		}
		return savedMedicalRecord;
	}
	
	public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) throws Exception {
		MedicalRecord savedMedicalRecord = new MedicalRecord();
		if(medicalRecord != null) {
			savedMedicalRecord = iMedicalRecordRepository.updateMedicalRecord(medicalRecord);

		} else {
			throw new MissingEntityException();
		}
		return savedMedicalRecord;
	}
	
	public MedicalRecord deleteMedicalRecord(MedicalRecord medicalRecord) throws Exception {
		MedicalRecord savedMedicalRecord = new MedicalRecord();
		if(medicalRecord != null) {
			savedMedicalRecord = iMedicalRecordRepository.deleteMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName());

		} else {
			throw new MissingEntityException();
		}
		return savedMedicalRecord;
	}
}
