package com.safetynet.safetynetalerts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.config.DataBaseConfig;
import com.safetynet.safetynetalerts.constants.DBConstants;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.utils.DatesUtils;

@Repository
public class URLsRepository implements IURLsRepository {

	private static final Logger logger = LogManager.getLogger("URLsRepository");

	public DataBaseConfig dataBaseConfig = new DataBaseConfig();

	public String getFirestation(int stationNumber) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		JSONArray jsonArrayPersons = new JSONArray();
		int adults = 0;
		int childs = 0;
		try {
			con = dataBaseConfig.getConnection();
			ps = con.prepareStatement(DBConstants.GET_PERSONS_BY_STATION);
			ps.setInt(1, stationNumber);
			rs = ps.executeQuery();

			while (rs.next()) {

				JSONObject jsonObject = new JSONObject();
				jsonObject.put("firstName", rs.getString(1));
				jsonObject.put("lastName", rs.getString(2));
				jsonObject.put("address", rs.getString(3));
				jsonObject.put("phone", rs.getString(4));
				String birthDate = rs.getString(5);
				int age = DatesUtils.calculateAge(birthDate);
				if (age > 18) {
					adults++;
				} else {
					childs++;
				}
				// TODO FAIRE LE RESTE
				jsonArrayPersons.put(jsonObject);
			}

		} catch (Exception ex) {
			logger.error("Error getting medicalRecord id", ex);
		} finally {
			dataBaseConfig.closeConnection(con);
			dataBaseConfig.closeResultSet(rs);
			dataBaseConfig.closePreparedStatement(ps);
		}

		JSONObject result = new JSONObject();
		result.put("persons", jsonArrayPersons);
		result.put("adults", adults);
		result.put("childs", childs);

