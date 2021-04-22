package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.model.Person;

public interface IPersonRepository {

	public Person savePerson(Person person);
	
	public Person updatePerson(Person person);
	
	public Person deletePerson(String firstName, String lastName);
	
}
