package com.efforts.service.action;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.efforts.dao.ProjectInfoDAO;
import com.efforts.model.ProjectInfo;

/**
 * Session Bean implementation class ProjectInfoServiceBean
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProjectInfoServiceBean implements ProjectInfoServiceBeanLocal {

	private static Logger logger = Logger
			.getLogger(ProjectInfoServiceBean.class);

	@PersistenceContext(unitName = "EffortsLoggerJPA")
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public ProjectInfoServiceBean() {

	}

	@Override
	public List<ProjectInfo> getProjectInfoList() {

		return getProjectDAO().getProjectInfoList();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addProjectInfo(ProjectInfo project) {
		getProjectDAO().create(project);

	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public ProjectInfoDAO getProjectDAO() {
		return new ProjectInfoDAO(em);
	}

	@Override
	public ProjectInfo getProjectById(Long projectId) {

		return getProjectDAO().getProjectById(projectId);
	}

	@Override
	public void updateProjectInfo(ProjectInfo project) {
		getProjectDAO().edit(project);

	}

}
