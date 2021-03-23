package com.safetynet.safetynetalerts.json;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.safetynet.safetynetalerts.config.Config;

public class ReadingJson {

	public static String read() {
		BufferedReader reader = null;
		String result = "";
		
		try {
			reader = new BufferedReader(new FileReader(Config.DATAFILENAME));

			try {
				String line = reader.readLine();

				// Loop on all file
				while (line != null) {

					result += (line + "\n");

					line = reader.readLine();
				}

				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return result;
		
	}
}
