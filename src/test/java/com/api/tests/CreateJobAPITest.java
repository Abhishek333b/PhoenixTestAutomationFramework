package com.api.tests;
import static com.api.utils.DateTimeUtil.getTimeWithDaysAgo;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.Role;
import com.api.constant.Service_Location;
import com.api.constant.Warrenty_Status;
//import com.api.pojo.CreateJobPayload;
import com.api.pojo.request.model.CreateJobPayload;
import com.api.pojo.request.model.Customer;
import com.api.pojo.request.model.CustomerAddress;
import com.api.pojo.request.model.CustomerProduct;
//import com.api.pojo.Problems;
import com.api.pojo.request.model.Problems;
import com.api.services.JobService;




public class CreateJobAPITest {
	
private JobService jobService;	
 private CreateJobPayload createJobPayload;
	
 @BeforeMethod(description = "creating createjob api request payload and instantiating the job service")
 public void setup() {
         Customer customer = new Customer("Abhi", "Bhagat", "7676767786", "", "abhi@gmail.com", "");
		
		CustomerAddress customerAddress = new CustomerAddress("A 12", "Abg c", "stghj t", "erhj t", "rtghj ee", "411056", "india", "maharashtra");
		CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10), "29904753563333", "29904753563333", "29904753563333", getTimeWithDaysAgo(10), 
			Product.NEXUS_2.getCode(),Model.NEXUS_2_BLUE.getCode());
		Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "battery issue");
		
		List<Problems> problemList = new ArrayList<>();
		problemList.add(problems);
		 createJobPayload = new CreateJobPayload(Service_Location.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), Warrenty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(),customer, customerAddress, customerProduct, problemList);
		 jobService = new JobService();
		
	}
	
	@Test(description = "Verifying if create api is giving correct response ",groups={"api","regression","smoke"})
	public void createJobAPITest() {
		jobService.createJob(Role.FD, createJobPayload)
		.then()
		.spec(responseSpec_OK())
		.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message",equalTo("Job created successfully. "))
		.body("data.mst_service_location_id",equalTo(1))
		.body("data.job_number",startsWith("JOB_"));
	}
	
	
	
}
