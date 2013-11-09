package com.efforts.action;

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
import com.efforts.utilities.MessageUtility;
import com.efforts.utilities.NavigationOutCome;

@ManagedBean
@RequestScoped
public class RolesInfoDetailBean {

	private boolean newRole;
	// @EJB
	private RolesServiceBeanLocal rolesService;
	// private RolesServiceBeanLocal rolesService = new RolesServiceBean();
	private RolesInfo role;

	@PostConstruct
	private void init() {

		role = (RolesInfo) JsfUtil
				.getRequestAttribute(EffortsConstants.ROLE_INFO);
		if (role == null) {
			newRole = true;
			role = new RolesInfo();
		}
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

	public RolesInfo getRole() {
		return role;
	}

	public void setRole(RolesInfo role) {
		this.role = role;
	}

	public String saveRoleInfo() {
		String result;

		if (newRole) {
			getRolesService().addRole(role);
			MessageUtility.addSuccessMessage("roles_add_success");
		} else {
			getRolesService().updateRole(role);
			MessageUtility.addSuccessMessage("roles_update_success");
		}
		result = NavigationOutCome.VIEW_ROLES;

		return result;

	}

	public String cancel() {
		return NavigationOutCome.VIEW_ROLES;
	}

	public boolean isNewRole() {
		return newRole;
	}

	public void setNewRole(boolean newRole) {
		this.newRole = newRole;
	}

}
