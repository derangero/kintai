package com.example.demo.daoImpl.master;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.common.DaoBase;
import com.example.demo.dao.master.custom.MRoleDetailCustomDao;
import com.example.demo.entity.master.MRole;
import com.example.demo.entity.master.MRoleDetail;

@Repository
@Transactional
public class MRoleDetailDaoImpl extends DaoBase implements MRoleDetailCustomDao {

	@Override
	public List<MRoleDetail> listByRole(Long roleId) {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<MRoleDetail> cq = cb.createQuery(MRoleDetail.class);
		Root<MRoleDetail> root = cq.from(MRoleDetail.class);
		Join<MRoleDetail, MRole> roleJoin = root.join("role");
		cq.select(root)
			.where(cb.equal(roleJoin.get("id"), roleId));

		return em.createQuery(cq).getResultList();
	}
}