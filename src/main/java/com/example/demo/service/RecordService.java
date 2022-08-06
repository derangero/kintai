package com.example.demo.service;

import java.time.LocalDateTime;

public interface RecordService {

	String recordByStart(String employeeCode, LocalDateTime recordTime);
	String recordByEnd(String employeeCode, LocalDateTime recordTime);
}
