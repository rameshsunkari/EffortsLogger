package com.efforts.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.naming.NamingException;

import com.efforts.model.Effortssubtype;
import com.efforts.model.Effortstype;
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
	private Long selectedEffortType;

	@PostConstruct
	public void init() {

		Long empId = (Long) JsfUtil
				.getSessionAttribute(EffortsConstants.EMP_ID);
		userInfo = getUserService().getUserInfo(empId);

		subType = new Effortssubtype();

		List<Effortstype> effortsTypeList = getEffortsService()
				.getAllEffortstype();
		List<SelectItem> typeItemList = new ArrayList<SelectItem>();
		typeItemList.add(new SelectItem("0", "Please Select a Effort Type"));
		for (Effortstype type : effortsTypeList) {
			typeItemList.add(new SelectItem(type.getId(), type.getName()));
		}

		setListOfEffortsType(typeItemList);

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

	public Long getSelectedEffortType() {
		return selectedEffortType;
	}

	public void setSelectedEffortType(Long selectedEffortType) {
		this.selectedEffortType = selectedEffortType;
	}

	public String cancel() {
		return NavigationOutCome.VIEW_EFFORTS_SUB_TYPE;
	}

	public String addEffortsSubType() {

		Effortstype type = getEffortsService().getEffortTypeById(
				getSelectedEffortType());

		subType.setEffortstype(type);

		getEffortsService().addEffortsSubType(subType);

		return NavigationOutCome.VIEW_EFFORTS_SUB_TYPE;
	}

	public List<Effortssubtype> getAllEffortsSubType() {
		return getEffortsService().getAllEffortssubtype();
	}

}
