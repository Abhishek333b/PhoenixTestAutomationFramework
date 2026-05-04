package com.api.tests;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.api.constant.Role.*;
import com.api.pojo.request.model.Detail;
import com.api.services.DashBoardService;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import static com.api.utils.SpecUtil.*;
@Listeners(com.listeners.APITestListener.class)
@Epic("Job Management")
@Feature("Job Details")
public class DetailsAPITest {
	
	private DashBoardService dashBoardService;
	private Detail detailPayload;
	
	@BeforeMethod(description="instantiating the dashboard service and creating detail payload")
	public void setup() {
		dashBoardService = new DashBoardService();
		detailPayload = new Detail("created_today");
		
	}
	
	@Story("Job Details is shown correctly for FD")
	@Description("verify if details API is working properly")
	@Severity(SeverityLevel.CRITICAL)
	
	@Test(description="verify if details API is working properly",groups= {"api","smoke"})
	public void detailAPITest() {
		dashBoardService.details(FD, detailPayload)
		.then()
		.spec(responseSpec_OK())
		.body("message",equalTo("Success"));
	}

}
