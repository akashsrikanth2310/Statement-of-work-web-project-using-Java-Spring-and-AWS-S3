package com.accolite.ppm.sow.v1.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
public class AlertConfigImplementation implements AlertConfig {

	JSONObject alertObject = new JSONObject();

	@Override
	public boolean sendAlert(String status) {

		JSONParser parser = new JSONParser();
		try {
			File file = new File("TestFile/abc.txt");
			Object obj = parser.parse(new FileReader("/alertConfiguration/alertObject.json"));
			alertObject = (JSONObject) obj;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println(alertObject);
		return false;
	}

}
