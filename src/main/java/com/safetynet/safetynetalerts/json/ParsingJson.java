package com.safetynet.safetynetalerts.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.safetynet.safetynetalerts.dao.FirestationDaoImpl;
import com.safetynet.safetynetalerts.dao.MedicalRecordDaoImpl;
import com.safetynet.safetynetalerts.dao.PersonDaoImpl;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

public class ParsingJson {

	public static void parse(String jsonString) {

		PersonDaoImpl personDaoImpl = new PersonDaoImpl();
		FirestationDaoImpl firestationDaoImpl = new FirestationDaoImpl();
		MedicalRecordDaoImpl medicalRecordDaoImpl = new MedicalRecordDaoImpl();
		JSONObject json = new JSONObject(jsonString);
		JSONArray personsArray = json.getJSONArray("persons");
		for (int line = 0; line < personsArray.length(); line++) {
			JSONObject personObject = personsArray.getJSONObject(line);
			Person person = new Person();
			person.setFirstName(personObject.getString("firstName"));
			person.setLastName(personObject.getString("lastName"));
			person.setAddress(personObject.getString("address"));
			person.setCity(personObject.getString("city"));
			person.setZip(personObject.getString("zip"));
			person.setPhone(personObject.getString("phone"));
			person.setEmail(personObject.getString("email"));
			personDaoImpl.savePerson(person);
		}

		JSONArray firestationsArray = json.getJSONArray("firestations");
		for (int line = 0; line < firestationsArray.length(); line++) {
			JSONObject firestationObject = firestationsArray.getJSONObject(line);
			Firestation firestation = new Firestation();
			firestation.setAddress(firestationObject.getString("address"));
			firestation.setStation(firestationObject.getInt("station"));
			firestationDaoImpl.saveFirestation(firestation);
		}

		JSONArray medicalRecordsArray = json.getJSONArray("medicalrecords");
		for (int line = 0; line < medicalRecordsArray.length(); line++) {
			JSONObject medicalRecordObject = medicalRecordsArray.getJSONObject(line);
			MedicalRecord medicalRecord = new MedicalRecord();
			medicalRecord.setFirstName(medicalRecordObject.getString("firstName"));
			medicalRecord.setLastName(medicalRecordObject.getString("lastName"));
			medicalRecord.setBirthDate(medicalRecordObject.getString("birthdate"));
			JSONArray medicationsArray = medicalRecordObject.getJSONArray("medications");
			List<String> medications = new ArrayList<>();
			for (int medication = 0; medication < medicationsArray.length(); medication++) {
				medications.add(medicationsArray.getString(medication));
			}
			medicalRecord.setMedications(medications);

			JSONArray allergiesArray = medicalRecordObject.getJSONArray("allergies");
			List<String> allergies = new ArrayList<>();
			for (int allergie = 0; allergie < allergiesArray.length(); allergie++) {
				allergies.add(allergiesArray.getString(allergie));
			}
			medicalRecord.setAllergies(allergies);
			medicalRecordDaoImpl.saveMedicalRecord(medicalRecord);
		}

	}

	public static List<Person> getPersons(String jsonString) {
		List<Person> result = new ArrayList<>();
		JSONObject json = new JSONObject(jsonString);
		JSONArray personsArray = json.getJSONArray("persons");
		for(int line = 0; line < personsArray.length(); line ++) {
			JSONObject personObject = personsArray.getJSONObject(line);
			Person person = new Person();
    		person.setFirstName(personObject.getString("firstName"));
    		person.setLastName(personObject.getString("lastName"));
    		person.setAddress(personObject.getString("address"));
    		person.setCity(personObject.getString("city"));
    		person.setZip(personObject.getString("zip"));
    		person.setPhone(personObject.getString("phone"));
    		person.setEmail(personObject.getString("email"));
    		result.add(person);
		}
		return result;
	}
	
	public static List<Firestation> getFirestations(String jsonString) {
		List<Firestation> result = new ArrayList<>();
		JSONObject json = new JSONObject(jsonString);
		JSONArray firestationsArray = json.getJSONArray("firestations");
		for (int line = 0; line < firestationsArray.length(); line++) {
			JSONObject firestationObject = firestationsArray.getJSONObject(line);
			Firestation firestation = new Firestation();
			firestation.setAddress(firestationObject.getString("address"));
			firestation.setStation(firestationObject.getInt("station"));
			result.add(firestation);
		}
		return result;
	}
	
	public static List<MedicalRecord> getMedicalRecords(String jsonString) {
		List<MedicalRecord> result = new ArrayList<>();
		JSONObject json = new JSONObject(jsonString);
		JSONArray medicalRecordsArray = json.getJSONArray("medicalrecords");
		for (int line = 0; line < medicalRecordsArray.length(); line++) {
			JSONObject medicalRecordObject = medicalRecordsArray.getJSONObject(line);
			MedicalRecord medicalRecord = new MedicalRecord();
			medicalRecord.setFirstName(medicalRecordObject.getString("firstName"));
			medicalRecord.setLastName(medicalRecordObject.getString("lastName"));
			medicalRecord.setBirthDate(medicalRecordObject.getString("birthdate"));
			JSONArray medicationsArray = medicalRecordObject.getJSONArray("medications");
			List<String> medications = new ArrayList<>();
			for (int medication = 0; medication < medicationsArray.length(); medication++) {
				medications.add(medicationsArray.getString(medication));
			}
			medicalRecord.setMedications(medications);

			JSONArray allergiesArray = medicalRecordObject.getJSONArray("allergies");
			List<String> allergies = new ArrayList<>();
			for (int allergie = 0; allergie < allergiesArray.length(); allergie++) {
				allergies.add(allergiesArray.getString(allergie));
			}
			medicalRecord.setAllergies(allergies);
			result.add(medicalRecord);
		}
		return result;
	}
	
	
}
