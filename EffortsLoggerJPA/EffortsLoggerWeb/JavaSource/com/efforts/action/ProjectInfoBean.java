package com.efforts.action;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.NamingException;

import com.efforts.model.ProjectInfo;
import com.efforts.service.action.ProjectInfoServiceBeanLocal;
import com.efforts.service.util.EffortsServiceConstants;
import com.efforts.utilities.EffortsConstants;
import com.efforts.utilities.EffortsServiceLocator;
import com.efforts.utilities.JsfUtil;
import com.efforts.utilities.NavigationOutCome;

@ManagedBean
@RequestScoped
public class ProjectInfoBean {

	private ProjectInfoServiceBeanLocal projectService;

	@PostConstruct
	private void init() {

	}

	public ProjectInfoServiceBeanLocal getProjectService() {
		try {
			projectService = (ProjectInfoServiceBeanLocal) EffortsServiceLocator
					.getLocalHome(EffortsServiceConstants.ProjectServiceConstant);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return projectService;
	}

	public void setProjectService(ProjectInfoServiceBeanLocal projectService) {
		this.projectService = projectService;
	}

	public String addProject() {
		return NavigationOutCome.ADD_PROJECTS;
	}

	public List<ProjectInfo> getListOfProjects() {
		return getProjectService().getProjectInfoList();
	}

	public String viewProjectInfo(ProjectInfo project) {
		JsfUtil.addRequestAttribute(EffortsConstants.PROJECT_INFO, project);
		return NavigationOutCome.VIEW_PROJECTS_INFO;
	}

}
