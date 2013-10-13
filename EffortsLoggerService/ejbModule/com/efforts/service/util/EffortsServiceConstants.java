package com.efforts.service.util;

import java.util.HashMap;

public class EffortsServiceConstants {
	
	public static final HashMap<String, String> serviceConstants = new HashMap<String, String>();
	
	private static final String UserService = "UserServiceBean#com.efforts.service.login.UserServiceBeanLocal";
	private static final String RolesService = "RolesServiceBean#com.efforts.service.action.RolesServiceBeanLocal";
	private static final String ManagerService = "ManagerInfoServiceBean#com.efforts.service.action.ManagerInfoServiceBeanLocal";
	private static final String ProjectsService = "ProjectInfoServiceBean#com.efforts.service.action.ProjectInfoServiceBeanLocal";
	private static final String EffortsService = "EffortsServiceBean#com.efforts.service.action.EffortsServiceBeanLocal";
	private static final String HelloService = "HelloWorld#com.efforts.test.hello.HelloWorldLocal";
	
	public static final String UserServiceConstant = "UserService";
	public static final String RolesServiceConstant = "RolesService";
	public static final String ProjectServiceConstant = "ProjectService";
	public static final String ManagerServiceConstant = "ManagerService";
	public static final String EffortsServiceConstant = "EffortsService";
	public static final String HelloServiceConstant = "HelloService";
	
	
	
	static{
		serviceConstants.put(UserServiceConstant, UserService);
		serviceConstants.put(RolesServiceConstant, RolesService);
		serviceConstants.put(ProjectServiceConstant, ProjectsService);
		serviceConstants.put(HelloServiceConstant, HelloService);
		serviceConstants.put(ManagerServiceConstant, ManagerService);
		serviceConstants.put(EffortsServiceConstant, EffortsService);
	}

}
