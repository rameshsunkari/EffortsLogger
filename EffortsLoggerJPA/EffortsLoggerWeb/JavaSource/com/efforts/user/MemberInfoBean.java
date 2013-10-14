package com.efforts.user;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;

import com.efforts.model.UserInfo;
import com.efforts.service.login.UserServiceBeanLocal;
import com.efforts.service.util.EffortsServiceConstants;
import com.efforts.utilities.EffortsConstants;
import com.efforts.utilities.EffortsServiceLocator;
import com.efforts.utilities.JsfUtil;
import com.efforts.utilities.NavigationOutCome;

@ManagedBean
@SessionScoped
public class MemberInfoBean {

	private UserServiceBeanLocal userService;
	private UserInfo userInfo;

	private boolean isLead;

	@PostConstruct
	public void init() {
		Long empId = (Long) JsfUtil
				.getSessionAttribute(EffortsConstants.EMP_ID);
		userInfo = getUserService().getUserInfo(empId);
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

	public String addNewMember() {
		return NavigationOutCome.ADD_MEMBERS;
	}

	public List<UserInfo> getListOfMembers() {
		return getUserService().getListOfMembers();
	}

	public List<UserInfo> getManagerMembersList() {
		return getUserService().getManagerMembersList(userInfo.getEmpid());
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public boolean isLead() {
		return isLead;
	}

	public void setLead(boolean isLead) {
		this.isLead = isLead;
	}

	public String viewMemberInfo(UserInfo member) {
		JsfUtil.addRequestAttribute(EffortsConstants.MEMBER_INFO, member);
		return NavigationOutCome.VIEW_MEMBERS_INFO;
	}
}
