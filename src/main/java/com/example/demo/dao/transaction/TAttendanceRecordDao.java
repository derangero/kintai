package com.example.demo.dao.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.transaction.custom.TAttendanceRecordCustomDao;
import com.example.demo.entity.transaction.TAttendanceRecord;

@Repository
public interface TAttendanceRecordDao extends JpaRepository<TAttendanceRecord, Long>, TAttendanceRecordCustomDao {
}

//@Repository
//public interface TAttendanceRecordRepository extends JpaRepository<TAttendanceRecord, Long> {
//
//	TAttendanceRecord getByEmployee(Long employeeId);
//	
//	// JPQLはポスグレのコマンドがすべて使えるわけではない
//	//@Query("select ar.employee, ar.startRecordDateTime from TAttendanceRecord ar where ar.employee = :employeeId and TO_DATE(TO_CHAR(ar.startRecordDateTime, 'YYYY/MM/DD'),'YYYY/MM/DD') = :startRecordDate")
//	//select項目はすべて必要
//	@Query(value = "select * from tattendance_records ar"
//			+ " where ar.employee_id = :employeeId"
//			+ " and TO_DATE(TO_CHAR(ar.start_record_datetime, 'YYYY/MM/DD'),'YYYY/MM/DD') = :startRecordDate",
//			nativeQuery = true)
//	TAttendanceRecord findByEmployeeAndStartRecord(@Param("employeeId") Long employeeId,
//			@Param("startRecordDate") LocalDate startRecordDate);
//
//	@Query(value = "select * from tattendance_records ar"
//			+ " where ar.employee_id = :employeeId"
//			+ " and TO_DATE(TO_CHAR(ar.start_record_datetime, 'YYYY/MM/DD'),'YYYY/MM/DD') >= :startRecordDate"
//			+ " and case when"
//			+ "  ar.end_record_datetime is null then 1 = 1"
//			+ "  else TO_DATE(TO_CHAR(ar.end_record_datetime, 'YYYY/MM/DD'),'YYYY/MM/DD') <= :endRecordDate"
//			+ " end",
//			nativeQuery = true)
//	List<TAttendanceRecord> findByEmployeeAndRecordRange(@Param("employeeId") Long employeeId,
//			@Param("startRecordDate") LocalDate startRecordDate, @Param("endRecordDate") LocalDate endRecordDate);
//}