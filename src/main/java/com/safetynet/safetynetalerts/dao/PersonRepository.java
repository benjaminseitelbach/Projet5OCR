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
import com.safetynet.safetynetalerts.model.Person;

import com.safetynet.safetynetalerts.constants.DBConstants;

@Repository
public class PersonRepository implements IPersonRepository {
	
	private static final Logger logger = LogManager.getLogger("PersonDaoImpl");

    public DataBaseConfig dataBaseConfig = new DataBaseConfig();
	
	public Person savePerson(Person person) {
		//System.out.println(person);
		Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.SAVE_PERSON);
            //FIRST_NAME, LAST_NAME, ADDRESS, CITY, ZIP, PHONE, EMAIL
            ps.setString(1, person.getFirstName());
            ps.setString(2, person.getLastName());
            ps.setString(3, person.getAddress());
            ps.setString(4, person.getCity());
            ps.setString(5, person.getZip());
            ps.setString(6, person.getPhone());
            ps.setString(7, person.getEmail());
            ps.execute();
            return person;
        }catch (Exception ex){
            logger.error("Error saving new person",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
            dataBaseConfig.closePreparedStatement(ps);            
        }
        return null;
	}
	
	public Person updatePerson(Person person) {
		Connection con = null;
        PreparedStatement ps = null;
        try {
        	int id = getId(person);
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.UPDATE_PERSON);
            //ADDRESS, CITY, ZIP, PHONE, EMAIL, ID
            ps.setString(1, person.getAddress());
            ps.setString(2, person.getCity());
            ps.setString(3, person.getZip());
            ps.setString(4, person.getPhone());
            ps.setString(5, person.getEmail());
            ps.setInt(6, id);
            ps.execute();
            return person;
        }catch (Exception ex){
            logger.error("Error updating person",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
            dataBaseConfig.closePreparedStatement(ps);            
        }
        return null;

	}

	public Person deletePerson(String firstName, String lastName) {
		int id = getId(firstName, lastName);
		Person person = findById(id);
		Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.DELETE_PERSON);
            //FIRSTNAME, LASTNAME
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.execute();
            return person;
        }catch (Exception ex){
            logger.error("Error deleting person",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
            dataBaseConfig.closePreparedStatement(ps);            
        }
		return null;
	
	}
	
	public int getId(Person person) {		
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result=-1;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.GET_PERSON_ID);
            ps.setString(1, person.getFirstName());
            ps.setString(2, person.getLastName());
            rs = ps.executeQuery();
            if(rs.next()){
                result = rs.getInt(1);;
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
	
	public int getId(String firstName, String lastName) {		
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result=-1;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.GET_PERSON_ID);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            rs = ps.executeQuery();
            if(rs.next()){
                result = rs.getInt(1);;
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
	
	public Person findById(int id) {		
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Person result = null;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.FIND_PERSON_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
            	result = new Person();
                result.setFirstName(rs.getString(1));
                result.setLastName(rs.getString(2));
                result.setAddress(rs.getString(3));
                result.setCity(rs.getString(4));
                result.setZip(rs.getString(5));
                result.setPhone(rs.getString(6));
                result.setEmail(rs.getString(7));
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
	
	public boolean personExists(Person person) {
    	Connection con = null;
    	PreparedStatement ps = null;
        ResultSet rs = null;

        try {
        	con = dataBaseConfig.getConnection();
        	
        	ps = con.prepareStatement(DBConstants.FIND_PERSON_BY_NAME);
            ps.setString(1, person.getFirstName());
            ps.setString(2, person.getLastName());
        	rs = ps.executeQuery();
        	if(rs.next()) {
        		//System.out.println("person exists");
        		return true;
        	}

        }catch (Exception ex){
            logger.error("Error verifying person exists", ex);
        }finally {
        	dataBaseConfig.closeConnection(con);
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        }

		return false;
    }
	
	public List<Person> getPersons() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Person> persons = new ArrayList<>();
		
		try {
        	con = dataBaseConfig.getConnection();
        	
        	ps = con.prepareStatement("SELECT * FROM PERSON");
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		Person person = new Person();
        		person.setFirstName(rs.getString(1));
        		person.setLastName(rs.getString(2));
        		persons.add(person);
        	}
        	return persons;

        }catch (Exception ex){
            logger.error("Error verifying person exists", ex);
        }finally {
        	dataBaseConfig.closeConnection(con);
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        }
		
		return null;
		
	}
	
}
