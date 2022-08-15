package com.example.demo.dto.header;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.example.demo.annotation.Message;
import com.example.demo.dto.DtoBase;

public class AttendanceRecordListHeaderDto extends DtoBase {

	@NotNull
	@Min(1000)
	@Max(9999)
	private Integer fiscalYear;

	@NotNull
	@Min(1)
	@Max(12)
	private Integer month;
	
	public AttendanceRecordListHeaderDto() {
	}

	@Message(message = "common.fiscalYear")
	public Integer getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(Integer fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	@Message(message = "common.month")
	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}
}