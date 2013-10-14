package com.efforts.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.naming.NamingException;

import com.efforts.model.ManagerInfo;
import com.efforts.model.ProjectInfo;
import com.efforts.service.action.ManagerInfoServiceBeanLocal;
import com.efforts.service.action.ProjectInfoServiceBeanLocal;
import com.efforts.service.util.EffortsServiceConstants;
import com.efforts.utilities.EffortsConstants;
import com.efforts.utilities.EffortsServiceLocator;
import com.efforts.utilities.JsfUtil;
import com.efforts.utilities.MessageUtility;
import com.efforts.utilities.NavigationOutCome;

@ManagedBean
@RequestScoped
public class ProjectInfoDetailBean {

	private ProjectInfo project;
	private boolean newProject;

	private ProjectInfoServiceBeanLocal projectService;
	private ManagerInfoServiceBeanLocal managerService;

	private List<SelectItem> listOfManagers;
	private Long selectedManager;

	@PostConstruct
	private void init() {

		List<ManagerInfo> managersList = getManagerService().getAllManagers();
		List<SelectItem> itemList = new ArrayList<SelectItem>();
		itemList.add(new SelectItem("0", "Please Select a Manager"));
		for (ManagerInfo manager : managersList) {
			itemList.add(new SelectItem(manager.getId(), manager.getFname()
					+ " " + manager.getLname()));
		}

		setListOfManagers(itemList);

		project = (ProjectInfo) JsfUtil
				.getRequestAttribute(EffortsConstants.PROJECT_INFO);
		if (project == null) {
			project = new ProjectInfo();
			newProject = true;
		} else {
			selectedManager = project.getManagerInfo().getId();
		}

	}

	public ProjectInfo getProject() {
		return project;
	}

	public void setProject(ProjectInfo project) {
		this.project = project;
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

	public String addProjectInfo() {
		String result;

		ManagerInfo manager = managerService
				.getManagerById(getSelectedManager());

		project.setManagerInfo(manager);

		if (newProject) {
			getProjectService().addProjectInfo(project);
			MessageUtility.addSuccessMessage("projectInfo_add_success");
		} else {
			getProjectService().updateProjectInfo(project);
			MessageUtility.addSuccessMessage("projectInfo_update_success");
		}
		result = NavigationOutCome.VIEW_PROJECTS;

		return result;

	}

	public String cancel() {
		return NavigationOutCome.VIEW_PROJECTS;
	}

	public List<ProjectInfo> getListOfProjects() {
		return getProjectService().getProjectInfoList();
	}

	public void setListOfProjects(List<ProjectInfo> listOfProjects) {
	}

	public ManagerInfoServiceBeanLocal getManagerService() {
		try {
			managerService = (ManagerInfoServiceBeanLocal) EffortsServiceLocator
					.getLocalHome(EffortsServiceConstants.ManagerServiceConstant);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return managerService;
	}

	public void setManagerService(ManagerInfoServiceBeanLocal managerService) {
		this.managerService = managerService;
	}

	public List<SelectItem> getListOfManagers() {
		return listOfManagers;
	}

	public void setListOfManagers(List<SelectItem> listOfManagers) {
		this.listOfManagers = listOfManagers;
	}

	public Long getSelectedManager() {
		return selectedManager;
	}

	public void setSelectedManager(Long selectedManager) {
		this.selectedManager = selectedManager;
	}

	public boolean isNewProject() {
		return newProject;
	}

	public void setNewProject(boolean newProject) {
		this.newProject = newProject;
	}

}
