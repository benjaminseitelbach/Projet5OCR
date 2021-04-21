package com.safetynet.safetynetalerts.integration.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.safetynet.safetynetalerts.config.DataBaseConfig;
import com.safetynet.safetynetalerts.constants.DBConstants;
import com.safetynet.safetynetalerts.integration.config.DataBaseTestConfig;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.utils.DatesUtils;

public class DataBasePrepareService {

    DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();

    public void clearDataBaseEntries(){
    	System.out.println("clear data base entries");
        Connection connection = null;
        try{
            connection = dataBaseTestConfig.getConnection();

            //clear medications
            connection.prepareStatement("truncate table medication").execute();

            //clear allergies
            connection.prepareStatement("truncate table allergie").execute();         
            
            //clear persons
            connection.prepareStatement("truncate table person").execute();
            
            //clear firestations
            connection.prepareStatement("truncate table firestation").execute();
            
            //clear medical records
            connection.prepareStatement("set FOREIGN_KEY_CHECKS = 0").execute();
            connection.prepareStatement("truncate table medicalRecord").execute();
            connection.prepareStatement("set FOREIGN_KEY_CHECKS = 1").execute();
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            dataBaseTestConfig.closeConnection(connection);
        }
    }


    public Person findPerson(String firstName, String lastName) {
    	Connection connection = null;

    	Person person = new Person();
    	
        try {
        	connection = dataBaseTestConfig.getConnection();
        	
        	PreparedStatement ps = connection.prepareStatement("SELECT FIRST_NAME, LAST_NAME, ADDRESS, CITY, ZIP, PHONE, EMAIL FROM PERSON WHERE"
        			+ " FIRST_NAME = '" + firstName + "' AND LAST_NAME = '" + lastName + "'");
            
        	ResultSet rs = ps.executeQuery();
        	if(rs.next()) {
        		person.setFirstName(rs.getString(1));
            	person.setLastName(rs.getString(2));
            	person.setAddress(rs.getString(3));
            	person.setCity(rs.getString(4));
            	person.setZip(rs.getString(5));
            	person.setPhone(rs.getString(6));
            	person.setEmail(rs.getString(7));
        	}
        
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dataBaseTestConfig.closeConnection(connection);

        }

		return person;
    }
    
    public boolean firestationExists(String address, int station) {
    	Connection connection = null;

        try {
        	connection = dataBaseTestConfig.getConnection();
        	
        	PreparedStatement ps = connection.prepareStatement("SELECT ADDRESS, STATION FROM FIRESTATION WHERE"
        			+ " ADDRESS = '" + address + "' AND STATION = '" + station + "'");
            
        	ResultSet rs = ps.executeQuery();
        	rs.next();
        	if(rs != null) {
        		return true;
        	}
            
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dataBaseTestConfig.closeConnection(connection);

        }

		return false;
    }
    
    public MedicalRecord findMedicalRecord(String firstName, String lastName) {
    	Connection connection = null;

    	MedicalRecord medicalRecord = new MedicalRecord();
    	
    	int id = getId(firstName, lastName);
        try {
        	connection = dataBaseTestConfig.getConnection();
        	
        	PreparedStatement ps = connection.prepareStatement("SELECT FIRST_NAME, LAST_NAME, BIRTHDATE FROM MEDICALRECORD WHERE"
        			+ " FIRST_NAME = '" + firstName + "' AND LAST_NAME = '" + lastName + "'");
            
        	ResultSet rs = ps.executeQuery();
        	if(rs.next()) {
        		medicalRecord.setFirstName(rs.getString(1));
            	medicalRecord.setLastName(rs.getString(2));
            	medicalRecord.setBirthDate(rs.getString(3));
            	medicalRecord.setMedications(findMedications(id));
            	medicalRecord.setAllergies(findAllergies(id));
        	}
        	
            
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dataBaseTestConfig.closeConnection(connection);

        }

		return medicalRecord;
    }
    
    public int getId(String firstName, String lastName) {
    	Connection connection = null;
    	int result = -1;
    	try {
    		connection = dataBaseTestConfig.getConnection();
    		
    		PreparedStatement ps = connection.prepareStatement("SELECT ID FROM MEDICALRECORD WHERE"
    				+ " FIRST_NAME = '" + firstName + "' AND LAST_NAME = '" + lastName + "'");
    		
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			result = rs.getInt(1);
    		}
    		
    	}catch (Exception e) {
    		e.printStackTrace();
    	}finally {
    		dataBaseTestConfig.closeConnection(connection);
    	}
    	return result;
    }
    
    public List<String> findMedications(int medicalRecordId) {
    	Connection connection = null;
    	List<String> result = new ArrayList<>();
    	try {
    		connection = dataBaseTestConfig.getConnection();
    		
    		PreparedStatement ps = connection.prepareStatement("SELECT NAME FROM MEDICATION WHERE medicalRecord_ID = " + medicalRecordId);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			result.add(rs.getString(1));
    		}
    		
    	}catch (Exception e) {
    		e.printStackTrace();
    	}finally {
    		dataBaseTestConfig.closeConnection(connection);
    	}
    	return result;
    }
    
    public List<String> findAllergies(int medicalRecordId) {
    	Connection connection = null;
    	List<String> result = new ArrayList<>();
    	try {
    		connection = dataBaseTestConfig.getConnection();
    		
    		PreparedStatement ps = connection.prepareStatement("SELECT NAME FROM ALLERGIE WHERE medicalRecord_ID = " + medicalRecordId);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			result.add(rs.getString(1));
    		}
    		
    	}catch (Exception e) {
    		e.printStackTrace();
    	}finally {
    		dataBaseTestConfig.closeConnection(connection);
    	}
    	return result;
    }
}
