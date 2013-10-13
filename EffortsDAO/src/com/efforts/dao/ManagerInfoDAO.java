package com.efforts.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.efforts.model.ManagerInfo;

public class ManagerInfoDAO extends BaseDAO<ManagerInfo> {

	private static Logger logger = Logger.getLogger(ManagerInfoDAO.class);
	private EntityManager em;
	private static final String MGR_ID = "mgrId";

	public ManagerInfoDAO(EntityManager em) {
		super(ManagerInfo.class);
		this.em = em;
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

	public List<ManagerInfo> getAllManagers() {
		logger.debug("Checking the managers from DAO method");
		Query query = getEntityManager().createNamedQuery(
				ManagerInfo.GET_ALL_MANAGERS, ManagerInfo.class);

		List<ManagerInfo> result = (List<ManagerInfo>) query.getResultList();
		return result;
	}

	public ManagerInfo getManagerById(Long mgrId) {

		logger.debug("Checking the manager with id :" + mgrId);
		Query query = getEntityManager().createNamedQuery(
				ManagerInfo.GET_MANAGER_BYID, ManagerInfo.class);

		query.setParameter(MGR_ID, mgrId);

		ManagerInfo result = (ManagerInfo) query.getSingleResult();
		return result;
	}

}
