package com.example.demo.dao.master;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.master.custom.MEmployeeCustomDao;
import com.example.demo.entity.master.MEmployee;

@Repository
public interface MEmployeeDao extends JpaRepository<MEmployee, Long>, MEmployeeCustomDao 
{
	public Optional<MEmployee> findByCode(String code);
}
