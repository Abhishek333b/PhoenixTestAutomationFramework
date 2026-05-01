package com.api.utils;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.api.constant.Role.*;

import com.api.constant.Role;
import com.api.pojo.UserCredentials;
import com.api.services.AuthService;

import io.restassured.http.ContentType;

public class AuthTokenProvider {
	private static Map<Role, String> tokencache = new ConcurrentHashMap<Role, String>();
	private static final Logger LOGGER = LogManager.getLogger(AuthTokenProvider.class);
	
	private AuthTokenProvider() {
		
	}

	public static String getToken(Role role) {
		
		LOGGER.info("Checking if the token for {} is present in cache",role);
		if(tokencache.containsKey(role)) {
			LOGGER.info(" token found  for {} ",role);
			return tokencache.get(role);
		}
		LOGGER.info(" token not  found making login request for role{} ",role);
		UserCredentials userCredentials = null;
		if(role==FD) {
			userCredentials = new UserCredentials("iamfd","password");
		}
		else if(role==SUP) {
			userCredentials = new UserCredentials("iamsup","password");
			
		}
		else if(role==ENG) {
			userCredentials = new UserCredentials("iameng","password");
			
		}
		else if(role==QC) {
			userCredentials = new UserCredentials("iamqc","password");
			
		}
		
		
		
	String token =	given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.contentType(ContentType.JSON)
		.body(userCredentials)
		.when()
		.post("login")
		.then()
		.log()
		.ifValidationFails()
		.statusCode(200)
		.body("message",equalTo("Success"))
		.extract()
		.body()
		.jsonPath()
		.getString("data.token");
	
	LOGGER.info("TOken cached for future request");
	tokencache.put(role, token);
	
	return token;
	
	}

}
