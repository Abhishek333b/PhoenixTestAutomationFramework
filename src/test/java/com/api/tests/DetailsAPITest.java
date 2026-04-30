package com.api.tests;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.api.constant.Role.*;
import com.api.pojo.request.model.Detail;
import com.api.services.DashBoardService;
import static com.api.utils.SpecUtil.*;

public class DetailsAPITest {
	
	private DashBoardService dashBoardService;
	private Detail detailPayload;
	
	@BeforeMethod(description="instantiating the dashboard service and creating detail payload")
	public void setup() {
		dashBoardService = new DashBoardService();
		detailPayload = new Detail("created_today");
		
	}
	
	@Test(description="verify if details API is working properly",groups= {"api","smoke"})
	public void detailAPITest() {
		dashBoardService.details(FD, detailPayload)
		.then()
		.spec(responseSpec_OK())
		.body("message",equalTo("Success"));
	}

}
