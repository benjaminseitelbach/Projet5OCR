package com.safetynet.safetynetalerts.integration.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.dao.IURLsRepository;
import com.safetynet.safetynetalerts.init.Initialization;
import com.safetynet.safetynetalerts.integration.service.DataBasePrepareService;

@SpringBootTest
public class URLsDaoIntegrationTest {
	
	@Autowired
	private IURLsRepository iURLsRepository;
	
	private static DataBasePrepareService dataBasePrepareService;
	
	@BeforeAll
	private static void setUp() throws Exception {
		System.out.println("Set up");
		dataBasePrepareService = new DataBasePrepareService();
		dataBasePrepareService.clearDataBaseEntries();
		Initialization.init();
	}
	
	@Test
	public void getFirestationByStationNumber1Test() throws Exception {
		String jsonString = iURLsRepository.getFirestation(1);
		JSONObject json = new JSONObject(jsonString);
		JSONArray personsArray = json.getJSONArray("persons");

		assertEquals(personsArray.length(), 6);
		int adults = json.getInt("adults");
		assertEquals(adults, 5);
		int childs = json.getInt("childs");
		assertEquals(childs, 1); 
	}
	
	@Test
	public void getChildAlertByAddress1509CulverSt () throws Exception {
		String jsonString = iURLsRepository.getChildAlert("1509 Culver St");
		JSONObject json = new JSONObject(jsonString);
		JSONArray childsArray = json.getJSONArray("childs");
		assertEquals(2, childsArray.length());
		JSONArray otherMembersArray = json.getJSONArray("other members");
		assertEquals(otherMembersArray.length(), 3);			
	}
	
	@Test
	public void getPhoneAlertByFirestation1 () throws Exception {
		String jsonString = iURLsRepository.getPhoneAlert(1);
		JSONObject json = new JSONObject(jsonString);
		JSONArray phonesArray = json.getJSONArray("phones");
		assertEquals(phonesArray.length(), 6);			
	}
	
	@Test
	public void getFireByAddress1509CulverSt () throws Exception {
		String jsonString = iURLsRepository.getFire("1509 Culver St");
		JSONObject json = new JSONObject(jsonString);
		JSONArray personsArray = json.getJSONArray("persons");
		assertEquals(personsArray.length(), 5);			
		int station = json.getInt("station");
		assertEquals(station, 3);
	}
	
	@Test
	public void getFloodByStations1And2 () throws Exception {
		List<Integer> stations = new ArrayList<>();
		stations.add(1);
		stations.add(2);
		String jsonString = iURLsRepository.getFloodStations(stations);
		JSONObject json = new JSONObject(jsonString);
		JSONArray addressesArray = json.getJSONArray("addresses");
		assertEquals(addressesArray.length(), 6); 		
	}
	
	@Test
	public void getPersonInfoJohnBoyd () throws Exception {
		String jsonString = iURLsRepository.getPersonInfo("John", "Boyd");
		JSONObject json = new JSONObject(jsonString);
		assertEquals(json.getString("firstname"), "John");
		assertEquals(json.getString("lastname"), "Boyd");
		assertEquals(json.getString("address"), "1509 Culver St");
		assertEquals(json.getString("birthdate"), "03/06/1984");
		assertEquals(json.getString("email"), "jaboyd@email.com");
	}
	
	@Test
	public void getCommunityEmailByCityCulver () throws Exception {
		String jsonString = iURLsRepository.getCommunityEmail("Culver");
		JSONObject json = new JSONObject(jsonString);
		JSONArray emailsArray = json.getJSONArray("emails");
		assertEquals(emailsArray.length(), 23);
	}
}
