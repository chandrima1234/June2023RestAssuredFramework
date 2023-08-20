package com.qa.gorest.client;

import java.util.Map;
import java.util.Properties;

import com.qa.gorest.frameoworkException.APIframeworkException;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

	//private static final String BASE_URI = "https://gorest.co.in";
	//private static final String BEARER_TOKEN = "e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6";
	private static RequestSpecBuilder specbuilder;
	
	 private Properties prop;
	 private String baseURI;
	 private boolean isAuthHeaderAdded = false;
 
 
 
	public RestClient(Properties prop,String baseURI) {
		specbuilder = new RequestSpecBuilder();
		this.prop=prop;
		this.baseURI=baseURI;
	}
	public void addAuthorizationHeader() {
		if(!isAuthHeaderAdded) {
			specbuilder.addHeader("Authorization", "Bearer "+ prop.getProperty("tokenId"));
			isAuthHeaderAdded = true;
		}
	}
	
	
	private void SetRequestContentType(String contentType) {
		switch (contentType.toLowerCase()) {
		case "json":
			  specbuilder.setContentType(ContentType.JSON);
			break;
		case "xml":
			  specbuilder.setContentType(ContentType.XML);
			break;	
		case "text":
			  specbuilder.setContentType(ContentType.TEXT);
			break;		
		case "multipart":
			  specbuilder.setContentType(ContentType.MULTIPART);
			break;
		default:
			System.out.println("Please pass the right content type");
			throw new APIframeworkException("INVALIDCONTENTTYPE");
		}
	}
	private RequestSpecification createRequestSpec(boolean includeAuth) {
		specbuilder.setBaseUri(baseURI);
		if(includeAuth) {
		addAuthorizationHeader();
		}
		return specbuilder.build();
		
	}
	
	private RequestSpecification createRequestSpec(Map<String,String> headermap,boolean includeAuth) {
		specbuilder.setBaseUri(baseURI);
		if(includeAuth) {
			addAuthorizationHeader();
			}
		if(headermap!=null) {
			specbuilder.addHeaders(headermap);
		}
		return specbuilder.build();
		
	}
	
	private RequestSpecification createRequestSpec(Map<String, String> headersMap, Map<String,Object> queryParams, boolean includeAuth) {
		specbuilder.setBaseUri(baseURI);
		if(includeAuth) {addAuthorizationHeader();}
		if(headersMap!=null) {
			specbuilder.addHeaders(headersMap);
		}
		if(queryParams!=null) {
			specbuilder.addQueryParams(queryParams);
		}
		return specbuilder.build();
	}
	
	private RequestSpecification createRequestSpec(Object requestBody , String contentType,boolean includeAuth) {
		specbuilder.setBaseUri(baseURI);
		if(includeAuth) {
			addAuthorizationHeader();
			}
		SetRequestContentType(contentType);
		if(requestBody!=null) {
			specbuilder.setBody(requestBody);
		}
		return specbuilder.build();
		
	}
	
	public RequestSpecification createRequestSpec(Object requestBody , String contentType,Map<String,String>headerMap,boolean includeAuth) {
		specbuilder.setBaseUri(baseURI);
		if(includeAuth) {
			addAuthorizationHeader();
			}
		SetRequestContentType(contentType);
		if(headerMap!=null) {
			specbuilder.addHeaders(headerMap);
		}
		if(requestBody!=null) {
			specbuilder.setBody(requestBody);
		}
		return specbuilder.build();
		
	}
	
	//Http Methods utils
	
	public Response get(String serviceURl,boolean includeAuth, boolean log) {
		
		if(log) {
		  return RestAssured.given(createRequestSpec(includeAuth)).log().all()
		    .when()
		    .get(serviceURl);
	   }
		return RestAssured.given(createRequestSpec(includeAuth)).when().get(serviceURl);
	
	}	
	
