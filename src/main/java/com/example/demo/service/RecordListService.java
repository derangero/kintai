package com.example.demo.service;

import java.time.LocalDate;

import org.springframework.ui.Model;

import com.example.demo.dto.RecordDto;
import com.example.demo.serviceImple.UserDetailsImpl;

public interface RecordListService {

	RecordDto setRecordDtos(Model model, UserDetailsImpl userDetails, LocalDate fromDate, LocalDate toDate)
			throws Exception;
}
