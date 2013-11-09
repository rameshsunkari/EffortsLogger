package com.efforts.action;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.NamingException;

import com.efforts.model.RolesInfo;
import com.efforts.service.action.RolesServiceBeanLocal;
import com.efforts.service.util.EffortsServiceConstants;
import com.efforts.utilities.EffortsConstants;
import com.efforts.utilities.EffortsServiceLocator;
import com.efforts.utilities.JsfUtil;
import com.efforts.utilities.NavigationOutCome;

@ManagedBean
@RequestScoped
public class RolesInfoBean {

	// @EJB
	private RolesServiceBeanLocal rolesService;

	// private RolesServiceBeanLocal rolesService = new RolesServiceBean();

	@PostConstruct
	private void init() {
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

	public List<RolesInfo> getListOfRoles() {
		return getRolesService().getAllRoles();
	}

	public String addRole() {
		return NavigationOutCome.ADD_ROLES;
	}

	public String viewRoleInfo(RolesInfo roleInfo) {
		JsfUtil.addRequestAttribute(EffortsConstants.ROLE_INFO, roleInfo);
		return NavigationOutCome.VIEW_ROLES_INFO;
	}

}
