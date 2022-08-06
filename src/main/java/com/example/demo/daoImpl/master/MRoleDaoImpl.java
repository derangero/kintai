package com.example.demo.daoImpl.master;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.common.DaoBase;
import com.example.demo.dao.master.custom.MRoleCustomDao;
import com.example.demo.entity.master.MRole;

@Repository
@Transactional
public class MRoleDaoImpl extends DaoBase implements MRoleCustomDao {

	@Override
	public List<MRole> listAllOrderByCode() {
        CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<MRole> cq = cb.createQuery(MRole.class);
		Root<MRole> root = cq.from(MRole.class);
		cq.select(root)
			.orderBy(cb.asc(root.get("code")));

		return em.createQuery(cq).getResultList();
	}
}
