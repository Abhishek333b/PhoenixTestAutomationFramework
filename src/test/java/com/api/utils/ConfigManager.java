package com.api.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.imageio.IIOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigManager {
	private static Properties prop = new Properties();
	private static String path = "config/config.properties";
	private static String env;
	private static final Logger LOGGER = LogManager.getLogger(ConfigManager.class);

	private ConfigManager() {

	}

	static {
		LOGGER.info("Reading env value passed from terminal");
		if (System.getProperty("env") == null) {
			LOGGER.warn("env varible is not set .using qa as the env");
		}
		env = System.getProperty("env", "qa");
		LOGGER.info("Running test in Env {}", env);
		env = env.toLowerCase().trim();

		switch (env) {
		case "dev" -> path = "config/config.dev.properties";

		case "qa" -> path = "config/config.qa.properties";

		case "uat" -> path = "config/config.uat.properties";
		default -> path = "config/config.qa.properties";
		}
		LOGGER.info("Using properties file from path{}", path);

		InputStream input = Thread.currentThread().getContextClassLoader().getSystemResourceAsStream(path);
		if (input == null) {
			LOGGER.error("Can not find the path", path);
			throw new RuntimeException("can not find file at path " + path);
		}
		try {

			prop.load(input);
		} catch (FileNotFoundException e) {
			LOGGER.error("Can not find thefile in path{}", path,e);
			e.printStackTrace();
		}catch(IOException e) {
			LOGGER.error("Something went wrong please chek file{}",path,e);
		}

	}

	public static String getProperty(String key) {
		return prop.getProperty(key);
	}

}
