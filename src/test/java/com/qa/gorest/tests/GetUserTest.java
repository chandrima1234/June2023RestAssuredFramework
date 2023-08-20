package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.Basetest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constant.APIHttpStatus;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

public class GetUserTest extends Basetest{
	
	@BeforeMethod
	public void getUserSetup() {
		restClient = new RestClient(prop,baseURI);
	}
	
	@Test
	public void getAllUsersTest() {
		restClient.get(GOREST_ENDPOINT, true,false)
		     .then().log().all()
		       .assertThat()
		         .statusCode(APIHttpStatus.OK_200.getCode());
	}
	@Test(enabled=false)
	public void getUserTest() {
		//restClient= new RestClient(prop, baseURI);
		restClient.get(GOREST_ENDPOINT+"/4165671",true, true)
		     .then().log().all()
		       .assertThat()
		         .statusCode(APIHttpStatus.OK_200.getCode())
		         .and().body("id", equalTo(4165671));
		         		
	}
	
	
@Test
	public void getUserQueryParamsTest() {
		//restClient= new RestClient(prop, baseURI);
		Map<String,String> queryParams = new HashMap<String,String>();
		queryParams.put("name", "Chandrima");
		queryParams.put("status", "active");
		restClient.get(GOREST_ENDPOINT, null, queryParams, true, true)
		
		     .then().log().all()
		       .assertThat()
		         .statusCode(APIHttpStatus.OK_200.getCode());
		        
		         		
	}
	
	
	
	
	
	 

}
