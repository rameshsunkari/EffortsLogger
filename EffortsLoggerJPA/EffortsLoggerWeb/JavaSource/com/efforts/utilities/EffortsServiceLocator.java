package com.efforts.utilities;

import java.net.URL;

import javax.ejb.EJBHome;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.mail.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;

import com.efforts.service.util.EffortsServiceConstants;

public class EffortsServiceLocator {
	private static final String appName = "app";
	private static final String MODULE = "EffortsLoggerService";
	private static InitialContext ic;

	public EffortsServiceLocator() {
		try {
			ic = new InitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static Object lookup(String jndiName) throws NamingException {
		return getIc()
				.lookup(jndiName);
	}

	/**
	 * will get the ejb Local home factory. clients need to cast to the type of
	 * EJBHome they desire
	 * 
	 * @return the Local EJB Home corresponding to the homeName
	 */
	public static Object getLocalHome(String serviceName)
			throws NamingException {		
		String classInfo = EffortsServiceConstants.serviceConstants.get(serviceName);
		String[] s1 = classInfo.split("#");
		String ejbName = s1[0];
		String viewClassName = s1[1];
//		String distinctName = "";
		String jndiHomeName = "java:" + appName + "/" + MODULE + "/"
				+ ejbName + "!" + viewClassName;

		return lookup(jndiHomeName);
	}

	/**
	 * Returns the package name of the class supplied as argument
	 * 
	 * @param c
	 * @return
	 */
	private static String getPackageName(Class c) {
		String fullyQualifiedName = c.getName();
		int lastDot = fullyQualifiedName.lastIndexOf('.');
		if (lastDot == -1) {
			return "";
		}
		return fullyQualifiedName.substring(0, lastDot);
	}

	/**
	 * will get the ejb Remote home factory. clients need to cast to the type of
	 * EJBHome they desire
	 * 
	 * @return the EJB Home corresponding to the homeName
	 */
	public EJBHome getRemoteHome(String jndiHomeName, Class className)
			throws NamingException {
		Object objref = lookup(jndiHomeName);
		return (EJBHome) PortableRemoteObject.narrow(objref, className);
	}

	/**
	 * This method helps in obtaining the jms connection factory
	 * 
	 * @return the factory for obtaining jms connection
	 */
	public ConnectionFactory getConnectionFactory(String connFactoryName)
			throws NamingException {
		return (ConnectionFactory) lookup(connFactoryName);
	}

	/**
	 * This method obtains the topc itself for a caller
	 * 
	 * @return the Topic Destination to send messages to
	 */
	public Destination getDestination(String destName) throws NamingException {
		return (Destination) lookup(destName);
	}

	/**
	 * This method obtains the datasource itself for a caller
	 * 
	 * @return the DataSource corresponding to the name parameter
	 */
	public DataSource getDataSource(String dataSourceName)
			throws NamingException {
		return (DataSource) lookup(dataSourceName);
	}

	/**
	 * This method obtains the E-mail session itself for a caller
	 * 
	 * @return the Session corresponding to the name parameter
	 */
	public Session getSession(String sessionName) throws NamingException {
		return (Session) lookup(sessionName);
	}

	/**
	 * @return the URL value corresponding to the env entry name.
	 */
	public URL getUrl(String envName) throws NamingException {
		return (URL) lookup(envName);
	}

	/**
	 * @return the boolean value corresponding to the env entry
	 */
	public boolean getBoolean(String envName) throws NamingException {
		Boolean bool = (Boolean) lookup(envName);
		return bool.booleanValue();
	}

	/**
	 * @return the String value corresponding to the env entry name.
	 */
	public String getString(String envName) throws NamingException {
		return (String) lookup(envName);
	}

	public static InitialContext getIc() {
		if (ic == null){
			try {
				ic = new InitialContext();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ic;
	}

	public static void setIc(InitialContext ic) {
		EffortsServiceLocator.ic = ic;
	}
}
