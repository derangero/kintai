package com.example.demo.daoImpl.transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.common.util.DataUtil;
import com.example.demo.dao.common.DaoBase;
import com.example.demo.dao.transaction.custom.TAttendanceRecordCustomDao;
import com.example.demo.entity.master.MEmployee;
import com.example.demo.entity.transaction.TAttendanceRecord;

@Repository
@Transactional
public class TAttendanceRecordDaoImpl extends DaoBase implements TAttendanceRecordCustomDao {

	@Override
	public TAttendanceRecord findByEmployeeAndStartRecord(Long employeeId, LocalDate startRecordDate) {
		if (employeeId != null && startRecordDate != null) {
			CriteriaBuilder cb = getCriteriaBuilder();
			CriteriaQuery<TAttendanceRecord> cq = cb.createQuery(TAttendanceRecord.class);
			Root<TAttendanceRecord> root = cq.from(TAttendanceRecord.class);
			Join<TAttendanceRecord, MEmployee> employeeJoin = root.join("employee");
			cq.select(root)
				.where(
					cb.equal(employeeJoin.get("id"), employeeId),
					cb.greaterThanOrEqualTo(root.get("startRecordDateTime"), DataUtil.toLocalDateTime(startRecordDate)),
					cb.lessThanOrEqualTo(root.get("startRecordDateTime"), DataUtil.toLocalDateTime(startRecordDate.plusDays(1)))
				);
				
			return em.createQuery(cq).getResultStream().findFirst().orElse(null);
		}
		return null;
	}

	@Override
	public List<TAttendanceRecord> listByEmployeeAndRecordRange(Long employeeId, LocalDate fromDate, LocalDate toDate) {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<TAttendanceRecord> cq = cb.createQuery(TAttendanceRecord.class);
		Root<TAttendanceRecord> root = cq.from(TAttendanceRecord.class);
		Join<TAttendanceRecord, MEmployee> employeeJoin = root.join("employee");

		cq.select(root)
			.where(
				cb.equal(employeeJoin.get("id"), employeeId),
				
				cb.greaterThanOrEqualTo(root.get("startRecordDateTime"), DataUtil.toLocalDateTime(fromDate)),
				
				cb.lessThanOrEqualTo(root.get("endRecordDateTime"), DataUtil.toLocalDateTime(toDate.plusDays(1)))
			);

		return em.createQuery(cq).getResultStream().collect(Collectors.toList());
	}
}
