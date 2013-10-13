package com.efforts.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.efforts.model.EffortsInfo;

public class EffortsInfoDAO extends BaseDAO<EffortsInfo> {

	private static Logger logger = Logger.getLogger(EffortsInfoDAO.class);

	private EntityManager em;

	private static final String EFFORTS_INFO_ID = "effortsInfoId";
	private static final String EMPID = "empId";
	private static final String MGRID = "mgrId";

	public EffortsInfoDAO(EntityManager em) {
		super(EffortsInfo.class);
		this.em = em;
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

	public EffortsInfo getEffortsInfoById(Long effortsInfoId) {
		logger.debug("Getting the Efforts Info of:" + effortsInfoId);
		Query query = getEntityManager().createNamedQuery(
				EffortsInfo.GET_EFFORTINFO_BY_ID, EffortsInfo.class);
		query.setParameter(EFFORTS_INFO_ID, effortsInfoId);
		EffortsInfo effortsInfo = (EffortsInfo) query.getSingleResult();

		return effortsInfo;
	}

	public List<EffortsInfo> getAllEfforts() {
		logger.debug("Getting all the efforts ");

		Query query = getEntityManager().createNamedQuery(
				EffortsInfo.GET_ALL_EFFORTS, EffortsInfo.class);

		List<EffortsInfo> list = (List<EffortsInfo>) query.getResultList();

		return list;

	}

	public List<EffortsInfo> getAllEmpEfforts(Long empId) {

		logger.debug("Getting all the efforts of an Employee: " + empId);

		Query query = getEntityManager().createNamedQuery(
				EffortsInfo.GET_ALL_EMP_EFFORTS, EffortsInfo.class);

		query.setParameter(EMPID, empId);

		List<EffortsInfo> list = (List<EffortsInfo>) query.getResultList();

		return list;
	}

	public List<EffortsInfo> getEmpOfManagerEfforts(Long mgrId) {

		logger.debug("Getting all the efforts of an Employee: " + mgrId);

		Query query = getEntityManager().createNamedQuery(
				EffortsInfo.GET_MGR_ALL_EMP_EFFORTS, EffortsInfo.class);

		query.setParameter(MGRID, mgrId);
		List<EffortsInfo> list = (List<EffortsInfo>) query.getResultList();

		return list;
	}

}
