package com.api.tests;

import static com.api.constant.Role.FD;
import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static com.api.utils.SpecUtil.responseSpec_TEXT;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.services.DashBoardService;
import com.api.services.UserService;

public class CountAPITest {
	
	private DashBoardService dashBoardService;
	@BeforeMethod(description = "Setting up the dashBoardService instance")
	public void setup() {
		dashBoardService = new DashBoardService();
	}
	
	
	

	@Test(description = "Verifying if Count api is giving correct response ",groups={"api","regression","smoke"})
	public void verifyCountAPIResponse() {
		dashBoardService.count(FD)
		.then()
		.spec(responseSpec_OK())
		
		.body("data",notNullValue())
		.body("data.size()",equalTo(3))
		.body("data.count",everyItem(greaterThanOrEqualTo(0)))
		.body("data.label",everyItem(not(blankOrNullString())))
		.body("data.key", containsInAnyOrder("pending_fst_assignment","created_today","pending_for_delivery"))
		.body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json"));
		}
	
	@Test(description = "Verifying if Count api is giving correct status code for invalid token ",groups={"api","negative","regression","smoke"})
	public void countAPITest_MissingAuthToken() {
		dashBoardService.countWithNoAuth()
		.then()
		.spec(responseSpec_TEXT(401));
		
		
	}

}
