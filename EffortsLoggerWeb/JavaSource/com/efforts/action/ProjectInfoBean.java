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
import com.efforts.utilities.EffortsServiceLocator;
import com.efforts.utilities.MessageUtility;
import com.efforts.utilities.NavigationOutCome;

@ManagedBean
@RequestScoped
public class ProjectInfoBean {

	private ProjectInfo project;

	private ProjectInfoServiceBeanLocal projectService;
	private ManagerInfoServiceBeanLocal managerService;

	private List<SelectItem> listOfManagers;
	private Long selectedManager;

	@PostConstruct
	private void init() {

		project = new ProjectInfo();

		List<ManagerInfo> managersList = getManagerService().getAllManagers();
		List<SelectItem> itemList = new ArrayList<SelectItem>();
		itemList.add(new SelectItem("0", "Please Select a Manager"));
		for (ManagerInfo manager : managersList) {
			itemList.add(new SelectItem(manager.getId(), manager.getFname()
					+ " " + manager.getLname()));
		}

		setListOfManagers(itemList);

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

		getProjectService().addProjectInfo(project);
		MessageUtility.addErrorMessage("projectInfo_add_success");
		result = NavigationOutCome.VIEW_PROJECTS;

		return result;

	}

	public String cancel() {
		return NavigationOutCome.VIEW_PROJECTS;
	}

	public String addProject() {
		return NavigationOutCome.ADD_PROJECTS;
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

}
