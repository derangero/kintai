package com.example.demo.serviceImple;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.common.util.Util;
import com.example.demo.dao.master.MEmployeeDao;
import com.example.demo.dao.master.MFiscalCalendarDao;
import com.example.demo.dao.transaction.TAttendanceRecordDao;
import com.example.demo.dto.RecordDto;
import com.example.demo.entity.master.MEmployee;
import com.example.demo.entity.master.MFiscalCalendar;
import com.example.demo.entity.transaction.TAttendanceRecord;
import com.example.demo.service.AttendanceRecordListService;

@Service
public class AttendanceRecordListServiceImple implements AttendanceRecordListService {

	@Autowired
	MFiscalCalendarDao fiscalCalendarDao;
	@Autowired
	MEmployeeDao employeeDao;
	@Autowired
	TAttendanceRecordDao attendanceRecordDao;

	@Override
	public RecordDto setRecordDtos(Model model, UserDetailsImpl userDetails, LocalDate fromDate, LocalDate toDate)
			throws Exception {
		MEmployee employee = employeeDao.findByCode(userDetails.getUsername()).get();
		List<MFiscalCalendar> calendars = fiscalCalendarDao.listByFromAndTo(fromDate, toDate);
		List<TAttendanceRecord> attendanceRecords = attendanceRecordDao.listByEmployeeAndRecordRange(
				employee.getId(), fromDate, toDate);
		Map<LocalDate, TAttendanceRecord> attendanceRecordMapByRecordDate = attendanceRecords.stream().collect(
				Collectors.toMap(x -> x.getStartRecordDateTime().toLocalDate(), x -> x));
		List<RecordDto> recordDtos = new ArrayList<>();

		for (MFiscalCalendar fiscalCalendar : calendars) {
			LocalDate calendarDate = fiscalCalendar.getCalendarDate();
			RecordDto dto = new RecordDto();
			dto.setRecordDate(calendarDate);
			TAttendanceRecord attendanceRecord = attendanceRecordMapByRecordDate.get(calendarDate);
			if (attendanceRecord != null) {
				LocalDateTime startRecordDateTime = attendanceRecord.getStartRecordDateTime();
				LocalDateTime endRecordDateTime = attendanceRecord.getEndRecordDateTime();
				
				dto.setRecordDateTimeByStart(startRecordDateTime);
				dto.setRecordDateTimeByEnd(endRecordDateTime);
				
				if (endRecordDateTime != null) {
					long hour = ChronoUnit.HOURS.between(startRecordDateTime, endRecordDateTime);
					long minutes = ChronoUnit.MINUTES.between(startRecordDateTime, endRecordDateTime);
					dto.setDisplayWorkingHours(Util.zeroPadding2(hour) + ":" + Util.zeroPadding2(minutes));
				}

				dto.setStampApproval(attendanceRecord.getStampApproval());
				dto.setDisplayStampApproval(attendanceRecord.getStampApproval() ? "済" : "未");
			}
			recordDtos.add(dto);
		}
		
		model.addAttribute("recordDtos", recordDtos);
		
		return recordDtos != null ? recordDtos.get(0) : null;
	}
}