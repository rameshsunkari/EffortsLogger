package com.efforts.projects;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.efforts.model.ProjectInfo;
import com.efforts.model.UserInfo;
import com.efforts.utilities.EffortsConstants;
import com.efforts.utilities.JsfUtil;
import com.efforts.utilities.NavigationOutCome;

@ManagedBean
@SessionScoped
public class ProjectBean {
	
	private UserInfo userInfo;
	private ProjectInfo project;
	
	@PostConstruct
	public void init(){
		userInfo = (UserInfo) JsfUtil.getSessionAttribute(EffortsConstants.EMP_INFO);
		
	}
	
	public String addProjects(){
		return NavigationOutCome.ADD_PROJECTS;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public ProjectInfo getProject() {
		return project;
	}

	public void setProject(ProjectInfo project) {
		this.project = project;
	}

}
