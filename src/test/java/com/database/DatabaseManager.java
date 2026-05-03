package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.EnvUtil;
import com.api.utils.VaultDBConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseManager {
	private static final Logger LOGGER = LogManager.getLogger(DatabaseManager.class);
	
	private static final int MAXIMUM_POOL_SIZE = Integer.parseInt(ConfigManager.getProperty("MAXIMUM_POOL_SIZE"));
	private static final int MINIMUM_IDLE_COUNT = Integer.parseInt(ConfigManager.getProperty("MINIMUM_IDLE_COUNT"));
	private static final int CONNECTION_TIMEOUT_IN_SECS = Integer
			.parseInt(ConfigManager.getProperty("CONNECTION_TIMEOUT_IN_SECS")) * 1000;
	private static final int IDLE_TIMEOUT_SECS = Integer.parseInt(ConfigManager.getProperty("IDLE_TIMEOUT_SECS"));
	private static final int MAX_LIFE_TIME_IN_MINS = Integer
			.parseInt(ConfigManager.getProperty("CONNECTION_TIMEOUT_IN_SECS")) * 1000;
	private static final String HIKARI_CP_POOL_NAME = ConfigManager.getProperty("HIKARI_CP_POOL_NAME");

	private static HikariConfig hikariConfig;
	private volatile static HikariDataSource hikariDataSource = null;

	private static boolean isVaultUp = true;
	private static final String DB_URL = loadSecret("DB_URL");
	private static final String DB_USER_NAME = loadSecret("DB_USER_NAME");
	private static final String DB_PASSWORD = loadSecret("DB_PASSWORD");
	

	public static String loadSecret(String key) {
		String value = null;

		if (isVaultUp) {
			value = VaultDBConfig.getSecret(key);
			
		}

		if (value == null) {
			LOGGER.error("valult is down !! OR Some issue with Vault");
			isVaultUp = false;
		} else {
			System.out.println();
			LOGGER.info("Reading value for key-{} from Vault...",key);
			return value;
		}
		LOGGER.info("Reading value from ENV...");
		value = EnvUtil.getValue(key);
		return value;
	}

	private static Connection conn;

	private DatabaseManager() {

	}

	private static void intializePool() {

		if (hikariDataSource == null) {
			LOGGER.warn("Database connection is not available creating hikariDatasource");
			synchronized (DatabaseManager.class) {
				if (hikariDataSource == null) {
					HikariConfig hikariConfig = new HikariConfig();
					hikariConfig.setJdbcUrl(DB_URL);
					hikariConfig.setUsername(DB_USER_NAME);
					hikariConfig.setPassword(DB_PASSWORD);
					hikariConfig.setMaximumPoolSize(MAXIMUM_POOL_SIZE);
					hikariConfig.setMinimumIdle(MINIMUM_IDLE_COUNT);
					hikariConfig.setConnectionTimeout(CONNECTION_TIMEOUT_IN_SECS * 1000);
					hikariConfig.setIdleTimeout(IDLE_TIMEOUT_SECS * 1000);
					hikariConfig.setMaxLifetime(MAX_LIFE_TIME_IN_MINS * 60 * 1000);
					hikariConfig.setPoolName(HIKARI_CP_POOL_NAME);

					hikariDataSource = new HikariDataSource(hikariConfig);
					LOGGER.info("Hikari datasource created.");

				}

			}

		}
	}

	public static Connection getConnection() throws SQLException {
		Connection connection = null;
		if (hikariDataSource == null) {
			LOGGER.info("Initializing database connection using HikariCP");
			intializePool();
		} else if (hikariDataSource.isClosed()) {
			LOGGER.error("Initializing database connection using HikariCP");
			throw new SQLException("hikari data source is closed");
		}

		connection = hikariDataSource.getConnection();

		return connection;
	}

}
