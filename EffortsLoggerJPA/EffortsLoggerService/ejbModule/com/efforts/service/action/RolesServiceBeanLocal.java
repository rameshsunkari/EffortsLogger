package com.efforts.service.action;

import java.util.List;

import javax.ejb.Local;

import com.efforts.model.RolesInfo;

@Local
public interface RolesServiceBeanLocal {
	
	public List<RolesInfo> getAllRoles();
	public void addRole(RolesInfo role);
	public void updateRole(RolesInfo role);
	public RolesInfo getRoleById(Long roleId);

}
