package com.efforts.utilities;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author rsunkari
 * 
 */
public class JsfUtil {

	/**
	 * Method to add a session attribute value
	 * 
	 * @param attName
	 * @param value
	 */
	public static void addSessionAttribute(String attName, Object value) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		HttpSession httpSession = request.getSession(false);
		httpSession.setAttribute(attName, value);

	}

	/**
	 * Returns the session attribute with the attribute name
	 * 
	 * @param attName
	 * @return
	 */
	public static Object getSessionAttribute(String attName) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		HttpSession httpSession = request.getSession(false);

		return httpSession.getAttribute(attName);
	}
	
	/**
	 * Clears the Session Attribute
	 * 
	 * @param attName
	 */
	public static void clearSessionAttribut(String attName){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		HttpSession httpSession = request.getSession(false);

		httpSession.removeAttribute(attName);;
	}

	/**
	 * Method to add a request attribute value
	 * 
	 * @param attName
	 * @param value
	 */
	public static void addRequestAttribute(String attName, Object value) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		request.setAttribute(attName, value);

	}

	/**
	 * Returns the request attribute with the attribute name
	 * 
	 * @param attName
	 * @return
	 */
	public static Object getRequestAttribute(String attName) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		return request.getAttribute(attName);
	}

	/**
	 *  Clears the Request Attribute
	 * @param attName
	 */
	
	public static void clearRequestAttribut(String attName){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		HttpSession httpSession = request.getSession(false);

		httpSession.removeAttribute(attName);;
	}
	
	  public static String getRequestParameter(String key) {
	        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
	    }

	    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
	        String theId = JsfUtil.getRequestParameter(requestParameterName);
	        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
	    }
}
