package com.example.demo.service;

import java.time.LocalDate;

import org.springframework.ui.Model;

import com.example.demo.dto.RecordDto;
import com.example.demo.serviceImple.UserDetailsImpl;

public interface ModelService {
	
	void setModelFromIndex(Model model, UserDetailsImpl userDetails, LocalDate recordTime);
	
	RecordDto getRecordDto(String employeeCode, LocalDate recordeDate);
}