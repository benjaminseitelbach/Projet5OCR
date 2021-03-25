package com.safetynet.safetynetalerts.integration.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.dao.MedicalRecordDao;
import com.safetynet.safetynetalerts.integration.service.DataBasePrepareService;
import com.safetynet.safetynetalerts.model.MedicalRecord;

@SpringBootTest
public class MedicalRecordDaoIntegrationTest {

	@Autowired
	private MedicalRecordDao medicalRecordDao;
	
	private static DataBasePrepareService dataBasePrepareService;
	
	private static String firstNameTest;
	private static String lastNameTest;
	
	@BeforeAll
	private static void setUp() throws Exception {
		System.out.println("Set up");
		dataBasePrepareService = new DataBasePrepareService();
		dataBasePrepareService.clearDataBaseEntries();
		firstNameTest = "FirstNameTest";
		lastNameTest = "LastNameTest";
	}
	
	@Test
	public void saveMedicalRecordIT() {
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setFirstName(firstNameTest);
		medicalRecord.setLastName(lastNameTest);
		medicalRecord.setBirthDate("BirthdateTest");
		List<String> medications = new ArrayList<>();
		medications.add("MedicationTest1");
		medications.add("MedicationTest2");
		medicalRecord.setMedications(medications);
		List<String> allergies = new ArrayList<>();
		allergies.add("AllergieTest1");
		allergies.add("AllergieTest2");
		medicalRecord.setAllergies(allergies);
		
		medicalRecordDao.saveMedicalRecord(medicalRecord);
		
		MedicalRecord medicalRecordFound = dataBasePrepareService.findMedicalRecord(firstNameTest, lastNameTest);
		
		assertEquals(medicalRecord.getBirthDate(), medicalRecordFound.getBirthDate());
		for (String medication : medicalRecord.getMedications()) {

			boolean medicationWasFound = false;
			for (String medicationFound : medicalRecordFound.getMedications()) {
				if (medication.equals(medicationFound)) {
					medicationWasFound = true;
				}

			}
			assertEquals(medicationWasFound, true);
		}

		for (String allergie : medicalRecord.getAllergies()) {
			boolean allergieWasFound = false;
			for (String allergieFound : medicalRecordFound.getAllergies()) {
				if (allergie.equals(allergieFound)) {
					allergieWasFound = true;
				}

			}
			assertEquals(allergieWasFound, true);
		}
	}
	
	@Test
	public void updateMedicalRecordIT() {
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setFirstName(firstNameTest);
		medicalRecord.setLastName(lastNameTest);
		medicalRecord.setBirthDate("BirthdateTest");
		List<String> medications = new ArrayList<>();
		medications.add("MedicationTest1");
		medications.add("MedicationTest2");
		medicalRecord.setMedications(medications);
		List<String> allergies = new ArrayList<>();
		allergies.add("AllergieTest1");
		allergies.add("AllergieTest2");
		medicalRecord.setAllergies(allergies);
		
		medicalRecordDao.saveMedicalRecord(medicalRecord);
		
		medicalRecord.setFirstName(firstNameTest);
		medicalRecord.setLastName(lastNameTest);
		medicalRecord.setBirthDate("NewBirthdateTest");
		medications.add("NewMedicationTest1");
		medications.add("NewMedicationTest2");
		medicalRecord.setMedications(medications);
		allergies.add("NewAllergieTest1");
		allergies.add("NewAllergieTest2");
		medicalRecord.setAllergies(allergies);
		
		medicalRecordDao.updateMedicalRecord(medicalRecord);
		
		MedicalRecord medicalRecordFound = dataBasePrepareService.findMedicalRecord(firstNameTest, lastNameTest);
		
		assertEquals(medicalRecord.getBirthDate(), medicalRecordFound.getBirthDate());
		for (String medication : medicalRecord.getMedications()) {

			boolean medicationWasFound = false;
			for (String medicationFound : medicalRecordFound.getMedications()) {
				if (medication.equals(medicationFound)) {
					medicationWasFound = true;
				}

			}
			assertEquals(medicationWasFound, true);
		}

		for (String allergie : medicalRecord.getAllergies()) {
			boolean allergieWasFound = false;
			for (String allergieFound : medicalRecordFound.getAllergies()) {
				if (allergie.equals(allergieFound)) {
					allergieWasFound = true;
				}

			}
			assertEquals(allergieWasFound, true);
		}

	}
	
	
	@Test
	public void deleteMedicalRecordIT() {

		medicalRecordDao.deleteMedicalRecord(firstNameTest, lastNameTest);
		
		//TODO CREER MEDICALRECORD EXISTS
		MedicalRecord medicalRecordFound = dataBasePrepareService.findMedicalRecord(firstNameTest, lastNameTest);
		
		assertEquals(null, medicalRecordFound.getFirstName());
		assertEquals(null, medicalRecordFound.getLastName());
		assertEquals(null, medicalRecordFound.getBirthDate());

	}
	
	
}
