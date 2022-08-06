package com.example.demo.service;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.master.MEmployeeDao;
import com.example.demo.dao.master.MRoleDetailDao;
import com.example.demo.entity.master.MEmployee;
import com.example.demo.entity.master.MRole;
import com.example.demo.entity.master.MRoleDetail;
import com.example.demo.serviceImple.UserDetailsImpl;

@Transactional
@Service
public class DefaultUserDetailsService implements UserDetailsService {

    @Autowired
    MEmployeeDao employeeDao;
    @Autowired
    MRoleDetailDao roleDetailDao;

    private MEmployee employee;

    @Override
	public UserDetails loadUserByUsername(String code) throws UsernameNotFoundException {
        MEmployee employee = employeeDao.findByCode(code)
        		.orElseThrow(() -> new UsernameNotFoundException("user not found"));
        MRole role = employee.getRole();
        List<MRoleDetail> roleDetails = roleDetailDao.listByRole(role.getId());
        Collection<GrantedAuthority> authority =
        		roleDetails.stream()
                .map((roleDetail) -> new SimpleGrantedAuthority(roleDetail.getName()))
                .collect(Collectors.toList());

        this.employee = employee;

        return new UserDetailsImpl(
        	employee.getCode(),
        	employee.getPassword(),
        	authority,
            employee.getName()
        );
    }
    
    public String getEmployeeName() {
        return employee.getName();
    }
}