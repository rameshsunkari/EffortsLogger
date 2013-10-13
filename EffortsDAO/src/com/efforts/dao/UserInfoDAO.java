package com.efforts.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.efforts.model.UserInfo;

public class UserInfoDAO extends BaseDAO<UserInfo> {

	private static Logger logger = Logger.getLogger(UserInfoDAO.class);

	EntityManager em;

	private static final String EMPID = "empId";
	private static final String PASSWORD = "password";
	private static final String MGRID = "mgrId";

	public UserInfoDAO(EntityManager em) {
		super(UserInfo.class);
		this.em = em;
	}

	protected BaseDAO<UserInfo> getDAO() {
		return null;
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public boolean checkLogin(Long empId, String password) {
		logger.debug("Checking the user login in Service method");
		Query query = getEntityManager().createNamedQuery(UserInfo.CHECK_LOGIN,
				UserInfo.class);
		query.setParameter(EMPID, empId);
		query.setParameter(PASSWORD, password);
		boolean result = query.getResultList().size() > 0;
		return result;
	}

	public UserInfo getEmpById(Long empId) {
		logger.debug("Getting the User Info of emp:" + empId);
		Query query = getEntityManager().createNamedQuery(
				UserInfo.GET_EMP_BY_ID, UserInfo.class);
		query.setParameter(EMPID, empId);
		UserInfo user = (UserInfo) query.getSingleResult();

		return user;
	}

	public List<UserInfo> getAllUsers() {
		logger.debug("Getting all the users ");

		Query query = getEntityManager().createNamedQuery(UserInfo.GET_ALL_EMP,
				UserInfo.class);

		List<UserInfo> list = (List<UserInfo>) query.getResultList();

		return list;

	}

	public List<UserInfo> getManagerMemberList(Long mgrId) {
		logger.debug("Getting all the employees under that manager: " + mgrId);

		Query query = getEntityManager().createNamedQuery(UserInfo.GET_MANAGER_MEMBERS,
				UserInfo.class);

		query.setParameter(MGRID, mgrId);
		List<UserInfo> list = (List<UserInfo>) query.getResultList();

		return list;
	}
}
