package com.qa.gorest.tests;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.Basetest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constant.APIHttpStatus;
import com.qa.gorest.pojo.user;
import com.qa.gorest.utils.StringUtil;

public class APIScemaValidationTest  extends Basetest {

	@BeforeMethod
	public void getUserSetup() {
		restClient = new RestClient(prop, baseURI);
	}
	
	@Test()
	public void createUserAPISchemaTest() {

		// 1. post
		user user = new user("tom", StringUtil.GetRandomEmail(), "male", "active");
		restClient.post(GOREST_ENDPOINT, "json", user, true, true).then().log().all().assertThat()
				.statusCode(APIHttpStatus.CREATED_201.getCode())
				.body(matchesJsonSchemaInClasspath("getUserSchema.json"));

	}

}
