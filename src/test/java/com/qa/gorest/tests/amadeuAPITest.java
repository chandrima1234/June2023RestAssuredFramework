package com.qa.gorest.tests;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.object.HasEqualValues;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.gorest.base.Basetest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constant.APIHttpStatus;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class amadeuAPITest extends Basetest {
	
	private String accessToken;
	
	@Parameters({"baseURI", "grantType","clientid","clientsecret"})
	@BeforeMethod
	public void flightApiSteup(String baseURI, String grantType, String clientid, String clientsecret ) {
		restClient = new RestClient(prop, baseURI);
		accessToken =restClient.getAccessToken(AMADEUS_ENDPOINT, grantType, clientid, clientsecret);
	}
	
	
	@Test
	  public void getFlightInfoTest() {
		RestClient restClientFlight = new RestClient(prop, baseURI);
		
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("origin", "PAR");
		queryParams.put("maxPrice", 200);
		Map<String,String> headerMap= new HashMap<String,String>();
		headerMap.put("authorization", "Bearer " + accessToken);
		Response flightDataresponse = restClientFlight.get(AMADEUS_FLIGHTBOOKING_ENDPOINT, queryParams, headerMap, true, true)
					 .then().log().all()
			         .assertThat()
			           .statusCode(APIHttpStatus.OK_200.getCode())
			             .and()
			               .extract().response();
			JsonPath js = flightDataresponse.jsonPath();
			String type = js.get("data[0].type");
			System.out.println(type);
	               

	}


}
