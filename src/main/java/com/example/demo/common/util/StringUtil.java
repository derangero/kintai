package com.example.demo.common.util;

public class StringUtil {
	
	private StringUtil() {}

	public static String joinBySemicolon(String first, String second) {
    	if (first != null && second != null) {
    		return first + ":" + second;
    	}
    	return null;
    }

	public static String convertFirstToUpperCase(String target) {
		if (target != null) {
			String firstLtr = target.substring(0, 1);
			String restLtrs = target.substring(1, target.length());
			firstLtr = firstLtr.toUpperCase();
			return firstLtr + restLtrs;
		}
		return null;
	}
}
