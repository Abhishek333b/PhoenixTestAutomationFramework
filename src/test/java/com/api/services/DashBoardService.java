package com.api.services;

import static com.api.constant.Role.FD;
import static com.api.utils.SpecUtil.*;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.Role;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class DashBoardService {

	private static final String COUNT_ENDPOINT = "/dashboard/count";

	private static final String DETAIL_ENDPOINT = "/dashboard/details";
	
	private static final Logger LOGGER = LogManager.getLogger(DashBoardService.class);
	
	@Step("Making count API request for role")
	public Response count(Role role) {
		LOGGER.info("Making request to the {} for role {} ",COUNT_ENDPOINT,role);
		return given().spec(requestSpecWithAuth(FD)).when().get(COUNT_ENDPOINT);
	}
	
	@Step("Making count API request without Auth token")
	public Response countWithNoAuth() {
		LOGGER.info("Making request to the {} with no Auth Token  ",COUNT_ENDPOINT);
		return given()
				.spec(requestSpec())
				.when().get(COUNT_ENDPOINT);
	}
	
	@Step("Making Details API request")
	public Response details(Role role,Object payload) {
		LOGGER.info("Making request to the {} with role{} and the payload {} ",DETAIL_ENDPOINT,role,payload);
		return given().spec(requestSpecWithAuth(role))
				.body(payload)
				.when().post(DETAIL_ENDPOINT);
	}
}
