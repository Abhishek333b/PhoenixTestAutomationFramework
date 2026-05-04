package com.api.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.File;

public class AllureEnvironmentWriterUtil {
	private static final Logger LOGGER = LogManager.getLogger(AllureEnvironmentWriterUtil.class);

	public static void createEnvironmentPropertiesFile() {
		String folderPath = "target/allure-results";
		File file = new File(folderPath);
		file.mkdirs();

		Properties prop = new Properties();
		prop.setProperty("Name", "jatin");
		prop.setProperty("Project Name", "Phoenix Test Automation Framework");
		prop.setProperty("Env", ConfigManager.env);
		prop.setProperty("BASE_URI", ConfigManager.getProperty("BASE_URI"));

		prop.setProperty("Operating system", System.getProperty("os.name"));
		prop.setProperty("java version", System.getProperty("java.version"));

	//	FileWriter fw = null;
		try(FileWriter fw = new FileWriter(folderPath + File.separator + "environment.properties")) 
		{
			prop.store(fw, "My properties File");
			
			LOGGER.info("Created the environment.properties file at {}", folderPath);

		} catch (IOException e) {
			LOGGER.error("unable to create the environment.properties file", e);
			
		}

	}

}
