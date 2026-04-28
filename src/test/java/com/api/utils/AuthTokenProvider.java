package com.api.utils;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.api.constant.Role.*;

import com.api.constant.Role;
import com.api.pojo.UserCredentials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {
	private static Map<Role, String> tokencache = new ConcurrentHashMap<Role, String>();
	private AuthTokenProvider() {
		
	}

	public static String getToken(Role role) {
		
		if(tokencache.containsKey(role)) {
			return tokencache.get(role);
		}
		
		
		
		
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
	
	
	tokencache.put(role, token);
	
	return token;
	
	}

}
