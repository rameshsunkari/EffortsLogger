package com.efforts.action;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.efforts.model.UserInfo;
import com.efforts.service.login.UserServiceBeanLocal;
import com.efforts.utilities.EffortsConstants;
import com.efforts.utilities.JsfUtil;
import com.efforts.utilities.MessageUtility;
import com.efforts.utilities.NavigationOutCome;

@ManagedBean
@SessionScoped
public class UserActionBean {
	private UserInfo userInfo;

	@EJB
	private UserServiceBeanLocal userService;

	@PostConstruct
	public void init() {
		Long empId = (Long) JsfUtil
				.getSessionAttribute(EffortsConstants.EMP_ID);
		if (empId != null)
			userInfo = getUserService().getUserInfo(empId);
	}

	public String logEfforts() {
		return NavigationOutCome.LOG_EFFORTS;
	}

	public String viewEfforts() {
		return NavigationOutCome.VIEW_EFFORTS;
	}
	

	public String viewEffortsType() {
		return NavigationOutCome.VIEW_EFFORTS_TYPE;
	}
	

	public String viewEffortsSubType() {
		return NavigationOutCome.VIEW_EFFORTS_SUB_TYPE;
	}

	public String myAccount() {
		return NavigationOutCome.MY_ACCOUNT;
	}

	public String logout() {
//		JsfUtil.clearSessionAttribut(EffortsConstants.EMP_ID);
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		MessageUtility.addSuccessMessage("logout_success");
		return NavigationOutCome.LOGOUT;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public UserServiceBeanLocal getUserService() {
		return userService;
	}

	public void setUserService(UserServiceBeanLocal userService) {
		this.userService = userService;
	}

	public String members() {
		return NavigationOutCome.VIEW_MEMBERS;
	}

	public String projects() {
		JsfUtil.addSessionAttribute(EffortsConstants.EMP_INFO, userInfo);
		return NavigationOutCome.VIEW_PROJECTS;
	}

	public String roles() {
		return NavigationOutCome.VIEW_ROLES;
	}
}
