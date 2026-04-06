package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import java.time.Instant;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import static com.api.utils.DateTimeUtil.*;
import com.api.utils.SpecUtil;

public class CreateJobAPITest {
	
	
	@Test
	public void createJobAPITest() {
		System.out.println(Instant.now().minus(10,ChronoUnit.DAYS));
		System.out.println("######################");
		Customer customer = new Customer("Abhi", "Bhagat", "7676767786", "", "abhi@gmail.com", "");
		
		CustomerAddress customerAddress = new CustomerAddress("A 12", "Abg c", "stghj t", "erhj t", "rtghj ee", "411056", "india", "maharashtra");
		CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10), "59904753563802", "59904753563802", "59904753563802", getTimeWithDaysAgo(10), 1, 1);
		Problems problems = new Problems(1, "battery issue");
		
		List<Problems> problemList = new ArrayList<>();
		problemList.add(problems);
		
		
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1,customer, customerAddress, customerProduct, problemList);
		given()
		
		
		.spec(SpecUtil.requestSpecWithAuth(Role.FD,createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message",equalTo("Job created successfully. "))
		.body("data.mst_service_location_id",equalTo(1))
		.body("data.job_number",startsWith("JOB_"));
	}
	
	
	
}
