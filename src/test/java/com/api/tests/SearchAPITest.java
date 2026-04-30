package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.services.JobService;
import com.api.utils.SpecUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data 
class Search {
    private String job_number;
}
public class SearchAPITest {
	private JobService jobService;
	private static final String JOB_NUMBER="JOB_259966";
	private Search searchPayload;
	
	@BeforeMethod(description="Instantiating the job service and creating the search payload ")
	public void setup() {
		
		jobService = new JobService();
		searchPayload = new Search(JOB_NUMBER);
	}
	
	@Test(description="verify if the search api is working properly",groups = {"e2e","smoke"})
	public void searchAPITest(){
		jobService.search(Role.FD,searchPayload)
		.then()
		.spec(SpecUtil.responseSpec_OK()).body("message",Matchers.equalTo("Success"));
		
		
	}
}
