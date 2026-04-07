package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import static com.api.utils.SpecUtil.*;

import static com.api.utils.ConfigManager.*;



import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LoginAPITest {
	private UserCredentials userCredentials;
    
	@BeforeMethod(description="create the user credentials for the login API")
	public void setup() {
		userCredentials = new UserCredentials("iamfd","password");
	}
	
 
	
	@Test(description = "Verifying if login api is working for FD user",groups={"api","regression","smoke"})
	public void loginAPITest() throws IOException {
		
		given()
		.spec(requestSpec(userCredentials))
		.when()
		.post("login")
		.then()
		.spec(responseSpec_OK())
		.body("message", equalTo("Success"))
		.and()
		.body(matchesJsonSchemaInClasspath("response-schema/LoginResponSeschema.json"));
		
	}
	
	
}
