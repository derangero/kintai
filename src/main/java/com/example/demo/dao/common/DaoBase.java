package com.example.demo.dao.common;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

import org.springframework.beans.factory.annotation.Autowired;

public class DaoBase {
	
	@Autowired
	protected EntityManager em;

	// Tのクラスを格納するフィールド
//	public Class<T> genericsClazz = null;
//	
//	public DaoBase(T... args) {
//		this.genericsClazz = (Class<T>) args.getClass().getComponentType();
//	}

	protected CriteriaBuilder getCriteriaBuilder() {
		return em.getCriteriaBuilder();
	}
}

/*      CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<MEmployee> cq = cb.createQuery(MEmployee.class);
		Root<MEmployee> root = cq.from(MEmployee.class);
		cq.select(root)
			.where(cb.equal(root.get("code"), code));

		TypedQuery<MEmployee> query = em.createQuery(cq);

		return Optional.ofNullable(query.getSingleResult());
*/
//.orderBy(cb.desc(root.get("code"))