package com.efforts.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.efforts.model.ProjectInfo;

public class ProjectInfoDAO extends BaseDAO<ProjectInfo> {

	private static Logger logger = Logger.getLogger(ProjectInfoDAO.class);
	private EntityManager em;

	private static final String PROJECT_ID = "projectId";

	public ProjectInfoDAO(EntityManager em) {
		super(ProjectInfo.class);
		this.em = em;
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

	public List<ProjectInfo> getProjectInfoList() {
		logger.debug("Checking the projects from Service method");
		Query query = em.createNamedQuery(ProjectInfo.GET_ALL_PROJECTS,
				ProjectInfo.class);

		List<ProjectInfo> result = (List<ProjectInfo>) query.getResultList();
		return result;
	}

	public ProjectInfo getProjectById(Long projectId) {

		logger.debug("Checking the Project with id :" + projectId);
		Query query = em.createNamedQuery(ProjectInfo.GET_PROJECT_BY_ID,
				ProjectInfo.class);

		query.setParameter(PROJECT_ID, projectId);

		ProjectInfo result = (ProjectInfo) query.getSingleResult();
		return result;
	}
}
