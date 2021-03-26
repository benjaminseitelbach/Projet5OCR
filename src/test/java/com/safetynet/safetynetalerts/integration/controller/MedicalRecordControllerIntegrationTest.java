package com.safetynet.safetynetalerts.integration.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.safetynetalerts.integration.service.DataBasePrepareService;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.MedicalRecord;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

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

	/*
	@Test
	public void saveMedicalRecordIT() throws Exception {
		// GIVEN
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

		// WHEN, THEN
		mockMvc.perform(post("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(medicalRecord.toJson())
				.characterEncoding("utf-8")).andExpect(status().isCreated()).andReturn();

	}
	*/
}