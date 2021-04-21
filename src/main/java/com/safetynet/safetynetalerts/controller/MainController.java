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

import com.safetynet.safetynetalerts.init.Initialization;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.URLsService;

@RestController
public class MainController {

	private static final Logger logger = LogManager.getLogger("MainController");
	
	@Autowired
	private URLsService urlsService;

	@PostConstruct
	public void init() {
		System.out.println("=============INITIALIZATION==========");
		Initialization.init();
	}
	
	@RequestMapping(path = "/firestation", method = RequestMethod.GET)
	public String getFirestation(@RequestParam int stationNumber) {
		String result = urlsService.getFirestation(stationNumber);
		logHttpResponse(result);
		return result;
	}

	@RequestMapping(path = "/childAlert", method = RequestMethod.GET)
	public String getChildAlert(@RequestParam String address) {

		String result = urlsService.getChildAlert(address);
		logHttpResponse(result);
		return result;

	}

	
	@RequestMapping(path = "/phoneAlert", method = RequestMethod.GET)
	public String getPhoneAlert(@RequestParam int firestation) {
		String result = urlsService.getPhoneAlert(firestation);
		logHttpResponse(result);
		return result;

	}

	@RequestMapping(path = "/fire", method = RequestMethod.GET)
	public String getFire(@RequestParam String address) {
		String result = urlsService.getFire(address);
		logHttpResponse(result);
		return result;
	}

	@RequestMapping(path = "/flood/stations", method = RequestMethod.GET)
	public String getFloodStations(@RequestParam List<Integer> stations) {

		String result = urlsService.getFloodStations(stations);
		logHttpResponse(result);
		return result;

	}

	@RequestMapping(path = "/personInfo", method = RequestMethod.GET)
	public String getPersonInfo(@RequestParam String firstName, @RequestParam String lastName) {
		String result = urlsService.getPersonInfo(firstName, lastName);
		logHttpResponse(result);
		return result;

	}

	@RequestMapping(path = "/communityEmail", method = RequestMethod.GET)
	public String getCommunityEmail(@RequestParam String city) {

		String result = urlsService.getCommunityEmail(city);
		logHttpResponse(result);
		return result;

	}
	
	public void logHttpResponse(String returnedInfo) {
		if (returnedInfo == null) {
			logger.error(HttpStatus.NOT_FOUND);
		} else {
			logger.info(HttpStatus.FOUND);
		}
	}
}
