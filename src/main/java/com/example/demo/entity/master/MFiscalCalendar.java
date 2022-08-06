package com.example.demo.entity.master;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mfiscal_calendars")
public class MFiscalCalendar {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "calendar_year")
    private Integer calendarYear;

    @Column(name = "calendar_date")
    private LocalDate calendarDate;

    @Column(name = "stamp_deleted", columnDefinition="boolean default false")
    private Boolean stampDeleted = false;

    public MFiscalCalendar() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCalendarYear() {
		return calendarYear;
	}

	public void setCalendarYear(Integer calendarYear) {
		this.calendarYear = calendarYear;
	}

	public LocalDate getCalendarDate() {
		return calendarDate;
	}

	public void setCalendarDate(LocalDate calendarDate) {
		this.calendarDate = calendarDate;
	}

	public Boolean getStampDeleted() {
		return stampDeleted;
	}

	public void setStampDeleted(Boolean stampDeleted) {
		this.stampDeleted = stampDeleted;
	}
}