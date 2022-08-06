package com.example.demo.dao.master.custom;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.entity.master.MFiscalCalendar;

public interface MFiscalCalendarCustomDao {
	public List<MFiscalCalendar> listByFromAndTo(LocalDate fromDate, LocalDate toDate);
}
