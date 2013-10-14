package com.efforts.login;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.efforts.service.login.UserServiceBeanLocal;
import com.efforts.service.util.EffortsServiceConstants;
import com.efforts.test.hello.HelloWorldLocal;
import com.efforts.utilities.EffortsConstants;
import com.efforts.utilities.EffortsServiceLocator;
import com.efforts.utilities.JsfUtil;
import com.efforts.utilities.NavigationOutCome;

@ManagedBean
@SessionScoped
public class UserLoginBean {
	private static Logger logger = Logger.getLogger(UserLoginBean.class);

	private String userId;
	private String password;
	private Long empId;
	
	
//	@EJB
	private HelloWorldLocal helloService;
//	private HelloWorldLocal helloService = new HelloWorld();
	
//	@EJB
	private UserServiceBeanLocal userService;
	
//	private UserServiceBeanLocal userService = new UserServiceBean();
	
	@PostConstruct
	public void init(){
		
	}

	public String checkLogin() {
		logger.debug("Checking the user login in JSF");
		boolean result = getUserService().checkUserLogin(empId, password);
		if (result) {
			JsfUtil.addSessionAttribute(EffortsConstants.EMP_ID, empId);
			return NavigationOutCome.SUCCESS;
		}

		return NavigationOutCome.FAILURE;
	}

	public Long getEmpId() {
		return empId;
	}

	public HelloWorldLocal getHelloService() {
		try {
			helloService = (HelloWorldLocal) EffortsServiceLocator.getLocalHome(EffortsServiceConstants.HelloServiceConstant);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return helloService;
	}

	public String getPassword() {
		return password;
	}

	public String getUserId() {
		return userId;
	}

	public UserServiceBeanLocal getUserService() {
		try {
			userService = (UserServiceBeanLocal) EffortsServiceLocator.getLocalHome(EffortsServiceConstants.UserServiceConstant);
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userService;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public void setHelloService(HelloWorldLocal helloService) {
		this.helloService = helloService;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserService(UserServiceBeanLocal userService) {
		this.userService = userService;
	}

	public String showMessage() {
		logger.debug("Calling Service method for the hello world in JSF");
		return getHelloService().sayHello();
	}

}
