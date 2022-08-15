package com.example.demo.serviceImple;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.master.MEmployeeDao;
import com.example.demo.dao.transaction.TAttendanceRecordDao;
import com.example.demo.entity.master.MEmployee;
import com.example.demo.entity.transaction.TAttendanceRecord;
import com.example.demo.service.RecordService;

@Service
public class RecordServiceImple implements RecordService {

	@Autowired
	MEmployeeDao employeeDao;
	@Autowired
	TAttendanceRecordDao attendanceRecordDao;

	@Override
	public String recordByStart(String employeeCode, LocalDateTime recordTime) {
		MEmployee employee = employeeDao.findByCode(employeeCode)
				.orElseThrow(() -> new UsernameNotFoundException("user not found"));
		TAttendanceRecord attendanceRecord = attendanceRecordDao.findByEmployeeAndStartRecord(employee.getId(),
				recordTime.toLocalDate());
		String errorMsg = validateByStart(attendanceRecord);
		if (errorMsg == null) {
			record(employee, recordTime, true);
		}

		return errorMsg;
	}

	private String validateByStart(TAttendanceRecord attendanceRecord) {
		String errorMsg = null;
		if (attendanceRecord != null) {
			errorMsg = "既に打刻されています";
		}

		return errorMsg;
	}

	@Override
	public String recordByEnd(String employeeCode, LocalDateTime recordTime) {
		MEmployee employee = employeeDao.findByCode(employeeCode)
				.orElseThrow(() -> new UsernameNotFoundException("user not found"));
		TAttendanceRecord attendanceRecord = attendanceRecordDao.findByEmployeeAndStartRecord(employee.getId(),
				recordTime.toLocalDate());
		String errorMsg = validateByEnd(attendanceRecord);
		if (errorMsg == null) {
			record(employee, recordTime, false);
		}

		return errorMsg;
	}
	
	private String validateByEnd(TAttendanceRecord attendanceRecord) {
		String errorMsg = null;
		if (attendanceRecord == null || attendanceRecord.getStartRecordDateTime() == null) {
			errorMsg = "出勤打刻されていません";
		}

		return errorMsg;
	}


	private boolean record(MEmployee employee, LocalDateTime recordTime, boolean isSrart) {
		TAttendanceRecord attendanceRecord = getAttendanceRecordByEmployeeAndStartRecordDate(
				employee.getId(), recordTime.toLocalDate());

		if (isSrart) {
			attendanceRecord = new TAttendanceRecord();
			attendanceRecord.setStartRecordDateTime(recordTime);
		} else {
			if (attendanceRecord != null) {
				attendanceRecord.setEndRecordDateTime(recordTime);
			}
		}
		if (attendanceRecord != null) {
			attendanceRecord.setEmployee(employee);
			//テストのためコメントアウト
			//attendanceRecordDao.save(attendanceRecord);
		}
		
		return true; // 今は戻り値を使わない
	}
	
	private TAttendanceRecord getAttendanceRecordByEmployeeAndStartRecordDate(Long employeeId, LocalDate recordTime) {
//		MEmployee employee = employeeDao.findById(employeeId)
//				.orElseThrow(() -> new UsernameNotFoundException("user not found"));
		// 二重打刻どうするか
//		TAttendanceRecord attendanceRecord = attendanceRecordRepo.findByEmployeeAndStartRecord(employee.getId(),
//				recordTime);
//
//		return attendanceRecord;
		return null;
	}
}