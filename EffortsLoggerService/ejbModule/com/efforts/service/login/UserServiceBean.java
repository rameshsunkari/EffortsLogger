package com.efforts.service.login;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.efforts.dao.UserInfoDAO;
import com.efforts.model.UserInfo;

/**
 * Session Bean implementation class UserServiceBean
 */
@Stateless
@Local(UserServiceBeanLocal.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserServiceBean implements UserServiceBeanLocal {

	@PersistenceContext(unitName = "EffortsLoggerJPA")
	private EntityManager em;

	public UserServiceBean() {

	}

	@Override
	public boolean checkUserLogin(Long empId, String password) {

		return getUserDAO().checkLogin(empId, password);
	}

	@Override
	public UserInfo getUserInfo(Long empId) {
		return getUserDAO().getEmpById(empId);
	}

	public UserInfoDAO getUserDAO() {
		return new UserInfoDAO(em);
	}

	@Override
	public void addMember(UserInfo user) {
		getUserDAO().create(user);

	}

	@Override
	public List<UserInfo> getListOfMembers() {

		return getUserDAO().getAllUsers();
	}

	@Override
	public List<UserInfo> getManagerMembersList(Long mgrId) {

		return getUserDAO().getManagerMemberList(mgrId);
	}

}
