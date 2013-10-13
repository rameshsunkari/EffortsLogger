package com.efforts.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.NamingException;

import com.efforts.model.RolesInfo;
import com.efforts.service.action.RolesServiceBeanLocal;
import com.efforts.service.util.EffortsServiceConstants;
import com.efforts.utilities.EffortsServiceLocator;
import com.efforts.utilities.MessageUtility;
import com.efforts.utilities.NavigationOutCome;

@ManagedBean
@RequestScoped
public class RolesInfoBean {

	// @EJB
	private RolesServiceBeanLocal rolesService;
	// private RolesServiceBeanLocal rolesService = new RolesServiceBean();
	private List<RolesInfo> listOfRoles = new ArrayList<RolesInfo>();
	private RolesInfo role = new RolesInfo();

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

	public void setListOfRoles(List<RolesInfo> listOfRoles) {
		this.listOfRoles = listOfRoles;
	}

	public RolesInfo getRole() {
		return role;
	}

	public void setRole(RolesInfo role) {
		this.role = role;
	}

	public String addRoleInfo() {
		String result;

		getRolesService().addRole(role);
		MessageUtility.addErrorMessage("roles_add_success");
		result = NavigationOutCome.VIEW_ROLES;

		return result;

	}

	public String cancel() {
		return NavigationOutCome.VIEW_ROLES;
	}

	public String addRole() {
		return NavigationOutCome.ADD_ROLES;
	}

}
