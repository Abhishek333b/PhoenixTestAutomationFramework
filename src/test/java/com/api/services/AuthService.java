package com.api.services;

import static com.api.utils.SpecUtil.requestSpec;
import com.api.pojo.request.model.UserCredentials;
import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public class AuthService {

	private static final String LOGIN_ENDPOINT="/login";
	public Response login(Object userCredentials) {
		
		Response response=	given()
		.spec(requestSpec(userCredentials))
		.when()
		.post(LOGIN_ENDPOINT);
		
		return response;
	}
}
