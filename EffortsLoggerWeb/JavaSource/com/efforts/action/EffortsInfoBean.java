package com.efforts.action;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.NamingException;

import com.efforts.model.EffortsInfo;
import com.efforts.model.UserInfo;
import com.efforts.service.action.EffortsServiceBeanLocal;
import com.efforts.service.login.UserServiceBeanLocal;
import com.efforts.service.util.EffortsServiceConstants;
import com.efforts.utilities.EffortsConstants;
import com.efforts.utilities.EffortsServiceLocator;
import com.efforts.utilities.JsfUtil;
import com.efforts.utilities.NavigationOutCome;

@ManagedBean
@RequestScoped
public class EffortsInfoBean {

	private UserInfo userInfo;
	private EffortsInfo effortsInfo;

	private UserServiceBeanLocal userService;
	private EffortsServiceBeanLocal effortsService;

	@PostConstruct
	public void init() {

		Long empId = (Long) JsfUtil
				.getSessionAttribute(EffortsConstants.EMP_ID);
		userInfo = getUserService().getUserInfo(empId);

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

			e.printStackTrace();
		}

		return userService;
	}

	public void setUserService(UserServiceBeanLocal userService) {
		this.userService = userService;
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

	public List<EffortsInfo> getEmpEffortsList() {
		return getEffortsService().getAllEmpEfforts(userInfo.getEmpid());
	}

	public String addNewEffortsInfo() {
		return NavigationOutCome.ADD_EFFORTS;
	}

	public EffortsInfo getEffortsInfo() {
		return effortsInfo;
	}

	public void setEffortsInfo(EffortsInfo effortsInfo) {
		this.effortsInfo = effortsInfo;
	}

	public String viewEffortsInfo(EffortsInfo effortsInfo) {
		JsfUtil.addRequestAttribute(EffortsConstants.EFFORTS_INFO, effortsInfo);

		return NavigationOutCome.VIEW_EFFORTS_INFO;

	}
}
