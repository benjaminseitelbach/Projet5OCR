package com.safetynet.safetynetalerts.integration.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.dao.PersonDao;
import com.safetynet.safetynetalerts.integration.service.DataBasePrepareService;
import com.safetynet.safetynetalerts.model.Person;

@SpringBootTest
public class PersonDaoIntegrationTest {

	@Autowired
	private PersonDao personDao;
	
	private static DataBasePrepareService dataBasePrepareService;
	
	private static String firstNameTest;
	private static String lastNameTest;
	
	@BeforeAll
	private static void setUp() throws Exception {
		System.out.println("Set up");
		dataBasePrepareService = new DataBasePrepareService();
		dataBasePrepareService.clearDataBaseEntries();
		firstNameTest = "FirstNameTest";
		lastNameTest = "LastNameTest";
	}
	
	@Test
	public void savePersonIT() {
		Person person = new Person();
		person.setFirstName(firstNameTest);
		person.setLastName(lastNameTest);
		person.setAddress("AddressTest");
		person.setCity("CityTest");
		person.setZip("ZipTest");
		person.setPhone("PhoneTest");
		person.setEmail("EmailTest");
		
		personDao.savePerson(person);
		
		Person personFound = dataBasePrepareService.findPerson(firstNameTest, lastNameTest);
		
		assertEquals(person.getAddress(), personFound.getAddress());
		assertEquals(person.getCity(), personFound.getCity());
		assertEquals(person.getZip(), personFound.getZip());
		assertEquals(person.getPhone(), personFound.getPhone());
		assertEquals(person.getEmail(), personFound.getEmail());
	}
	
	@Test
	public void updatePersonIT() {
		Person person = new Person();
		person.setFirstName(firstNameTest);
		person.setLastName(lastNameTest);
		person.setAddress("NewAddressTest");
		person.setCity("NewCityTest");
		person.setZip("NewZipTest");
		person.setPhone("NewPhoneTest");
		person.setEmail("NewEmailTest");
		
		personDao.updatePerson(person);
		
		Person personFound = dataBasePrepareService.findPerson(firstNameTest, lastNameTest);
		
		assertEquals(person.getAddress(), personFound.getAddress());
		assertEquals(person.getCity(), personFound.getCity());
		assertEquals(person.getZip(), personFound.getZip());
		assertEquals(person.getPhone(), personFound.getPhone());
		assertEquals(person.getEmail(), personFound.getEmail());
	}
	
	@Test
	public void deletePersonIT() {

		personDao.deletePerson(firstNameTest, lastNameTest);
		
		Person personFound = dataBasePrepareService.findPerson(firstNameTest, lastNameTest);
		
		assertEquals(null, personFound.getFirstName());
		assertEquals(null, personFound.getLastName());
		assertEquals(null, personFound.getAddress());
		assertEquals(null, personFound.getCity());
		assertEquals(null, personFound.getZip());
		assertEquals(null, personFound.getPhone());
		assertEquals(null, personFound.getEmail());
	}
	
}
