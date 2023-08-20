package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.gorest.pojo.*;
import com.qa.gorest.utils.ExcelUtil;
import com.qa.gorest.utils.StringUtil;
import com.qa.gorest.base.Basetest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constant.APIConstants;
import com.qa.gorest.constant.APIHttpStatus;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class CreateUserTest extends Basetest {

	@BeforeMethod
	public void getUserSetup() {
		restClient = new RestClient(prop, baseURI);
	}

	@DataProvider
	public Object[][] getUserTestData() {
		return new Object[][] { { "subodh", "male", "active" }, { "Seema", "female", "inactive" },
				{ "Madhuri", "female", "active" } };
	}

	@DataProvider
	public Object[][] getUserTestSheetData() {
		return ExcelUtil.getTestData(APIConstants.GOREST_USER_SHEET_NAME);
	}

	@Test(dataProvider = "getUserTestSheetData")
	public void getAllUserstest(String name, String gender, String status) {

		// 1. post
		user user = new user(name, StringUtil.GetRandomEmail(), gender, status);

		// RestClient restClient= new RestClient(prop, baseURI));
		Integer userId = restClient.post(GOREST_ENDPOINT, "json", user, true, true).then().log().all().assertThat()
				.statusCode(APIHttpStatus.CREATED_201.getCode()).extract().path("id");
		System.out.println("User id " + userId);

		// 2.get
		RestClient restClientGet = new RestClient(prop, baseURI);
		restClientGet.get(GOREST_ENDPOINT + "/" + userId, true, true).then().log().all().assertThat()
				.statusCode(APIHttpStatus.OK_200.getCode()).assertThat().body("id", equalTo(userId));

	}

	
}