package com.safetynet.safetynetalerts.integration.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
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
@TestMethodOrder(OrderAnnotation.class)
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
	
	@AfterAll
	private static void tearDown() {
		dataBasePrepareService.clearDataBaseEntries();
	}

	
	@Test
	@Order(1)
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
	
	@Test
	@Order(2)
	public void updateMedicalRecordIT() throws Exception {
		// GIVEN
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setFirstName(firstNameTest);
		medicalRecord.setLastName(lastNameTest);
		medicalRecord.setBirthDate("NewBirthdateTest");
		List<String> medications = new ArrayList<>();
		medications.add("NewMedicationTest1");
		medications.add("NewMedicationTest2");
		medicalRecord.setMedications(medications);
		List<String> allergies = new ArrayList<>();
		allergies.add("NewAllergieTest1");
		allergies.add("NewAllergieTest2");
		medicalRecord.setAllergies(allergies);

		// WHEN, THEN
		mockMvc.perform(put("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(medicalRecord.toJson())
				.characterEncoding("utf-8")).andExpect(status().isCreated()).andReturn();

	}
	
	@Test
	@Order(3)
	public void deleteMedicalRecordIT() throws Exception {
		// GIVEN
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setFirstName(firstNameTest);
		medicalRecord.setLastName(lastNameTest);
		List<String> medications = new ArrayList<>();
		medicalRecord.setMedications(medications);
		List<String> allergies = new ArrayList<>();
		medicalRecord.setAllergies(allergies);

		// WHEN, THEN
		mockMvc.perform(delete("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(medicalRecord.toJson())
				.characterEncoding("utf-8")).andExpect(status().isOk()).andReturn();

	}
	
}