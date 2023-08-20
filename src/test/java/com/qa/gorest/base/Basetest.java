package com.qa.gorest.base;

import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.gorest.client.RestClient;
import com.qa.gorest.configuration.ConfigurationManagement;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class Basetest {
	//service URLs:
	public static final String GOREST_ENDPOINT ="/public/v2/users";
	public static final String REQRES_ENDPOINT ="/api/users";
	public static final String CIRCUIT_ENDPOINT ="/api/f1/";
	public static final String AMADEUS_ENDPOINT ="/v1/security/oauth2/token";
	public static final String AMADEUS_FLIGHTBOOKING_ENDPOINT ="/v1/shopping/flight-destinations";
	
	
	protected ConfigurationManagement config;
	protected Properties prop;
	protected  RestClient restClient;
	protected String baseURI;
	
	@Parameters({"baseURI"})
	@BeforeTest
	public void setUp(String baseURI) {
		
		RestAssured.filters(new AllureRestAssured());
		config = new ConfigurationManagement();
		prop= config.initProp();
		//String baseURI = prop.getProperty("baseURI");
		this.baseURI =baseURI;
		
	}

}
