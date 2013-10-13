package com.efforts.service.action;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.efforts.dao.ManagerInfoDAO;
import com.efforts.model.ManagerInfo;

/**
 * Session Bean implementation class ManagerInfoServiceBean
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ManagerInfoServiceBean implements ManagerInfoServiceBeanLocal {

	private static Logger logger = Logger
			.getLogger(ManagerInfoServiceBean.class);

	@PersistenceContext(unitName = "EffortsLoggerJPA")
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public ManagerInfoServiceBean() {

	}

	@Override
	public List<ManagerInfo> getAllManagers() {

		return getManagerInfoDAO().getAllManagers();
	}

	@Override
	public void addManager(ManagerInfo manager) {
		getManagerInfoDAO().create(manager);
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public ManagerInfo getManagerById(Long mgrId) {

		return getManagerInfoDAO().getManagerById(mgrId);
	}

	public ManagerInfoDAO getManagerInfoDAO() {
		return new ManagerInfoDAO(em);
	}

}
