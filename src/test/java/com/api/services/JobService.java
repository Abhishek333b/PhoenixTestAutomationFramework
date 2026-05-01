package com.api.services;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.Role;
//import com.api.pojo.CreateJobPayload;
import com.api.pojo.request.model.CreateJobPayload;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public class JobService {
	private static final String CREATE_JOB_ENDPOINT= "/job/create";
	
	private static final String SEARCH_ENDPOINT="/job/search";
	private static final Logger LOGGER = LogManager.getLogger(JobService.class);
	
	public Response createJob(Role role,CreateJobPayload creatJobPayload) {
		LOGGER.info("Making request to {} with role {} and payload {}",CREATE_JOB_ENDPOINT,role, creatJobPayload);
	return	given()
		.spec(requestSpecWithAuth(role,creatJobPayload))
		.when()
		.post(CREATE_JOB_ENDPOINT);
	}

	public Response search(Role role,Object payload) {
		LOGGER.info("Making request to {} with role {} and payload {}",SEARCH_ENDPOINT,role,payload);
		
		return
				given().spec(SpecUtil.requestSpecWithAuth(role))
				.body(payload).post(SEARCH_ENDPOINT);
		
	}
	
	
}
