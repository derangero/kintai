package com.example.demo.dao.master.custom;

import java.util.List;

import com.example.demo.entity.master.MRoleDetail;

public interface MRoleDetailCustomDao {

	public List<MRoleDetail> listByRole(Long roleId);
}
