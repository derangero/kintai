package com.example.demo.daoImpl.master;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.common.DaoBase;
import com.example.demo.dao.master.custom.MEmployeeCustomDao;

@Repository
@Transactional
public class MEmployeeDaoImpl extends DaoBase implements MEmployeeCustomDao {
}
