package com.safetynet.safetynetalerts.dao;

import java.util.List;

public interface URLsDao {
	
	public String getPersonsByStationNumber(int stationNumber);
	
	public String getChildsByAddress(String address);
	
	public String getPhonesByStationNumber(int stationNumber);
	
	public String getPersonsByAddress(String address);
	
	public String getPersonsByStations(List<Integer> stations);
	
	public String getPersonInfo(String firstName, String lastName);
	
	public String getEmailsByCity(String city);	
	
}