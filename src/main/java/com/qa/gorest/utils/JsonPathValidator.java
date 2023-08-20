package com.qa.gorest.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.qa.gorest.frameoworkException.APIframeworkException;

import io.restassured.response.Response;

public class JsonPathValidator {
	
	private String getJsonResponseAsString(Response response) {
		return response.getBody().asString();
	}
	
	public <T> T read(Response response ,String jsonPath ) {
		String jsonresponse = getJsonResponseAsString(response);
		try {
		return JsonPath.read(jsonresponse,jsonPath);
		}
		catch(PathNotFoundException e) {
			e.printStackTrace();
			throw new APIframeworkException(jsonPath + "is not found");
			
		}
		
	}
	
	public <T> List<T> readList(Response response ,String jsonPath ) {
		String jsonresponse = getJsonResponseAsString(response);
		try {
		return JsonPath.read(jsonresponse,jsonPath);
		}
		catch(PathNotFoundException e) {
			e.printStackTrace();
			throw new APIframeworkException(jsonPath + "is not found");
			
		}
	}
		
		public <T> List<Map<String,T>> readListofMaps(Response response ,String jsonPath ) {
			String jsonresponse = getJsonResponseAsString(response);
			try {
			return JsonPath.read(jsonresponse,jsonPath);
			}
			catch(PathNotFoundException e) {
				e.printStackTrace();
				throw new APIframeworkException(jsonPath + "is not found");
				
			}
		
	}

}
