package com.example.demo.dao.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.master.custom.MFiscalCalendarCustomDao;
import com.example.demo.entity.master.MFiscalCalendar;

@Repository
public interface MFiscalCalendarDao extends JpaRepository<MFiscalCalendar, Long>, MFiscalCalendarCustomDao {
}