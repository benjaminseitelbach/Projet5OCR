package com.safetynet.safetynetalerts.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.dao.URLsDao;

import com.safetynet.safetynetalerts.init.Initialization;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

@RestController
public class MainController {

	private static final Logger logger = LogManager.getLogger("Controller");
	
	@Autowired
	private URLsDao URLsDao;

	@PostConstruct
	public void init() {
		Initialization.init();
	}
	

	@RequestMapping(path = "/firestation", method = RequestMethod.GET)
	public String getPersonsByStationNumber(@RequestParam int stationNumber) {
		System.out.println(stationNumber);
		String personsByStationNumber = URLsDao.getPersonsByStationNumber(stationNumber);
		
		logHttpResponse(personsByStationNumber);
		
		return personsByStationNumber;
	}

	@RequestMapping(path = "/childAlert", method = RequestMethod.GET)
	public String getChildsByAddress(@RequestParam String address) {

		String childsByAddress = URLsDao.getChildsByAddress(address);
		
		logHttpResponse(childsByAddress);
		
		return childsByAddress;

	}

	/*
	@RequestMapping(path = "/phoneAlert", method = RequestMethod.GET)
	public String getPhonesByStationNumber(@RequestParam int stationNumber) {
		System.out.println("station number: " + stationNumber);
		String phonesByStationNumber = URLsDao.getPhonesByStationNumber(stationNumber);
		
		logHttpResponse(phonesByStationNumber);
		
		return phonesByStationNumber;

	}
	*/
	
	@RequestMapping(path = "/phoneAlert", method = RequestMethod.GET)
	public String getPhonesByStationNumber(@RequestParam int firestation) {
		//System.out.println("station number: " + firestation);
		String phonesByStationNumber = URLsDao.getPhonesByStationNumber(firestation);
		
		logHttpResponse(phonesByStationNumber);
		
		return phonesByStationNumber;

	}

	@RequestMapping(path = "/fire", method = RequestMethod.GET)
	public String getPersonsByAddress(@RequestParam String address) {

		String personsByAddress = URLsDao.getPersonsByAddress(address);
		
		logHttpResponse(personsByAddress);
		
		return personsByAddress;
	}

	@RequestMapping(path = "/flood/stations", method = RequestMethod.GET)
	public String getPersonsByStations(@RequestParam List<Integer> stations) {

		String personsByStations = URLsDao.getPersonsByStations(stations);
		
		logHttpResponse(personsByStations);
		
		return personsByStations;

	}

	@RequestMapping(path = "/personInfo", method = RequestMethod.GET)
	public String getPersonInfo(@RequestParam String firstName, @RequestParam String lastName) {
		String personInfo = URLsDao.getPersonInfo(firstName, lastName);
		
		logHttpResponse(personInfo);
		
		return personInfo;

	}

	@RequestMapping(path = "/communityEmail", method = RequestMethod.GET)
	public String getEmailsByCity(@RequestParam String city) {

		String emailsByCity = URLsDao.getEmailsByCity(city);
		
		logHttpResponse(emailsByCity);
		
		return emailsByCity;

	}
	

	public void logHttpResponse(String returnedInfo) {
		if (returnedInfo == null) {
			logger.error(HttpStatus.NOT_FOUND);
		} else {
			logger.info(HttpStatus.FOUND);
		}
	}
	
	
	/*
	public String getJson(Object object) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(object);
		return json;
	}

	public String getJson(List<Object> objects) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(objects);
		return json;
	}
	*/
}
