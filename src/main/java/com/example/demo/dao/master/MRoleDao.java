package com.example.demo.dao.master;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.master.custom.MRoleCustomDao;
import com.example.demo.entity.master.MRole;

@Repository
public interface MRoleDao extends JpaRepository<MRole, Long>, MRoleCustomDao {
	Optional<MRole> findByCode(Integer code);
}
