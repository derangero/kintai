package com.example.demo.daoImpl.master;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.common.DaoBase;
import com.example.demo.dao.master.custom.MFiscalCalendarCustomDao;
import com.example.demo.entity.master.MFiscalCalendar;

@Repository
@Transactional
public class MFiscalCalendarDaoImpl extends DaoBase implements MFiscalCalendarCustomDao {

	@Override
	public List<MFiscalCalendar> listByFromAndTo(LocalDate fromDate, LocalDate toDate) {
        CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<MFiscalCalendar> cq = cb.createQuery(MFiscalCalendar.class);
		Root<MFiscalCalendar> root = cq.from(MFiscalCalendar.class);

		cq.select(root)
			.where(
				cb.greaterThanOrEqualTo(root.get("calendarDate"), fromDate),
				cb.lessThanOrEqualTo(root.get("calendarDate"), toDate)
			)
			.orderBy(cb.asc(root.get("calendarDate")));

		return em.createQuery(cq).getResultList();
	}

}
