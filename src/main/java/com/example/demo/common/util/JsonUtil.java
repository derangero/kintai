package com.example.demo.common.util;

import com.example.demo.dto.DtoBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	
	private JsonUtil() {}

	public static <M extends DtoBase> String getJson(M dto){
        String retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            retVal = objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            System.err.println(e);
        }
        return retVal;
    }
}