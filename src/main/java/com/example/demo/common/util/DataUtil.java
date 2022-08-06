package com.example.demo.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DataUtil {

    public static LocalDateTime toLocalDateTime(LocalDate date) {
    	if (date.atStartOfDay() != null) {
    		return date.atStartOfDay();
    	}
    	return null;
    }
}
