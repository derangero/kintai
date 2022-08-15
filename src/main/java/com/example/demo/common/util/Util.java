package com.example.demo.common.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Util {

	private Util() {}

	public static LocalDateTime getNowLocalDateTime() {
    	Instant instant = new Date().toInstant();
    	LocalDateTime recordTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		return recordTime;
	}

	public static LocalDate getNowLocalDate() {
		return getNowLocalDateTime().toLocalDate();
	}

	public static String formatHHmmss(LocalDateTime time) {
		if (time != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm:ss");
			return time.format(formatter);
		}
		return null;
	}
	
	public static String zeroPadding2(Long number) {
		return zeroPadding(number, "%02d");
	}

	public static String zeroPadding4(Long number) {
		return zeroPadding(number, "%04d");
	}

	public static String zeroPadding(Long number, String formatter) {
		if (number != null) {
			return String.format(formatter, number);
		}
		return null;
	}
}