public Response get(String serviceURl, Map<String,String> headerMap,boolean includeAuth,boolean log) {
		
		if(log) {
		  return RestAssured.given(createRequestSpec(headerMap,includeAuth)).log().all()
		    .when()
		    .get(serviceURl);
	   }
		return RestAssured.given(createRequestSpec(headerMap,includeAuth)).when().get(serviceURl);
	
	}

public Response get(String serviceUrl, Map<String, Object> queryParams,  Map<String, String> headersMap, boolean includeAuth, boolean log) {
	
	if(log) {
		return RestAssured.given(createRequestSpec(headersMap, queryParams, includeAuth)).log().all()
		.when()
			.get(serviceUrl);
	}
	return RestAssured.given(createRequestSpec(headersMap, queryParams, includeAuth)).when().get(serviceUrl);
}


public Response post(String serviceUrl, String contentType,Object requestBody, Map<String,String> headerMap,boolean includeAuth, boolean log) {
	if(log) {
		return RestAssured.given(createRequestSpec(requestBody, contentType, headerMap,includeAuth)).log().all()
		   .when()
		   .post(serviceUrl);
	}
	return RestAssured.given(createRequestSpec(requestBody, contentType, headerMap,includeAuth))
			   .when()
			   .post(serviceUrl);	
}

public Response post(String serviceUrl, String contentType,Object requestBody,boolean includeAuth, boolean log) {
	if(log) {
		return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth)).log().all()
		   .when()
		   .post(serviceUrl);
	}
	return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth))
			   .when()
			   .post(serviceUrl);	
}

public Response put(String serviceUrl, String contentType,Object requestBody, Map<String,String> headerMap, boolean includeAuth,boolean log) {
	if(log) {
		return RestAssured.given(createRequestSpec(requestBody, contentType, headerMap,includeAuth)).log().all()
		   .when()
		   .put(serviceUrl);
	}
	return RestAssured.given(createRequestSpec(requestBody, contentType, headerMap,includeAuth))
			   .when()
			   .put(serviceUrl);	
}
public Response put(String serviceUrl, String contentType,Object requestBody, boolean log,boolean includeAuth) {
	if(log) {
		return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth)).log().all()
		   .when()
		   .put(serviceUrl);
	}
	return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth))
			   .when()
			   .put(serviceUrl);	
}



public Response patch(String serviceUrl, String contentType,Object requestBody, Map<String,String> headerMap, boolean log,boolean includeAuth) {
	if(log) {
		return RestAssured.given(createRequestSpec(requestBody, contentType, headerMap,includeAuth)).log().all()
		   .when()
		   .patch(serviceUrl);
	}
	return RestAssured.given(createRequestSpec(requestBody, contentType, headerMap,includeAuth))
			   .when()
			   .put(serviceUrl);	
}
public Response patch(String serviceUrl, String contentType,Object requestBody, boolean log,boolean includeAuth) {
	if(log) {
		return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth)).log().all()
		   .when()
		   .patch(serviceUrl);
	}
	return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth))
			   .when()
			   .patch(serviceUrl);	
}

public Response delete(String serviceUrl, boolean log,boolean includeAuth) {
	if(log) {
		return RestAssured.given(createRequestSpec(includeAuth)).log().all()
		   .when()
		   .delete(serviceUrl);
	}
	return RestAssured.given(createRequestSpec(includeAuth))
			   .when()
			   .delete(serviceUrl);	
     }

public String getAccessToken(String serviceURL, String grantType, String clientId, String clientSecret  ) {
	//1. POST - get the access token
			RestAssured.baseURI = "https://test.api.amadeus.com";
			
			String accessToken = RestAssured.given().log().all()
				.contentType(ContentType.URLENC)
				.formParam("grant_type", grantType)
				.formParam("client_id", clientId)
				.formParam("client_secret", clientSecret)
			.when()
				.post(serviceURL)
			.then().log().all()
				.assertThat()
					.statusCode(200)
					.extract().path("access_token");
				
			System.out.println("access token: " + accessToken);
		return 	accessToken;
			
}



}



























	

