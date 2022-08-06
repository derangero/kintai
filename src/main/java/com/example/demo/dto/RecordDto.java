package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RecordDto {

	private LocalDate recordDate;
	private LocalDateTime recordDateTimeByStart;
	private LocalDateTime recordDateTimeByEnd;
	private boolean stampApproval;
	
	private String displayStampApproval;
	private String displayWorkingHours;
	
	public RecordDto() {
	}

	public LocalDate getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(LocalDate recordDate) {
		this.recordDate = recordDate;
	}

	public LocalDateTime getRecordDateTimeByStart() {
		return recordDateTimeByStart;
	}

	public void setRecordDateTimeByStart(LocalDateTime recordDateTimeByStart) {
		this.recordDateTimeByStart = recordDateTimeByStart;
	}

	public LocalDateTime getRecordDateTimeByEnd() {
		return recordDateTimeByEnd;
	}

	public void setRecordDateTimeByEnd(LocalDateTime recordDateTimeByEnd) {
		this.recordDateTimeByEnd = recordDateTimeByEnd;
	}

	public boolean getStampApproval() {
		return stampApproval;
	}

	public void setStampApproval(boolean stampApproval) {
		this.stampApproval = stampApproval;
	}

	public String getDisplayStampApproval() {
		return displayStampApproval;
	}

	public void setDisplayStampApproval(String displayStampApproval) {
		this.displayStampApproval = displayStampApproval;
	}

	public String getDisplayWorkingHours() {
		return displayWorkingHours;
	}

	public void setDisplayWorkingHours(String displayWorkingHours) {
		this.displayWorkingHours = displayWorkingHours;
	}
}