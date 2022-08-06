package com.example.demo.form;

import javax.validation.constraints.NotBlank;

public class TopForm {

    @NotBlank
    private String employeeCode;

    public TopForm() {
    }

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
}