package com.safetynet.safetynetalerts.utils;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class DatesUtils {

	public static int calculateAge(String sBirthDate) {
		String sBirthMonth= sBirthDate.substring(0, 2);
		String sBirthDay = sBirthDate.substring(3, 5);
		String sBirthYear = sBirthDate.substring(6, 10);

		int birthDay = Integer.parseInt(sBirthDay);
		int birthMonth = Integer.parseInt(sBirthMonth);
		int birthYear = Integer.parseInt(sBirthYear);
		
		LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);

		Date today = new Date();
		int todayDay = today.getDate();
		int todayMonth = today.getMonth() + 1;
		int todayYear = today.getYear() + 1900;

		LocalDate todayDate = LocalDate.of(todayYear, todayMonth, todayDay);

		return Period.between(birthDate, todayDate).getYears();
		
	}
}
