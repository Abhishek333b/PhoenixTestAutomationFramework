package com.api.tests.datadriven;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Role;
//import com.api.pojo.CreateJobPayload;
import com.api.pojo.request.model.CreateJobPayload;
import com.api.services.JobService;

public class CreateJobAPIJsonDataDrivenTest {
	
	private JobService jobService;
	@BeforeMethod(description = " instantiating the job service")
	 public void setup() {
	         
			 jobService = new JobService();
			
		}
	 
 
@Test(description = "Verifying if create api is giving correct response ",groups={"api","regression","datadriven","faker"},
			dataProviderClass = com.dataproviders.DataProviderUtils.class,
			dataProvider = "CreateJobAPIJsonDataProvider"
			)
	public void createJobAPITest(CreateJobPayload createJobPayload) {
	jobService.createJob(Role.FD, createJobPayload)
		.then()
		.spec(responseSpec_OK())
		.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message",equalTo("Job created successfully. "))
		.body("data.mst_service_location_id",equalTo(1))
		.body("data.job_number",startsWith("JOB_"));
	}
	
	
	
}
