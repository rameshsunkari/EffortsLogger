package com.efforts.service.action;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.efforts.dao.RolesInfoDAO;
import com.efforts.model.RolesInfo;

/**
 * Session Bean implementation class RolesServiceBean
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class RolesServiceBean implements RolesServiceBeanLocal {
	private static Logger logger = Logger.getLogger(RolesServiceBean.class);

	@PersistenceContext(unitName = "EffortsLoggerJPA")
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public RolesServiceBean() {

	}

	@Override
	public List<RolesInfo> getAllRoles() {

		return getRolesDAO().getAllRoles();

	}

	@Override
	public void addRole(RolesInfo role) {

		getRolesDAO().create(role);
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public RolesInfoDAO getRolesDAO() {
		return new RolesInfoDAO(em);
	}

	@Override
	public RolesInfo getRoleById(Long roleId) {

		return getRolesDAO().getRoleById(roleId);
	}

	@Override
	public void updateRole(RolesInfo role) {
		getRolesDAO().edit(role);

	}

}
