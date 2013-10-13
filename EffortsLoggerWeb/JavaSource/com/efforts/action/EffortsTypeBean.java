package com.efforts.action;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.NamingException;

import com.efforts.model.Effortstype;
import com.efforts.service.action.EffortsServiceBeanLocal;
import com.efforts.service.util.EffortsServiceConstants;
import com.efforts.utilities.EffortsConstants;
import com.efforts.utilities.EffortsServiceLocator;
import com.efforts.utilities.JsfUtil;
import com.efforts.utilities.NavigationOutCome;

@ManagedBean
@RequestScoped
public class EffortsTypeBean {

	private EffortsServiceBeanLocal effortsService;

	@PostConstruct
	public void init() {
	}

	public String addNewEffortsType() {
		return NavigationOutCome.ADD_EFFORTS_TYPE;
	}

	public List<Effortstype> getAllEffortstypes() {
		return getEffortsService().getAllEffortstype();
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

	public String viewEffortsTypeInfo(Effortstype typeBean) {
		JsfUtil.addRequestAttribute(EffortsConstants.EFFORTS_TYPE_INFO,
				typeBean);
		return NavigationOutCome.VIEW_EFFORTS_TYPE_INFO;
	}
}
