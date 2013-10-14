package com.efforts.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.naming.NamingException;

import com.efforts.model.Effortstype;
import com.efforts.model.ProjectInfo;
import com.efforts.model.RolesInfo;
import com.efforts.model.UserInfo;
import com.efforts.service.action.EffortsServiceBeanLocal;
import com.efforts.service.action.ProjectInfoServiceBeanLocal;
import com.efforts.service.action.RolesServiceBeanLocal;
import com.efforts.service.login.UserServiceBeanLocal;
import com.efforts.service.util.EffortsServiceConstants;
import com.efforts.utilities.EffortsConstants;
import com.efforts.utilities.EffortsServiceLocator;
import com.efforts.utilities.JsfUtil;
import com.efforts.utilities.MessageUtility;
import com.efforts.utilities.NavigationOutCome;

@ManagedBean
@RequestScoped
public class EffortsTypeDetailBean {

	private Effortstype type;
	private UserInfo userInfo;
	private boolean newEffortsType;

	private UserServiceBeanLocal userService;
	private ProjectInfoServiceBeanLocal projectService;
	private RolesServiceBeanLocal rolesService;
	private EffortsServiceBeanLocal effortsService;

	private List<SelectItem> listOfProjects;
	private Long selectedProject;

	private List<SelectItem> listOfRoles;
	private Long selectedRole;

	@PostConstruct
	public void init() {
		Long empId = (Long) JsfUtil
				.getSessionAttribute(EffortsConstants.EMP_ID);
		userInfo = getUserService().getUserInfo(empId);

		List<ProjectInfo> projectList = getProjectService()
				.getProjectInfoList();
		List<SelectItem> projectItemList = new ArrayList<SelectItem>();
		projectItemList.add(new SelectItem("0", "Please Select a Project"));
		for (ProjectInfo project : projectList) {
			projectItemList.add(new SelectItem(project.getId(), project
					.getName()));
		}

		setListOfProjects(projectItemList);

		List<RolesInfo> roleList = getRolesService().getAllRoles();
		List<SelectItem> roleItemList = new ArrayList<SelectItem>();
		roleItemList.add(new SelectItem("0", "Please Select a Role"));
		for (RolesInfo role : roleList) {
			roleItemList.add(new SelectItem(role.getId(), role.getName()));
		}

		setListOfRoles(roleItemList);

		type = (Effortstype) JsfUtil
				.getRequestAttribute(EffortsConstants.EFFORTS_TYPE_INFO);
		if (type == null) {
			type = new Effortstype();
			newEffortsType = true;
		} else {
			selectedProject = type.getProjectInfo().getId();
			selectedRole = type.getRoleInfo().getId();
		}

	}

	public Effortstype getType() {
		return type;
	}

	public void setType(Effortstype type) {
		this.type = type;
	}

	public UserServiceBeanLocal getUserService() {
		try {
			userService = (UserServiceBeanLocal) EffortsServiceLocator
					.getLocalHome(EffortsServiceConstants.UserServiceConstant);

		} catch (NamingException e) {

			e.printStackTrace();
		}

		return userService;
	}

	public void setUserService(UserServiceBeanLocal userService) {
		this.userService = userService;
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

	public RolesServiceBeanLocal getRolesService() {
		try {
			rolesService = (RolesServiceBeanLocal) EffortsServiceLocator
					.getLocalHome(EffortsServiceConstants.RolesServiceConstant);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rolesService;
	}

	public void setRolesService(RolesServiceBeanLocal rolesService) {
		this.rolesService = rolesService;
	}

	public List<SelectItem> getListOfProjects() {
		return listOfProjects;
	}

	public void setListOfProjects(List<SelectItem> listOfProjects) {
		this.listOfProjects = listOfProjects;
	}

	public Long getSelectedProject() {
		return selectedProject;
	}

	public void setSelectedProject(Long selectedProject) {
		this.selectedProject = selectedProject;
	}

	public List<SelectItem> getListOfRoles() {
		return listOfRoles;
	}

	public void setListOfRoles(List<SelectItem> listOfRoles) {
		this.listOfRoles = listOfRoles;
	}

	public Long getSelectedRole() {
		return selectedRole;
	}

	public void setSelectedRole(Long selectedRole) {
		this.selectedRole = selectedRole;
	}

	public String cancel() {
		return NavigationOutCome.VIEW_EFFORTS_TYPE;
	}

	public String saveEffortsType() {
		RolesInfo role = getRolesService().getRoleById(getSelectedRole());

		type.setRoleInfo(role);

		ProjectInfo project = getProjectService().getProjectById(
				getSelectedProject());
		type.setProjectInfo(project);

		if (newEffortsType) {
			getEffortsService().addEffortsType(type);
			MessageUtility.addSuccessMessage("efforttype_add_success");
		} else {
			getEffortsService().updateEffortsType(type);
			MessageUtility.addSuccessMessage("efforttype_update_success");
		}

		return NavigationOutCome.VIEW_EFFORTS_TYPE;
	}

	public List<Effortstype> getAllEffortstypes() {
		return getEffortsService().getAllEffortstype();
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public EffortsServiceBeanLocal getEffortsService() {
		try {
			effortsService = (EffortsServiceBeanLocal) EffortsServiceLocator
					.getLocalHome(EffortsServiceConstants.EffortsServiceConstant);

		} catch (NamingException e) {

			e.printStackTrace();
		}
		return effortsService;
	}

	public void setEffortsService(EffortsServiceBeanLocal effortsService) {
		this.effortsService = effortsService;
	}

	public boolean isNewEffortsType() {
		return newEffortsType;
	}

	public void setNewEffortsType(boolean newEffortsType) {
		this.newEffortsType = newEffortsType;
	}
}
