package com.efforts.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.naming.NamingException;

import com.efforts.mail.MailUtil;
import com.efforts.model.ManagerInfo;
import com.efforts.model.ProjectInfo;
import com.efforts.model.RolesInfo;
import com.efforts.model.UserInfo;
import com.efforts.service.action.ManagerInfoServiceBeanLocal;
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
@SessionScoped
public class MemberInfoDetailBean {

	private UserInfo userInfo;
	private UserInfo newUser;

	private UserServiceBeanLocal userService;
	private ProjectInfoServiceBeanLocal projectService;
	private ManagerInfoServiceBeanLocal managerService;
	private RolesServiceBeanLocal rolesService;

	private List<SelectItem> listOfManagers;
	private Long selectedManager;

	private List<SelectItem> listOfProjects;
	private Long selectedProject;

	private List<SelectItem> listOfRoles;
	private Long selectedRole;

	private boolean isLead;
	private boolean addNewUser;

	@PostConstruct
	public void init() {
		Long empId = (Long) JsfUtil
				.getSessionAttribute(EffortsConstants.EMP_ID);
		userInfo = getUserService().getUserInfo(empId);

		List<ManagerInfo> managersList = getManagerService().getAllManagers();
		List<SelectItem> managerItemList = new ArrayList<SelectItem>();
		managerItemList.add(new SelectItem("0", "Please Select a Manager"));
		for (ManagerInfo manager : managersList) {
			managerItemList.add(new SelectItem(manager.getId(), manager
					.getFname() + " " + manager.getLname()));
		}

		setListOfManagers(managerItemList);

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

		newUser = (UserInfo) JsfUtil
				.getRequestAttribute(EffortsConstants.MEMBER_INFO);
		if (newUser == null) {
			newUser = new UserInfo();
			addNewUser = true;
		} else {
			selectedManager = newUser.getManagerInfo().getId();
			selectedProject = newUser.getProjectInfo().getId();
			selectedRole = newUser.getRolesInfo().getId();
		}

	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public UserServiceBeanLocal getUserService() {
		try {
			userService = (UserServiceBeanLocal) EffortsServiceLocator
					.getLocalHome(EffortsServiceConstants.UserServiceConstant);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userService;
	}

	public void setUserService(UserServiceBeanLocal userService) {
		this.userService = userService;
	}

	public UserInfo getNewUser() {
		return newUser;
	}

	public void setNewUser(UserInfo newUser) {
		this.newUser = newUser;
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

	public String saveMember() {
		String result;

		ManagerInfo manager = managerService
				.getManagerById(getSelectedManager());

		newUser.setManagerInfo(manager);

		ProjectInfo project = projectService
				.getProjectById(getSelectedProject());

		newUser.setProjectInfo(project);

		RolesInfo role = rolesService.getRoleById(getSelectedRole());

		newUser.setRolesInfo(role);
		newUser.setUsrpassword(String.valueOf(newUser.getEmpid()));
		if (isLead) {
			newUser.setIsLead("1");
			ManagerInfo newManager = new ManagerInfo();
			newManager.setFname(newUser.getFname());
			newManager.setLname(newUser.getLname());
			newManager.setMailid(newUser.getMailid());
			newManager.setId(newUser.getEmpid());
			getManagerService().addManager(newManager);
		} else {
			newUser.setIsLead("0");
		}

		if (addNewUser) {

			getUserService().addMember(newUser);
			String userName = newUser.getFname() + " " + newUser.getLname();
			String managerName = newUser.getManagerInfo().getFname() + " "
					+ newUser.getManagerInfo().getLname();
			Object[] msgParams = new Object[] { userName,
					newUser.getRolesInfo().getName(),
					newUser.getProjectInfo().getName(), managerName };
			MailUtil.sendMessage(newUser.getMailid(), newUser.getManagerInfo()
					.getMailid(), "add_member_subject",
					new Object[] { userName }, "add_member_content", msgParams);
			MessageUtility.addSuccessMessage("user_add_success");
		} else {
			getUserService().updateMember(newUser);
			MessageUtility.addSuccessMessage("user_update_success");
		}

		result = NavigationOutCome.VIEW_MEMBERS;

		return result;
	}

	public String cancel() {

		return NavigationOutCome.VIEW_MEMBERS;

	}

	public boolean isLead() {
		return isLead;
	}

	public void setLead(boolean isLead) {
		this.isLead = isLead;
	}

	public List<UserInfo> getListOfMembers() {
		return getUserService().getListOfMembers();
	}

	public List<UserInfo> getManagerMembersList() {
		return getUserService().getManagerMembersList(userInfo.getEmpid());
	}

	public boolean isAddNewUser() {
		return addNewUser;
	}

	public void setAddNewUser(boolean addNewUser) {
		this.addNewUser = addNewUser;
	}

	}
