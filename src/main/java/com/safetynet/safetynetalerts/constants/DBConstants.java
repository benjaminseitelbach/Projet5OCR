package com.safetynet.safetynetalerts.constants;

public class DBConstants {
	
	public static final String SAVE_PERSON = "insert into person(FIRST_NAME, LAST_NAME, ADDRESS, CITY, ZIP, PHONE, EMAIL) values(?,?,?,?,?,?,?)";
	public static final String UPDATE_PERSON = "update person set ADDRESS=?, CITY=?, ZIP=?, PHONE=?, EMAIL=? where ID=?";
	public static final String DELETE_PERSON = "delete from person where FIRST_NAME=? and LAST_NAME=?";
	public static final String GET_PERSON_ID = "select ID from person where FIRST_NAME=? AND LAST_NAME=?";
	public static final String FIND_PERSON_BY_ID = "select * from person where ID=?";
	public static final String FIND_PERSON_BY_NAME = "select * from person where FIRST_NAME = ? and LAST_NAME = ?";
	
	public static final String SAVE_FIRESTATION = "insert into firestation(ADDRESS, STATION) values (?,?)";
	public static final String UPDATE_FIRESTATION = "update firestation set STATION=? where ADDRESS=?";
	public static final String DELETE_FIRESTATION = "delete from firestation where ADDRESS=? and STATION=?";
	public static final String FIND_FIRESTATION = "select * from firestation where ADDRESS=? and STATION=?";
	
	public static final String SAVE_MEDICALRECORD = "insert into medicalRecord(FIRST_NAME, LAST_NAME, BIRTHDATE) values(?,?,?)";
	public static final String UPDATE_MEDICALRECORD = "update medicalRecord set BIRTHDATE=? where id=?";
	public static final String SAVE_MEDICATION = "insert into medication(NAME, medicalRecord_ID) values(?,?)";
	public static final String UPDATE_MEDICATION = "update medication set NAME=? where medicalRecord_ID=?";
	public static final String DELETE_MEDICATIONS = "delete from medication where medicalRecord_ID=?";
	public static final String SAVE_ALLERGIE = "insert into allergie(NAME, medicalRecord_ID) values(?,?)";
	public static final String UPDATE_ALLERGIE = "update allergie set NAME=? where medicalRecord_ID=?";
	public static final String DELETE_ALLERGIES = "delete from allergie where medicalRecord_ID=?";
	public static final String GET_MEDICALRECORD_ID = "select ID from medicalRecord where FIRST_NAME=? AND LAST_NAME=?";
	public static final String DELETE_MEDICALRECORD = "delete from medicalRecord where FIRST_NAME=? and LAST_NAME=?";
	public static final String FIND_MEDICALRECORD_BY_ID = "select FIRST_NAME, LAST_NAME, BIRTHDATE from medicalRecord where ID=?";
	public static final String FIND_MEDICATIONS_BY_MEDICALRECORD_ID = "select NAME from medication where medicalRecord_ID=?";
	public static final String FIND_ALLERGIES_BY_MEDICALRECORD_ID = "select NAME from allergie where medicalRecord_ID=?";
	public static final String FIND_MEDICALRECORD = "select * from medicalRecord where FIRST_NAME=? and LAST_NAME=?";
}