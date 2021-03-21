package com.safetynet.safetynetalerts.dao;

import java.util.List;

import com.safetynet.safetynetalerts.model.Person;

public interface PersonDao {

	public Person savePerson(Person person);
	
	public Person updatePerson(Person person);
	
	public Person deletePerson(String firstName, String lastName);
	
	public List<Person> getPersons();
}
