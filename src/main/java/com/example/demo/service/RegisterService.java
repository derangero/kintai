package com.example.demo.service;

import org.springframework.ui.Model;

public interface RegisterService {

	void createUser(String name, String password, Integer roleCode);

	void setModelByRegistering(Model model);
}