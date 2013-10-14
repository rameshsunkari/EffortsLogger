package com.efforts.service.login;

import java.util.List;

import com.efforts.model.UserInfo;


public interface UserServiceBeanLocal {
	public boolean checkUserLogin(Long empId, String password);
	public UserInfo getUserInfo(Long empId);
	public void addMember(UserInfo user);
	public void updateMember(UserInfo user);
	public List<UserInfo> getListOfMembers();
	public List<UserInfo> getManagerMembersList(Long mgrId);

}
