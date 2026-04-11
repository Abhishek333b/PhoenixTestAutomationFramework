package com.api.tests;
import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.pojo.request.model.CreateJobPayload;
import com.api.utils.FakerDataGenerator;

public class CreateJobAPITestwithFakeData {
 private CreateJobPayload createJobPayload;
 private final static String COUNTRY= "India";
 @BeforeMethod(description = "creating createjob api request payload")
 public void setup() {
	 
			
			
			createJobPayload = FakerDataGenerator.generateFakeCreateJobData();
		
	}
	
	@Test(description = "Verifying if create api is giving correct response ",groups={"api","regression","smoke"})
	public void createJobAPITest() {
		given()
		.spec(requestSpecWithAuth(Role.FD,createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(responseSpec_OK())
		.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message",equalTo("Job created successfully. "))
		.body("data.mst_service_location_id",equalTo(1))
		.body("data.job_number",startsWith("JOB_"));
	}
	
	
	
}
