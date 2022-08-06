package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RegisterForm {

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotNull
    private Integer roleCode;

    public RegisterForm() {
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(Integer roleCode) {
		this.roleCode = roleCode;
	}
}