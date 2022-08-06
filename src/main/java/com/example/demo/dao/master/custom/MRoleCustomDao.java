package com.example.demo.dao.master.custom;

import java.util.List;

import com.example.demo.entity.master.MRole;

public interface MRoleCustomDao {

	List<MRole> listAllOrderByCode();
}
