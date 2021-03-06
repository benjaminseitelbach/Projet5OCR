package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.model.MedicalRecord;

public interface IMedicalRecordRepository {

	public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord);

	public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord);

	public MedicalRecord deleteMedicalRecord(String firstName, String lastName);
}