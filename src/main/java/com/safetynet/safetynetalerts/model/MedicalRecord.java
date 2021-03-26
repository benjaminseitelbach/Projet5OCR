package com.safetynet.safetynetalerts.model;

import java.util.List;

public class MedicalRecord {
	private String firstName;
	private String lastName;	
	private String birthDate;
	private List<String> medications;
	private List<String> allergies;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}	
	public List<String> getMedications() {
		return medications;
	}
	public void setMedications(List<String> medications) {
		this.medications = medications;
	}
	public List<String> getAllergies() {
		return allergies;
	}
	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}
	
	public String medicationsToJson() {
		String result = "[";
		int size = medications.size();
		for(int iMedication = 0; iMedication < size; iMedication ++) {
			String medication = medications.get(iMedication);
			if(iMedication == size - 1) {
				result += "\"" + medication + "\"";
			} else {
				result += "\"" + medication + "\", ";
			}
			
		}
		result += "]";
		return result;
	}
	
	public String allergiesToJson() {
		String result = "[";
		int size = allergies.size();
		for(int iAllergie = 0; iAllergie < size; iAllergie ++) {
			String allergie = allergies.get(iAllergie);
			if(iAllergie == size - 1) {
				result += "\"" + allergie + "\"";
			} else {
				result += "\"" + allergie + "\", ";
			}
			
		}
		result += "]";
		return result;
	}
	
	public String toJson() {
		return "{ \"firstName\":\"" + firstName + "\", \"lastName\":\"" + lastName + "\", \"birthdate\":\"" + birthDate + 
				"\", \"medications\":" + medicationsToJson() + ", \"allergies\":" + allergiesToJson() + "}";
	}
	
}
