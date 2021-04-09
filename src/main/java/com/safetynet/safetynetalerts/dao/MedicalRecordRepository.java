package com.safetynet.safetynetalerts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.safetynet.safetynetalerts.config.DataBaseConfig;
import com.safetynet.safetynetalerts.constants.DBConstants;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

@Repository
public class MedicalRecordRepository implements IMedicalRecordRepository {

	private static final Logger logger = LogManager.getLogger("MedicalRecordRepository");

    public DataBaseConfig dataBaseConfig = new DataBaseConfig();
    
	public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
		//System.out.println(medicalRecord);
		Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.SAVE_MEDICALRECORD);
            //FIRST_NAME, LAST_NAME, ADDRESS, CITY, ZIP, PHONE, EMAIL
            //int id = medicalRecord.getId();
            //ps.setInt(1, id);
            ps.setString(1, medicalRecord.getFirstName());
            ps.setString(2, medicalRecord.getLastName());
            ps.setString(3, medicalRecord.getBirthDate());
            ps.execute();
            
            int id = getId(medicalRecord);
            //System.out.println("ID:" + id);
            
            for(String medication : medicalRecord.getMedications()) {
            	saveMedication(medication, id);            	
            }
            
            for(String allergie : medicalRecord.getAllergies()) {
            	saveAllergie(allergie, id);            	
            }
            return medicalRecord;
        }catch (Exception ex){
            logger.error("Error saving new person",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
            dataBaseConfig.closePreparedStatement(ps);            
        }
        return null;
	}
	
	public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
		int id = getId(medicalRecord);
		System.out.println("ID:" + id);
		System.out.println("medical record:" + medicalRecord);
		Connection con = null;
        PreparedStatement ps = null;
        try {
        	
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.UPDATE_MEDICALRECORD);
            //BIRTHDATE
            ps.setString(1, medicalRecord.getBirthDate());
            ps.setInt(2, id);
            
            deleteMedications(id);
            
            //System.out.println("MEDICATIONS DELETED");
            	
            for(String medication : medicalRecord.getMedications()) {
                saveMedication(medication, id);            	
            }
            //System.out.println("MEDICATIONS UPDATED");
            deleteAllergies(id);
            //System.out.println("ALLERGIES DELETED");
            for(String allergie : medicalRecord.getAllergies()) {
                saveAllergie(allergie, id);            	
                
            }
            
            ps.execute();
            return medicalRecord;
        }catch (Exception ex){
            logger.error("Error updating medicalRecord",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
            dataBaseConfig.closePreparedStatement(ps);            
        }
        return null;
	}
	
	public MedicalRecord deleteMedicalRecord(String firstName, String lastName) {
		int id = getId(firstName, lastName);
		//System.out.println("ID:" + id);
		MedicalRecord medicalRecord = findById(id);
		//System.out.println("MEDICAL RECORD: " + medicalRecord);
		Connection con = null;
        PreparedStatement ps = null;
        try {
        	
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.DELETE_MEDICALRECORD);
            //BIRTHDATE
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            
            deleteMedications(id);
            deleteAllergies(id);
            
            ps.execute();
            return medicalRecord;
        }catch (Exception ex){
            logger.error("Error updating medicalRecord",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
            dataBaseConfig.closePreparedStatement(ps);            
        }
        return null;
	}
	
	public boolean saveMedication(String medication, int medicalRecordId) {
		Connection con = null;
    	PreparedStatement ps = null;
    	try {
    		con = dataBaseConfig.getConnection();
    		ps = con.prepareStatement(DBConstants.SAVE_MEDICATION);
    		
    		ps.setString(1, medication);
    		ps.setInt(2, medicalRecordId);
    		return ps.execute();
    	}catch (Exception ex){
            logger.error("Error saving new person",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
            dataBaseConfig.closePreparedStatement(ps);            
        }
		return false;
	}
	
	public boolean deleteMedications(int medicalRecordId) {
		//System.out.println("deleting medications");
		Connection con = null;
	    PreparedStatement ps = null;
	    try {
	        con = dataBaseConfig.getConnection();
	        ps = con.prepareStatement(DBConstants.DELETE_MEDICATIONS);
	        //MEDICALRECORDID
	        ps.setInt(1, medicalRecordId);
	        //System.out.println(medicalRecordId);
	        //TODO REGARDER POURQUOI RETOURNE FALSE
	        return ps.execute();
	    }catch (Exception ex){
	        logger.error("Error deleting medications",ex);
	    }finally {
	        dataBaseConfig.closeConnection(con);
	        dataBaseConfig.closePreparedStatement(ps);            
	    }
	    return false;
	}
	
	public boolean saveAllergie(String allergie, int medicalRecordId) {
		Connection con = null;
    	PreparedStatement ps = null;
    	try {
    		con = dataBaseConfig.getConnection();
    		ps = con.prepareStatement(DBConstants.SAVE_ALLERGIE);
    		
    		ps.setString(1, allergie);
    		ps.setInt(2, medicalRecordId);
    		return ps.execute();
    	}catch (Exception ex){
            logger.error("Error saving allergie",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
            dataBaseConfig.closePreparedStatement(ps);            
        }
		return false;
		
	}
	
	public boolean deleteAllergies(int medicalRecordId) {
		Connection con = null;
	    PreparedStatement ps = null;
	    try {
	        con = dataBaseConfig.getConnection();
	        ps = con.prepareStatement(DBConstants.DELETE_ALLERGIES);
	        //ALLERGIE, MEDICALRECORDID
	        ps.setInt(1, medicalRecordId);
	        
	        return ps.execute();
	    }catch (Exception ex){
	        logger.error("Error deleting allergies",ex);
	    }finally {
	        dataBaseConfig.closeConnection(con);
	        dataBaseConfig.closePreparedStatement(ps);            
	    }
	    return false;
	}
		
	public int getId(MedicalRecord medicalRecord) {
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result=-1;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.GET_MEDICALRECORD_ID);
            ps.setString(1, medicalRecord.getFirstName());
            ps.setString(2, medicalRecord.getLastName());
            rs = ps.executeQuery();
            if(rs.next()){
                result = rs.getInt(1);;
            }
            
        }catch (Exception ex){
            logger.error("Error getting medicalRecord id",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        }
        return result;
		
	}
	
	public int getId(String firstName, String lastName) {		
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result=-1;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.GET_MEDICALRECORD_ID);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            rs = ps.executeQuery();
            if(rs.next()){
                result = rs.getInt(1);;
            }
            
        }catch (Exception ex){
            logger.error("Error getting medicalRecord id",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        }
        return result;
	}
	
	public MedicalRecord findById(int id) {		
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        MedicalRecord result = null;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.FIND_MEDICALRECORD_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
            	result = new MedicalRecord();
                result.setFirstName(rs.getString(1));
                result.setLastName(rs.getString(2));
                result.setBirthDate(rs.getString(3));
                result.setMedications(findMedicationsByMedicalRecordId(id));
                result.setAllergies(findAllergiesByMedicalRecordId(id));
            }
            
        }catch (Exception ex){
            logger.error("Error getting person id",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        }
        return result;
	}
	
	public List<String> findMedicationsByMedicalRecordId(int id) {		
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> result = null;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.FIND_MEDICATIONS_BY_MEDICALRECORD_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            result = new ArrayList<>();
            while(rs.next()){       	
                result.add(rs.getString(1));
            }
            
        }catch (Exception ex){
            logger.error("Error finding medications by medicalRecord id",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        }
        return result;
	}
	
	public List<String> findAllergiesByMedicalRecordId(int id) {		
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> result = null;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.FIND_ALLERGIES_BY_MEDICALRECORD_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            result = new ArrayList<>();
            while(rs.next()){           	
                result.add(rs.getString(1));
            }
            
        }catch (Exception ex){
            logger.error("Error finding allergies by medicalRecord id",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        }
        return result;
	}
	
	public boolean medicalRecordExists(MedicalRecord medicalRecord) {
    	Connection con = null;
    	PreparedStatement ps = null;
        ResultSet rs = null;

        try {
        	con = dataBaseConfig.getConnection();
        	
        	ps = con.prepareStatement(DBConstants.FIND_MEDICALRECORD);
            ps.setString(1, medicalRecord.getFirstName());
            ps.setString(2, medicalRecord.getLastName());
        	rs = ps.executeQuery();
        	if(rs.next()) {
        		return true;
        	}

        }catch (Exception ex){
            logger.error("Error checking medical record exists", ex);
        }finally {
        	dataBaseConfig.closeConnection(con);
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        }

		return false;
    }
}
