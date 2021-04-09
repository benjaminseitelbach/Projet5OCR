package com.safetynet.safetynetalerts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.safetynet.safetynetalerts.config.DataBaseConfig;
import com.safetynet.safetynetalerts.constants.DBConstants;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

@Repository
public class FirestationRepository implements IFirestationRepository {
	
	private static final Logger logger = LogManager.getLogger("FirestationRepository");

    public DataBaseConfig dataBaseConfig = new DataBaseConfig();
    
    public Firestation saveFirestation(Firestation firestation) {

		Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.SAVE_FIRESTATION);
            //ADDRESS, STATION
            ps.setString(1, firestation.getAddress());
            ps.setInt(2, firestation.getStation());
            ps.execute();
            return firestation;
        }catch (Exception ex){
            logger.error("Error saving new firestation",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
            dataBaseConfig.closePreparedStatement(ps);            
        }
        return null;
	}
    
    public Firestation updateFirestation(Firestation firestation) {
    	Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.UPDATE_FIRESTATION);
            //STATION, ADDRESS
            ps.setInt(1, firestation.getStation());
            ps.setString(2, firestation.getAddress());

            ps.execute();
            return firestation;
        }catch (Exception ex){
            logger.error("Error updating person",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
            dataBaseConfig.closePreparedStatement(ps);            
        }
        return null;
    }
    
    public Firestation deleteFirestation(Firestation firestation) {
		Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.DELETE_FIRESTATION);
            //ADDRESS, STATION
            ps.setString(1, firestation.getAddress());
            ps.setInt(2, firestation.getStation());
            ps.execute();
            return firestation;
        }catch (Exception ex){
            logger.error("Error deleting firestation",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
            dataBaseConfig.closePreparedStatement(ps);            
        }
		return null;
    }
    
    public boolean firestationExists(Firestation firestation) {
    	Connection con = null;
    	PreparedStatement ps = null;
        ResultSet rs = null;

        try {
        	con = dataBaseConfig.getConnection();
        	
        	ps = con.prepareStatement(DBConstants.FIND_FIRESTATION);
            ps.setString(1, firestation.getAddress());
            ps.setInt(2, firestation.getStation());
        	rs = ps.executeQuery();
        	if(rs.next()) {
        		return true;
        	}

        }catch (Exception ex){
            logger.error("Error checking firestation exists", ex);
        }finally {
        	dataBaseConfig.closeConnection(con);
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        }

		return false;
    }
	
}
