package com.qa.gorest.utils;

public class StringUtil {

	
	public static String GetRandomEmail() {
		return "api" +System.currentTimeMillis() + "@api.com"; 
	}
}
