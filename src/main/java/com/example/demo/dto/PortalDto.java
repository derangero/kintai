package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.common.util.Util;

public class PortalDto extends DtoBase {

    private String recordedTime;
    private boolean stampRecordedByStart;
    private boolean stampRecordedByEnd;
    private String errorMsg;

    public PortalDto() {
    }

    public static PortalDto fromByGoToWork(LocalDateTime nowTime, String errorMsg) {
    	PortalDto dto = new PortalDto();
    	if (errorMsg == null) {
			dto.stampRecordedByStart = true;
			dto.recordedTime = Util.formatHHmmss(nowTime);
    	}
    	dto.errorMsg = errorMsg;

    	return dto;
    }

    public static PortalDto fromByLeaveWork(LocalDateTime nowTime, String errorMsg) {
    	PortalDto dto = new PortalDto();
    	if (errorMsg == null) {
    		dto.stampRecordedByStart = true;
    		dto.stampRecordedByEnd = true;
    		dto.recordedTime = Util.formatHHmmss(nowTime);
    	}
    	dto.errorMsg = errorMsg;
    	
    	return dto;
    }

	public String getRecordedTime() {
		return recordedTime;
	}

	public void setRecordedTime(String recordedTime) {
		this.recordedTime = recordedTime;
	}

	public boolean isStampRecordedByStart() {
		return stampRecordedByStart;
	}

	public void setStampRecordedByStart(boolean stampRecordedByStart) {
		this.stampRecordedByStart = stampRecordedByStart;
	}

	public boolean isStampRecordedByEnd() {
		return stampRecordedByEnd;
	}

	public void setStampRecordedByEnd(boolean stampRecordedByEnd) {
		this.stampRecordedByEnd = stampRecordedByEnd;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}