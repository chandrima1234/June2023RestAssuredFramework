package com.qa.gorest.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.Basetest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constant.APIHttpStatus;
import com.qa.gorest.utils.JsonPathValidator;

import io.restassured.response.Response;

public class CircuitTest extends Basetest{
	
	@BeforeMethod
	public void getUserSetup() {
		restClient = new RestClient(prop,baseURI);
	}
	@Test
	public void getCircuitTest() {
		Response circuitresponse = restClient.get(CIRCUIT_ENDPOINT+ "2017/circuits.json",false, false);
		circuitresponse
		.then()
		.assertThat()
		.statusCode(APIHttpStatus.OK_200.getCode());
		
		JsonPathValidator js = new JsonPathValidator();
		List<String> countryList = js.readList(circuitresponse, "$.MRData.CircuitTable.Circuits[?(@.circuitId == 'shanghai')].Location.country");
		System.out.println(countryList);
		Assert.assertTrue(countryList.contains("China"));
		//     .then().log().all()
		//       .assertThat()
		//         .statusCode(APIHttpStatus.OK_200.getCode());
	}

}
