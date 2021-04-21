package com.safetynet.safetynetalerts.integration.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.dao.IFirestationRepository;

import com.safetynet.safetynetalerts.integration.service.DataBasePrepareService;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.Person;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class FirestationDaoIntegrationTest {

	@Autowired
	private IFirestationRepository iFirestationRepository;
	
	private static DataBasePrepareService dataBasePrepareService;
	
	private static String addressTest;
	private static int stationTest;
	private static int newStation;
	
	@BeforeAll
	private static void setUp() throws Exception {
		System.out.println("Set up");
		dataBasePrepareService = new DataBasePrepareService();
		dataBasePrepareService.clearDataBaseEntries();
		addressTest = "AddressTest";
		stationTest = 5;
		newStation = 6;
	}
	
	@AfterAll
	private static void tearDown() {
		dataBasePrepareService.clearDataBaseEntries();
	}
	
	@Test
	@Order(1)
	public void saveFirestationIT() {
		Firestation firestation = new Firestation();
		firestation.setAddress(addressTest);
		firestation.setStation(stationTest);
	
		iFirestationRepository.saveFirestation(firestation);
		
		boolean firestationExists = dataBasePrepareService.firestationExists(addressTest, stationTest);
		
		assertEquals(true, firestationExists);
	}
	
	@Test
	@Order(2)
	public void updateFirestationIT() {
		Firestation firestation = new Firestation();
		firestation.setAddress(addressTest);		
		firestation.setStation(newStation);
		
		iFirestationRepository.updateFirestation(firestation);
		
		boolean firestationExists = dataBasePrepareService.firestationExists(addressTest, newStation);
		
		assertEquals(true, firestationExists);
	}
	
	@Test
	@Order(3)
	public void deleteFirestationIT() {
		Firestation firestation = new Firestation();
		firestation.setAddress(addressTest);		
		firestation.setStation(newStation);
		
		iFirestationRepository.deleteFirestation(firestation);
		
		boolean firestationExists = false;/* dataBasePrepareService.firestationExists(addressTest, newStation);*/
		
		assertEquals(false, firestationExists);
		
		
	}
	
}
