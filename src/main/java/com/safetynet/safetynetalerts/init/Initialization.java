package com.safetynet.safetynetalerts.init;

import java.util.List;

import com.safetynet.safetynetalerts.dao.FirestationRepository;
import com.safetynet.safetynetalerts.dao.MedicalRecordRepository;
import com.safetynet.safetynetalerts.dao.PersonRepository;

import com.safetynet.safetynetalerts.json.ParsingJson;
import com.safetynet.safetynetalerts.json.ReadingJson;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

public class Initialization {
	
	private static PersonRepository personRepository;
	private static FirestationRepository firestationRepository;
	private static MedicalRecordRepository medicalRecordRepository;
	
	public static void init() {
		personRepository = new PersonRepository();
		firestationRepository = new FirestationRepository();
		medicalRecordRepository = new MedicalRecordRepository();
		System.out.println("Init");
		String json = ReadingJson.read();
		//System.out.println(json);
		//DatasDaoImpl datasDaoImpl = ParsingJson.parse(json);
		List<Person> persons = ParsingJson.getPersons(json);
		for(Person person : persons) {
			if(!personRepository.personExists(person)) {
				personRepository.savePerson(person);
			}
		}
		
		List<Firestation> firestations = ParsingJson.getFirestations(json);
		for(Firestation firestation : firestations) {
			if(!firestationRepository.firestationExists(firestation)) {
				firestationRepository.saveFirestation(firestation);
			}
		}
		
		List<MedicalRecord> medicalRecords = ParsingJson.getMedicalRecords(json);
		for(MedicalRecord medicalRecord : medicalRecords) {
			if(!medicalRecordRepository.medicalRecordExists(medicalRecord)) {
				medicalRecordRepository.saveMedicalRecord(medicalRecord);
			}
		}
		//ParsingJson.parse(json);
		//datasDaoImpl.setDatas();
		
	}
}
