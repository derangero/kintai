package com.example.demo.dao.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.master.custom.MRoleDetailCustomDao;
import com.example.demo.entity.master.MRoleDetail;

@Repository
public interface MRoleDetailDao extends JpaRepository<MRoleDetail, Long>, MRoleDetailCustomDao {
}
