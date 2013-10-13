package com.efforts.service.action;

import java.util.List;

import javax.ejb.Local;

import com.efforts.model.ManagerInfo;

@Local
public interface ManagerInfoServiceBeanLocal {
	
	public List<ManagerInfo> getAllManagers();
	public void addManager(ManagerInfo manager);
	public ManagerInfo getManagerById(Long mgrId);

}
