package com.efforts.dao;

import java.util.List;

import javax.management.relation.RoleInfo;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.efforts.model.RolesInfo;

public class RolesInfoDAO extends BaseDAO<RolesInfo> {
	private static Logger logger = Logger.getLogger(RolesInfoDAO.class);
	private EntityManager em;

	private static String ROLE_ID = "roleId";

	public RolesInfoDAO(EntityManager em) {
		super(RolesInfo.class);
		this.em = em;
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

	public List<RolesInfo> getAllRoles() {
		logger.debug("Checking the roles from DAO method");
		Query query = em.createNamedQuery(RolesInfo.GET_ALL_ROLES,
				RolesInfo.class);

		List<RolesInfo> result = (List<RolesInfo>) query.getResultList();
		return result;

	}

	public boolean addRole(RolesInfo role) {

		try {
			getEntityManager().persist(role);
			return true;
		} catch (Exception e) {

		}
		return false;
	}

	public RolesInfo getRoleById(Long roleId) {

		logger.debug("Checking the role with id :" + roleId);
		Query query = em.createNamedQuery(RolesInfo.GET_ROLE_BY_ID,
				RolesInfo.class);

		query.setParameter(ROLE_ID, roleId);

		RolesInfo result = (RolesInfo) query.getSingleResult();
		return result;
	}
}
