package com.efforts.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.naming.NamingException;

import com.efforts.mail.MailUtil;
import com.efforts.model.EffortsInfo;
import com.efforts.model.Effortssubtype;
import com.efforts.model.Effortstype;
import com.efforts.model.UserInfo;
import com.efforts.service.action.EffortsServiceBeanLocal;
import com.efforts.service.action.ManagerInfoServiceBeanLocal;
import com.efforts.service.login.UserServiceBeanLocal;
import com.efforts.service.util.EffortsServiceConstants;
import com.efforts.utilities.EffortsConstants;
import com.efforts.utilities.EffortsServiceLocator;
import com.efforts.utilities.JsfUtil;
import com.efforts.utilities.NavigationOutCome;

@ManagedBean
@RequestScoped
public class EffortsInfoDetailBean {

	private boolean newEfforts;

	private UserInfo userInfo;
	private EffortsInfo effortsInfo;

	private UserServiceBeanLocal userService;
	private EffortsServiceBeanLocal effortsService;
	private ManagerInfoServiceBeanLocal managerService;

	private List<SelectItem> listOfEffortsType;
	private Long selectedEffortType;

	private List<SelectItem> listOfEffortsSubType;
	private Long selectedEffortSubType;

	@PostConstruct
	public void init() {

		Long empId = (Long) JsfUtil
				.getSessionAttribute(EffortsConstants.EMP_ID);
		userInfo = getUserService().getUserInfo(empId);

		effortsInfo = (EffortsInfo) JsfUtil
				.getRequestAttribute(EffortsConstants.EFFORTS_INFO);

		List<Effortstype> effortsTypeList = getEffortsService()
				.getAllEffortstype();
		List<SelectItem> typeItemList = new ArrayList<SelectItem>();
		typeItemList.add(new SelectItem("0", "Please Select a Effort Type"));
		for (Effortstype type : effortsTypeList) {
			typeItemList.add(new SelectItem(type.getId(), type.getName()));
		}

		setListOfEffortsType(typeItemList);

		List<Effortssubtype> subTypeList = getEffortsService()
				.getAllEffortssubtype();
		List<SelectItem> subTypeItemList = new ArrayList<SelectItem>();
		subTypeItemList.add(new SelectItem("0", "Please Select a Effort Type"));
		for (Effortssubtype subType : subTypeList) {
			subTypeItemList.add(new SelectItem(subType.getId(), subType
					.getName()));
		}

		setListOfEffortsSubType(subTypeItemList);
		if (effortsInfo == null) {
			newEfforts = true;
			effortsInfo = new EffortsInfo();
		} else {
			selectedEffortSubType = effortsInfo.getEffortssubtype().getId();
			selectedEffortType = effortsInfo.getEffortstype().getId();
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

			e.printStackTrace();
		}

		return userService;
	}

	public void setUserService(UserServiceBeanLocal userService) {
		this.userService = userService;
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

	public List<SelectItem> getListOfEffortsSubType() {
		return listOfEffortsSubType;
	}

	public void setListOfEffortsSubType(List<SelectItem> listOfEffortsSubType) {
		this.listOfEffortsSubType = listOfEffortsSubType;
	}

	public Long getSelectedEffortSubType() {
		return selectedEffortSubType;
	}

	public void setSelectedEffortSubType(Long selectedEffortSubType) {
		this.selectedEffortSubType = selectedEffortSubType;
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

	public String cancel() {
		return NavigationOutCome.VIEW_EFFORTS;
	}

	public String saveEffortsInfo() {

		Effortssubtype subType = getEffortsService().getEffortssubtypeById(
				getSelectedEffortSubType());

		effortsInfo.setEffortssubtype(subType);
		effortsInfo.setEffortstype(subType.getEffortstype());

		effortsInfo.setManagerInfo(userInfo.getManagerInfo());
		effortsInfo.setUserInfo(userInfo);
		effortsInfo.setProjectInfo(userInfo.getProjectInfo());
		effortsInfo.setSubmitted_Date(new Date());
		if (newEfforts) {
			getEffortsService().addEffortsInfo(effortsInfo);
		} else {
			getEffortsService().updateEffortsInfo(effortsInfo);
		}

		String userName = userInfo.getFname() + " " + userInfo.getLname();
		// String managerName = userInfo.getManagerInfo().getFname() + " " +
		// userInfo.getManagerInfo().getLname();

		Object[] msgParams = new Object[] { userName,
				effortsInfo.getEffortdate(), effortsInfo.getDescription(),
				effortsInfo.getEffortstype().getName(),
				effortsInfo.getEffortssubtype().getName(),
				effortsInfo.getPoints(), effortsInfo.getSubmitted_Date(),
				effortsInfo.getRemarks() };
		MailUtil.sendMessage(userInfo.getMailid(), userInfo.getManagerInfo()
				.getMailid(), "add_efforts_subject", new Object[] { effortsInfo
				.getEffortdate() }, "add_efforts_content", msgParams);

		return NavigationOutCome.VIEW_EFFORTS;
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

	public ManagerInfoServiceBeanLocal getManagerService() {
		try {
			managerService = (ManagerInfoServiceBeanLocal) EffortsServiceLocator
					.getLocalHome(EffortsServiceConstants.ManagerServiceConstant);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return managerService;
	}

	public void setManagerService(ManagerInfoServiceBeanLocal managerService) {
		this.managerService = managerService;
	}

	public boolean isNewEfforts() {
		return newEfforts;
	}

	public void setNewEfforts(boolean newEfforts) {
		this.newEfforts = newEfforts;
	}

}
