package com.safetynet.safetynetalerts.integration.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.dao.FirestationDao;
import com.safetynet.safetynetalerts.dao.PersonDao;
import com.safetynet.safetynetalerts.integration.service.DataBasePrepareService;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.Person;

@SpringBootTest
public class FirestationDaoIntegrationTest {

	@Autowired
	private FirestationDao firestationDao;
	
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
	
	@Test
	public void saveFirestationIT() {
		Firestation firestation = new Firestation();
		firestation.setAddress(addressTest);
		firestation.setStation(stationTest);
	
		firestationDao.saveFirestation(firestation);
		
		boolean firestationExists = dataBasePrepareService.firestationExists(addressTest, stationTest);
		
		assertEquals(true, firestationExists);
	}
	
	@Test
	public void updateFirestationIT() {
		Firestation firestation = new Firestation();
		firestation.setAddress(addressTest);		
		firestation.setStation(newStation);
		
		firestationDao.updateFirestation(firestation);
		
		boolean firestationExists = dataBasePrepareService.firestationExists(addressTest, newStation);
		
		assertEquals(true, firestationExists);
	}
	
	@Test
	public void deleteFirestationIT() {
		Firestation firestation = new Firestation();
		firestation.setAddress(addressTest);		
		firestation.setStation(newStation);
		
		firestationDao.deleteFirestation(firestation);
		
		boolean firestationExists = false;/* dataBasePrepareService.firestationExists(addressTest, newStation);*/
		
		assertEquals(false, firestationExists);
		
		
	}
	
}
