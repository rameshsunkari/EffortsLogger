package com.efforts.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.NamingException;

import com.efforts.model.EffortsInfo;
import com.efforts.model.UserInfo;
import com.efforts.service.action.EffortsServiceBeanLocal;
import com.efforts.service.login.UserServiceBeanLocal;
import com.efforts.service.util.EffortsServiceConstants;
import com.efforts.utilities.EffortsConstants;
import com.efforts.utilities.EffortsServiceLocator;
import com.efforts.utilities.FileUtil;
import com.efforts.utilities.JsfUtil;
import com.efforts.utilities.NavigationOutCome;

@ManagedBean
@RequestScoped
public class EffortsInfoBean extends ListInfoPage<EffortsInfo> {

	private UserInfo userInfo;
	private EffortsInfo effortsInfo;

	private UserServiceBeanLocal userService;
	private EffortsServiceBeanLocal effortsService;
	private StringBuilder outputBuffer;

	@PostConstruct
	public void init() {

		Long empId = (Long) JsfUtil
				.getSessionAttribute(EffortsConstants.EMP_ID);
		userInfo = getUserService().getUserInfo(empId);

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

	public List<EffortsInfo> getEmpEffortsList() {
		return getEffortsService().getAllEmpEfforts(userInfo.getEmpid());
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

	public String viewEffortsInfo(EffortsInfo effortsInfo) {
		JsfUtil.addRequestAttribute(EffortsConstants.EFFORTS_INFO, effortsInfo);

		return NavigationOutCome.VIEW_EFFORTS_INFO;

	}

	@Override
	public List<EffortsInfo> findEntityList(boolean all, int maxResults,
			int firstResult) {

		return getEffortsService().getAllEmpEfforts(userInfo.getEmpid(), all,
				maxResults, firstResult);
	}

	@Override
	public EffortsInfo findEffortsInfo(Long id) {
		return getEffortsService().getEffortsInfoById(id);
	}

	public void exportToPDF() throws IOException {
		FileUtil.export(getOutputBuffer().toString().getBytes(),
				"application/pdf", ".pdf");
	}

	public void exportToExcel() throws IOException {
		FileUtil.export(getOutputBuffer().toString().getBytes(),
				"application/vnd.ms-excel", ".xls");
	}

	public StringBuilder getOutputBuffer() {
		outputBuffer = new StringBuilder();
		outputBuffer
				.append("<table><tr><th>Efforts Id</th><th>Effort Type </th><th>Effort Sub Type</th><th>Description</th><th>Effort Date</th><th>Effort Submitted On</th><th>Hours Spent</th><th>Remarks</th></tr>");
		for (EffortsInfo efforts : getEmpEffortsList()) {
			outputBuffer.append("<tr>");
			outputBuffer.append("<td>").append(efforts.getId()).append("</td>");
			outputBuffer.append("<td>").append(efforts.getEffortstype().getName())
					.append("</td>");
			outputBuffer.append("<td>").append(efforts.getEffortssubtype().getName())
					.append("</td>");
			outputBuffer.append("<td>").append(efforts.getDescription())
					.append("</td>");
			outputBuffer.append("<td>").append(efforts.getEffortdate())
					.append("</td>");
			outputBuffer.append("<td>").append(efforts.getSubmitted_Date())
					.append("</td>");
			outputBuffer.append("<td>").append(efforts.getPoints())
					.append("</td>");
			outputBuffer.append("<td>").append(efforts.getRemarks())
					.append("</td>");
			outputBuffer.append("</tr>");
		}
		outputBuffer.append("</table>");

		return outputBuffer;
	}

	public void setOutputBuffer(StringBuilder outputBuffer) {
		this.outputBuffer = outputBuffer;
	}

}
