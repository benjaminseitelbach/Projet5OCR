package com.safetynet.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.dao.IURLsRepository;

@Service
public class URLsService {
	
	@Autowired
	private IURLsRepository iURLsRepository;
	
	public String getFirestation(int stationNumber) {
		String result = iURLsRepository.getFirestation(stationNumber);
		return result;
	}
	
	public String getChildAlert(String address) {
		String result = iURLsRepository.getChildAlert(address);
		return result;
	}
	
	public String getPhoneAlert(int firestation) {
		String result = iURLsRepository.getFirestation(firestation);
		return result;
	}
	
	public String getFire(String address) {
		String result = iURLsRepository.getFire(address);
		return result;
	}
	
	public String getFloodStations(List<Integer> stations) {
		String result = iURLsRepository.getFloodStations(stations);
		return result;
	}
	
	public String getPersonInfo(String firstName, String lastName) {
		String result = iURLsRepository.getPersonInfo(firstName, lastName);
		return result;
	}
	
	public String getCommunityEmail(String city) {
		String result = iURLsRepository.getCommunityEmail(city);
		return result;
	}
	
	
}
