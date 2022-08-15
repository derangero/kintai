package com.example.demo.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.common.util.Util;
import com.example.demo.dao.master.MEmployeeDao;
import com.example.demo.dao.master.MRoleDao;
import com.example.demo.dto.RegisterDto;
import com.example.demo.entity.master.MEmployee;
import com.example.demo.entity.master.MRole;
import com.example.demo.service.RegisterService;

@Service
public class RegisterServiceImple implements RegisterService {

	@Autowired
	MEmployeeDao employeeDao;
	@Autowired
	MRoleDao roleDao;
	@Autowired
    PasswordEncoder passwordEncoder;

	@Override
	public void createUser(String name, String password, Integer roleCode) {
		MRole role = roleDao.findByCode(roleCode).orElseGet(null);

		MEmployee employee = new MEmployee();
        employee.setName(name);
        employee.setCode(Util.zeroPadding4(employeeDao.count() + 1L));
        // パスワードはハッシュ化する
        employee.setPassword(passwordEncoder.encode(password));
        employee.setRole(role);
        employee.setStampDeleted(false);

        employeeDao.save(employee);
	}

	@Override
	public void setModelByRegistering(Model model) {
        model.addAttribute("registerForm", new RegisterDto());
        List<MRole> roles = roleDao.listAllOrderByCode();
        model.addAttribute("roleList", roles);
        model.addAttribute("selectedValue", "1");
	}
}