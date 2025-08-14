package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.constants.Env;
import com.google.gson.Gson;
import com.ui.pojo.Environment;
import com.ui.pojo.config;

public class JSONUtility {

	public static Environment readJson(Env env) {
		Gson gson = new Gson();
		File jsonFile = new File((System.getProperty("user.dir")) + "\\config\\config.json");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(jsonFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		config config = gson.fromJson(fileReader, config.class);
		Environment environment = config.getEnvironments().get("QA");
		return environment;

	}

}
