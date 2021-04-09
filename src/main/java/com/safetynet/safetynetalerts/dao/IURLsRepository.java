package com.safetynet.safetynetalerts.dao;

import java.util.List;

public interface IURLsRepository {
	
	public String getFirestation(int stationNumber);
	
	public String getChildAlert(String address);
	
	public String getPhoneAlert(int firestation);
	
	public String getFire(String address);
	
	public String getFloodStations(List<Integer> stations);
	
	public String getPersonInfo(String firstName, String lastName);
	
	public String getCommunityEmail(String city);	
	
}