		return result.toString();

	}

	public String getChildAlert(String address) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		JSONObject result = new JSONObject();
		JSONArray childsArray = new JSONArray();
		JSONArray otherMembersArray = new JSONArray();

		try {
			con = dataBaseConfig.getConnection();
			ps = con.prepareStatement(DBConstants.GET_PERSONS_AND_BIRTHDATE_BY_ADDRESS);
			ps.setString(1, address);
			rs = ps.executeQuery();

			while (rs.next()) {

				JSONObject jsonObject = new JSONObject();
				jsonObject.put("firstName", rs.getString(1));
				jsonObject.put("lastName", rs.getString(2));

				String birthDate = rs.getString(3);
				int age = DatesUtils.calculateAge(birthDate);

				if (age <= 18) {
					jsonObject.put("age", age);
					childsArray.put(jsonObject);
				} else {
					otherMembersArray.put(jsonObject);
				}

			}

		} catch (Exception ex) {
			logger.error("Error getting phones by station", ex);
		} finally {
			dataBaseConfig.closeConnection(con);
			dataBaseConfig.closeResultSet(rs);
			dataBaseConfig.closePreparedStatement(ps);
		}
		result.put("childs", childsArray);
		result.put("other members", otherMembersArray);
		return result.toString();

	}

	public String getPhoneAlert(int firestation) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		JSONArray jsonArrayPhones = new JSONArray();

		try {
			con = dataBaseConfig.getConnection();
			ps = con.prepareStatement(DBConstants.GET_PHONES_BY_STATION);
			ps.setInt(1, firestation);
			rs = ps.executeQuery();

			while (rs.next()) {
				JSONObject jsonObject = new JSONObject();

				jsonObject.put("phone", rs.getString(1));

				jsonArrayPhones.put(jsonObject);

			}

		} catch (Exception ex) {
			logger.error("Error getting phones by station", ex);
		} finally {
			dataBaseConfig.closeConnection(con);
			dataBaseConfig.closeResultSet(rs);
			dataBaseConfig.closePreparedStatement(ps);
		}

		JSONObject result = new JSONObject();
		result.put("phones", jsonArrayPhones);
		return result.toString();

	}

	public String getFire(String address) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		JSONObject result = new JSONObject();
		JSONArray resultArray = new JSONArray();
		int station = 0;
		try {
			con = dataBaseConfig.getConnection();
			ps = con.prepareStatement(DBConstants.GET_PERSONS_AND_MEDICALRECORDS_STATION_BY_ADDRESS);
			ps.setString(1, address);
			rs = ps.executeQuery();

			while (rs.next()) {

				String firstName = rs.getString(1);
				String lastName = rs.getString(2);

				System.out.println(rs.getRow() + ": " + firstName + " " + lastName);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("firstName", firstName);
				jsonObject.put("lastName", lastName);
				jsonObject.put("phone", rs.getString(3));
				String birthDate = rs.getString(4);
				int age = DatesUtils.calculateAge(birthDate);
				jsonObject.put("age", age);

				JSONArray medications = new JSONArray();
				String medication = rs.getString(5);
				medications.put(medication);

				String allergie = rs.getString(6);
				JSONArray allergies = new JSONArray();
				allergies.put(allergie);

				station = rs.getInt(7);
				boolean nextPerson = false;
				while (!nextPerson) {
					if (rs.next()) {
						if (rs.getString(1).equals(firstName) && rs.getString(2).equals(lastName)) {

							if (rs.getString(5) != null && !rs.getString(5).equals(medication)) {
								medication = rs.getString(5);
								medications.put(medication);
							}

							if (rs.getString(6) != null && !rs.getString(6).equals(allergie)) {
								allergie = rs.getString(6);
								allergies.put(allergie);
							}

						} else {
							nextPerson = true;
							rs.previous();
						}

					} else {
						nextPerson = true;
					}

				}

				jsonObject.put("medications", medications);
				jsonObject.put("allergies", allergies);
				resultArray.put(jsonObject);

			}

		} catch (Exception ex) {
			logger.error("Error getting phones by station", ex);
		} finally {
			dataBaseConfig.closeConnection(con);
			dataBaseConfig.closeResultSet(rs);
			dataBaseConfig.closePreparedStatement(ps);
		}
		result.put("station", station);
		result.put("persons", resultArray);
		return result.toString();

	}

	// TODO CAS SOPHIA ZEMICKS 
	public String getFloodStations(List<Integer> stations) {
		JSONObject result = new JSONObject();
		JSONArray resultArray = new JSONArray();
		
		for (Integer station : stations) {
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				con = dataBaseConfig.getConnection();
				ps = con.prepareStatement(DBConstants.GET_PERSONS_AND_MEDICALRECORDS_BY_STATION);
				ps.setInt(1, station);
				rs = ps.executeQuery();

				while (rs.next()) {
					System.out.println("row: " + rs.getRow());
					JSONObject addressJson = new JSONObject();
					JSONArray persons = new JSONArray();

					String firstName = rs.getString(1);
					String lastName = rs.getString(2);

					System.out.println(rs.getRow() + ": " + firstName + " " + lastName);
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("firstName", firstName);
					jsonObject.put("lastName", lastName);

					String address = rs.getString(3);
					
					jsonObject.put("phone", rs.getString(4));

					String birthDate = rs.getString(5);
					int age = DatesUtils.calculateAge(birthDate);
					jsonObject.put("age", age);

					JSONArray medications = new JSONArray();
					String medication = rs.getString(6);
					medications.put(medication);

					String allergie = rs.getString(7);
					JSONArray allergies = new JSONArray();
					allergies.put(allergie);

					boolean nextPerson = false;
					while (!nextPerson) {
						if (rs.next()) {
							if (rs.getString(1).equals(firstName) && rs.getString(2).equals(lastName)) {

								if (rs.getString(6) != null && !rs.getString(6).equals(medication)) {
									medication = rs.getString(6);
									medications.put(medication);
								}

								if (rs.getString(7) != null && !rs.getString(7).equals(allergie)) {
									allergie = rs.getString(7);
									allergies.put(allergie);
								}

							} else {
								nextPerson = true;
								rs.previous();
							}

						} else {
							nextPerson = true;
						}

					}

					jsonObject.put("medications", medications);
					jsonObject.put("allergies", allergies);
					persons.put(jsonObject);
					
					boolean nextAddress = false;
					while (!nextAddress) {
						
						if(rs.next()) {
							if(rs.getString(3).equals(address)) {
								firstName = rs.getString(1);
								lastName = rs.getString(2);

								System.out.println(rs.getRow() + ": " + firstName + " " + lastName);
								jsonObject = new JSONObject();
								jsonObject.put("firstName", firstName);
								jsonObject.put("lastName", lastName);

								jsonObject.put("phone", rs.getString(4));

								birthDate = rs.getString(5);
								age = DatesUtils.calculateAge(birthDate);
								jsonObject.put("age", age);

								medications = new JSONArray();
								medication = rs.getString(6);
								medications.put(medication);

								allergie = rs.getString(7);
								allergies = new JSONArray();
								allergies.put(allergie);
								
								nextPerson = false;
								while (!nextPerson) {
									if (rs.next()) {
										if (rs.getString(1).equals(firstName) && rs.getString(2).equals(lastName)) {

											if (rs.getString(6) != null && !rs.getString(6).equals(medication)) {
												medication = rs.getString(6);
												medications.put(medication);
											}

											if (rs.getString(7) != null && !rs.getString(7).equals(allergie)) {
												allergie = rs.getString(7);
												allergies.put(allergie);
											}

										} else {
											nextPerson = true;
											rs.previous();
											System.out.println("row: " + rs.getRow());
										}

									} else {
										nextPerson = true;
									}

								}
								jsonObject.put("medications", medications);
								jsonObject.put("allergies", allergies);
								persons.put(jsonObject);
								
							} else {
								//addressJson.put("address", address);
								
								nextAddress = true;
								rs.previous();
								System.out.println("row: " + rs.getRow());
								addressJson.put("address", address);
								addressJson.put("persons", persons);
								resultArray.put(addressJson);
								
							}
						} else {
							//END LOOP
							nextAddress = true;
							addressJson.put("address", address);
							addressJson.put("persons", persons);
							resultArray.put(addressJson);
						}
						/*
						addressJson.put("address", address);
						addressJson.put("persons", persons);
						resultArray.put(addressJson);
						*/
						
					}
					
				}

			} catch (

			Exception ex) {
				logger.error("Error getting phones by station", ex);
			} finally {
				dataBaseConfig.closeConnection(con);
				dataBaseConfig.closeResultSet(rs);
				dataBaseConfig.closePreparedStatement(ps);
			}
			
			// result.put("persons", resultArray);
			
		}
		result.put("addresses", resultArray);
		return result.toString();
		
	}

	public String getPersonInfo(String firstName, String lastName) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		JSONObject jsonObject = new JSONObject();
		try {
			con = dataBaseConfig.getConnection();
			ps = con.prepareStatement(DBConstants.GET_PERSON_INFO);
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			rs = ps.executeQuery();

			if (rs.next()) {
				
				jsonObject.put("firstname", rs.getString(1));
				jsonObject.put("lastname", rs.getString(2));
				jsonObject.put("address", rs.getString(3));
				jsonObject.put("birthdate", rs.getString(4));
				jsonObject.put("email", rs.getString(5));
				JSONArray medications = new JSONArray();
				String medication = rs.getString(6);
				medications.put(medication);
				JSONArray allergies = new JSONArray();
				String allergie = rs.getString(7);
				allergies.put(allergie);
				while (rs.next()) {
					String newMedication = rs.getString(6);
					if (!newMedication.equals(medication)) {
						medications.put(newMedication);
						medication = newMedication;
					}
					String newAllergie = rs.getString(7);
					if (!newAllergie.equals(allergie)) {
						allergies.put(newAllergie);
						allergie = newAllergie;
					}
				}
				jsonObject.put("medications", medications);
				jsonObject.put("allergies", allergies);
				

			}

		} catch (Exception ex) {
			logger.error("Error getting emails by city", ex);
		} finally {
			dataBaseConfig.closeConnection(con);
			dataBaseConfig.closeResultSet(rs);
			dataBaseConfig.closePreparedStatement(ps);
		}
		
		return jsonObject.toString();

	}

	public String getCommunityEmail(String city) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		JSONObject result = new JSONObject();
		JSONArray resultArray = new JSONArray();

		try {
			con = dataBaseConfig.getConnection();
			ps = con.prepareStatement(DBConstants.GET_EMAILS_BY_CITY);
			ps.setString(1, city);
			rs = ps.executeQuery();

			while (rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("email", rs.getString(1));
				resultArray.put(jsonObject);

			}

		} catch (Exception ex) {
			logger.error("Error getting emails by city", ex);
		} finally {
			dataBaseConfig.closeConnection(con);
			dataBaseConfig.closeResultSet(rs);
			dataBaseConfig.closePreparedStatement(ps);
		}
		result.put("emails", resultArray);
		return result.toString();

	}
}
