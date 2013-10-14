package com.efforts.action;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.naming.NamingException;

import com.efforts.model.Effortssubtype;
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
public class EffortsSubTypeBean {

	private UserInfo userInfo;
	private Effortssubtype subType;

	private UserServiceBeanLocal userService;
	private EffortsServiceBeanLocal effortsService;

	private List<SelectItem> listOfEffortsType;

	@PostConstruct
	public void init() {

		Long empId = (Long) JsfUtil
				.getSessionAttribute(EffortsConstants.EMP_ID);
		userInfo = getUserService().getUserInfo(empId);

	}

	public String addNewEffortsSubTypeBean() {
		return NavigationOutCome.ADD_EFFORTS_SUB_TYPE;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Effortssubtype getSubType() {
		return subType;
	}

	public void setSubType(Effortssubtype subType) {
		this.subType = subType;
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

	public List<SelectItem> getListOfEffortsType() {
		return listOfEffortsType;
	}

	public void setListOfEffortsType(List<SelectItem> listOfEffortsType) {
		this.listOfEffortsType = listOfEffortsType;
	}

	public List<Effortssubtype> getAllEffortsSubType() {
		return getEffortsService().getAllEffortssubtype();
	}

	public String viewEffortsSubType(Effortssubtype subType) {
		JsfUtil.addRequestAttribute(EffortsConstants.EFFORTS_SUBTYPE_INFO, subType);

		return NavigationOutCome.VIEW_EFFORTS_SUBTYPE_INFO;
	}

}
