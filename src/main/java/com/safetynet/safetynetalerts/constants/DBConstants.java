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
	
	public static final String GET_PERSONS_BY_STATION = "select PERSON.FIRST_NAME, PERSON.LAST_NAME, PERSON.ADDRESS, PHONE, BIRTHDATE from person"
			+ " left join firestation on PERSON.ADDRESS=FIRESTATION.ADDRESS left join medicalRecord on PERSON.FIRST_NAME = MEDICALRECORD.FIRST_NAME"
			+ " and PERSON.LAST_NAME = MEDICALRECORD.LAST_NAME where STATION=?";
	public static final String GET_PERSONS_AND_BIRTHDATE_BY_ADDRESS = "select PERSON.FIRST_NAME, PERSON.LAST_NAME, BIRTHDATE from person"
			+ " left join medicalRecord on PERSON.FIRST_NAME=MEDICALRECORD.FIRST_NAME and PERSON.LAST_NAME=MEDICALRECORD.LAST_NAME where ADDRESS=?";
	public static final String GET_PHONES_BY_STATION = "select PHONE from person left join firestation on PERSON.ADDRESS=FIRESTATION.ADDRESS"
			+ " where STATION=?";
	public static final String GET_PERSONS_AND_MEDICALRECORDS_STATION_BY_ADDRESS = "select PERSON.FIRST_NAME, PERSON.LAST_NAME, PHONE,"
			+ " BIRTHDATE, MEDICATION.NAME, ALLERGIE.NAME, STATION from person"
			+ " left join medicalRecord on PERSON.FIRST_NAME=MEDICALRECORD.FIRST_NAME AND PERSON.LAST_NAME=MEDICALRECORD.LAST_NAME"
			+ " left join medication on medicalRecord.ID=MEDICATION.medicalRecord_ID"
			+ " left join allergie on medicalRecord.ID=ALLERGIE.medicalRecord_ID"
			+ " left join firestation on PERSON.ADDRESS=FIRESTATION.ADDRESS where PERSON.ADDRESS=?";
	public static final String GET_PERSONS_AND_MEDICALRECORDS_BY_STATION ="select person.FIRST_NAME, person.LAST_NAME, person.ADDRESS,"
			+ " PHONE, medicalRecord.BIRTHDATE, medication.NAME, allergie.NAME from person"
			+ " left join medicalRecord on person.FIRST_NAME=medicalRecord.FIRST_NAME and person.LAST_NAME=medicalRecord.LAST_NAME"
			+ " left join medication on medicalRecord.ID=medication.medicalRecord_ID"
			+ " left join allergie on medicalRecord.ID=allergie.medicalRecord_ID"
			+ " left join firestation on person.ADDRESS=firestation.ADDRESS where station=?";
	public static final String GET_PERSON_INFO = "select PERSON.FIRST_NAME, PERSON.LAST_NAME, ADDRESS, BIRTHDATE, EMAIL, MEDICATION.NAME,"
			+ " ALLERGIE.NAME from person"
			+ " left join medicalRecord on PERSON.FIRST_NAME=MEDICALRECORD.FIRST_NAME AND PERSON.LAST_NAME=MEDICALRECORD.LAST_NAME"
			+ " left join medication on MEDICALRECORD.ID=MEDICATION.medicalRecord_ID"
			+ " left join allergie on MEDICALRECORD.ID=ALLERGIE.medicalRecord_ID where PERSON.FIRST_NAME=? and PERSON.LAST_NAME=?";
	public static final String GET_EMAILS_BY_CITY = "select EMAIL from person where CITY=?";
}