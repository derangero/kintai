package com.example.demo.entity.transaction;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.entity.master.MEmployee;

@Entity
@Table(name = "tattendance_records")
public class TAttendanceRecord implements Serializable {
	 private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_record_datetime")
    private LocalDateTime startRecordDateTime;

    @Column(name = "end_record_datetime")
    private LocalDateTime endRecordDateTime;

    @ManyToOne()
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private MEmployee employee;

    @Column(name = "stamp_approval", columnDefinition="boolean default false")
    private Boolean stampApproval = false;

    @Column(name = "stamp_deleted", columnDefinition="boolean default false")
    private Boolean stampDeleted = false;

    public TAttendanceRecord() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getStartRecordDateTime() {
		return startRecordDateTime;
	}

	public void setStartRecordDateTime(LocalDateTime startRecordDateTime) {
		this.startRecordDateTime = startRecordDateTime;
	}

	public LocalDateTime getEndRecordDateTime() {
		return endRecordDateTime;
	}

	public void setEndRecordDateTime(LocalDateTime endRecordDateTime) {
		this.endRecordDateTime = endRecordDateTime;
	}

	public MEmployee getEmployee() {
		return employee;
	}

	public void setEmployee(MEmployee employee) {
		this.employee = employee;
	}

	public Boolean getStampApproval() {
		return stampApproval;
	}

	public void setStampApproval(Boolean stampApproval) {
		this.stampApproval = stampApproval;
	}

	public Boolean getStampDeleted() {
		return stampDeleted;
	}

	public void setStampDeleted(Boolean stampDeleted) {
		this.stampDeleted = stampDeleted;
	}
}