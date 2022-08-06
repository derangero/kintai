package com.example.demo.dao.transaction.custom;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.entity.transaction.TAttendanceRecord;

public interface TAttendanceRecordCustomDao {

	TAttendanceRecord findByEmployeeAndStartRecord(Long employeeId, LocalDate startRecordDate);
	List<TAttendanceRecord> listByEmployeeAndRecordRange(Long employeeId, LocalDate fromDate, LocalDate toDate);
}
