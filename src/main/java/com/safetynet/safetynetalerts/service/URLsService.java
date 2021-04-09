package com.safetynet.safetynetalerts.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.dao.IURLsRepository;

@Service
public class URLsService {
	
	private static final Logger logger = LogManager.getLogger("URLsService");
	
	@Autowired
	private IURLsRepository iURLsRepository;
	
	public String getFirestation(int stationNumber) {
		String result = iURLsRepository.getFirestation(stationNumber);
		logHttpResponse(result);
		return result;
	}
	
	public String getChildAlert(String address) {
		String result = iURLsRepository.getChildAlert(address);
		logHttpResponse(result);
		return result;
	}
	
	public String getPhoneAlert(int firestation) {
		String result = iURLsRepository.getFirestation(firestation);
		logHttpResponse(result);
		return result;
	}
	
	public String getFire(String address) {
		String result = iURLsRepository.getFire(address);
		logHttpResponse(result);
		return result;
	}
	
	public String getFloodStations(List<Integer> stations) {
		String result = iURLsRepository.getFloodStations(stations);
		logHttpResponse(result);
		return result;
	}
	
	public String getPersonInfo(String firstName, String lastName) {
		String result = iURLsRepository.getPersonInfo(firstName, lastName);
		logHttpResponse(result);
		return result;
	}
	
	public String getCommunityEmail(String city) {
		String result = iURLsRepository.getCommunityEmail(city);
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
