package com.example.demo.serviceImple;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.common.util.Util;
import com.example.demo.dao.master.MEmployeeDao;
import com.example.demo.dao.transaction.TAttendanceRecordDao;
import com.example.demo.dto.RecordDto;
import com.example.demo.entity.master.MEmployee;
import com.example.demo.entity.transaction.TAttendanceRecord;
import com.example.demo.service.ModelService;

@Service
public class ModelServiceImpl implements ModelService {

	@Autowired
	MEmployeeDao employeeDao;
	@Autowired
	TAttendanceRecordDao attendanceRecordDao;
	
	@Override
	public void setModelFromIndex(Model model, UserDetailsImpl userDetails, LocalDate recordTime) {

		model.addAttribute("employeeCode", userDetails.getUsername());
    	model.addAttribute("employeeName", userDetails.getEmployeeName());
    	model.addAttribute("isRecordedByStart", false);
    	model.addAttribute("isRecordedByEnd", false);

    	RecordDto recordDto = getRecordDto(userDetails.getUsername(), recordTime);
		if (recordDto != null) {
			model.addAttribute("isRecordedByStart", recordDto.getRecordDateTimeByStart() != null);
			model.addAttribute("isRecordedByEnd", recordDto.getRecordDateTimeByEnd() != null);
			if (recordDto.getRecordDateTimeByEnd() != null) {
				model.addAttribute("recordedTime", Util.formatHHmmss(recordDto.getRecordDateTimeByEnd()));
			}
		}
	}
	
	@Override
	public RecordDto getRecordDto(String employeeCode, LocalDate recordeDate) {
		MEmployee employee = employeeDao.findByCode(employeeCode)
				.orElseThrow(() -> new UsernameNotFoundException("user not found"));
		TAttendanceRecord attendanceRecord = attendanceRecordDao.findByEmployeeAndStartRecord(employee.getId(),
				recordeDate);
		if (attendanceRecord != null) {
			LocalDateTime startRecordDateTime = attendanceRecord.getStartRecordDateTime();
			LocalDateTime endRecordDateTime = attendanceRecord.getEndRecordDateTime();
			
			RecordDto recordDto = new RecordDto();
			if (startRecordDateTime != null) {
				recordDto.setRecordDateTimeByStart(startRecordDateTime);
			}
			if (endRecordDateTime != null) {
				recordDto.setRecordDateTimeByEnd(endRecordDateTime);
			}
			return recordDto;
		}

		return null;
	}
}
