package com.example.samplecrud.etc.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringUtils {
	private static ObjectMapper objectMapper = new ObjectMapper();
	public static String objectToJsonString(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			return null;
		}
	}
}
