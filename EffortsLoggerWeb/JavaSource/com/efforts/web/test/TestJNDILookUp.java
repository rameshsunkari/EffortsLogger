package com.efforts.web.test;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.efforts.test.hello.HelloWorldLocal;

public class TestJNDILookUp {
	public static void main(String[] args) throws NamingException {

		Context context = null;
		try {
			Properties props = new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.jboss.naming.remote.client.InitialContextFactory");
			props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			props.put(Context.PROVIDER_URL, "remote://localhost:4447");
			props.put(Context.SECURITY_PRINCIPAL, "ramesh");
			props.put(Context.SECURITY_CREDENTIALS, "sunkari");
			props.put("jboss.naming.client.ejb.context", true);
			context = new InitialContext(props);
			System.out.println("\n\tGot initial Context: " + context);
			HelloWorldLocal obj = (HelloWorldLocal) context
					.lookup("ejb:/EffortsLoggerService//HelloWorld!com.efforts.test.hello.HelloWorldLocal");
			System.out.println(obj);
			obj.sayHello();

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			context.close();
		}
		

	}

}
