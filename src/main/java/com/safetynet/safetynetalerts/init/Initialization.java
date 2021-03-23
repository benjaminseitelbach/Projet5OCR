package com.safetynet.safetynetalerts.init;

import java.util.List;


import com.safetynet.safetynetalerts.dao.FirestationDaoImpl;
import com.safetynet.safetynetalerts.dao.MedicalRecordDaoImpl;
import com.safetynet.safetynetalerts.dao.PersonDaoImpl;
import com.safetynet.safetynetalerts.json.ParsingJson;
import com.safetynet.safetynetalerts.json.ReadingJson;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

public class Initialization {
	
	private static PersonDaoImpl personDaoImpl;
	private static FirestationDaoImpl firestationDaoImpl;
	private static MedicalRecordDaoImpl medicalRecordDaoImpl;
	
	public static void init() {
		personDaoImpl = new PersonDaoImpl();
		firestationDaoImpl = new FirestationDaoImpl();
		medicalRecordDaoImpl = new MedicalRecordDaoImpl();
		System.out.println("Init");
		String json = ReadingJson.read();
		//System.out.println(json);
		//DatasDaoImpl datasDaoImpl = ParsingJson.parse(json);
		List<Person> persons = ParsingJson.getPersons(json);
		for(Person person : persons) {
			if(!personDaoImpl.personExists(person)) {
				personDaoImpl.savePerson(person);
			}
		}
		
		List<Firestation> firestations = ParsingJson.getFirestations(json);
		for(Firestation firestation : firestations) {
			if(!firestationDaoImpl.firestationExists(firestation)) {
				firestationDaoImpl.saveFirestation(firestation);
			}
		}
		
		List<MedicalRecord> medicalRecords = ParsingJson.getMedicalRecords(json);
		for(MedicalRecord medicalRecord : medicalRecords) {
			if(!medicalRecordDaoImpl.medicalRecordExists(medicalRecord)) {
				medicalRecordDaoImpl.saveMedicalRecord(medicalRecord);
			}
		}
		//ParsingJson.parse(json);
		//datasDaoImpl.setDatas();
		
	}
}
