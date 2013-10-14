package com.efforts.test.hello;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

/**
 * Session Bean implementation class HelloWorld
 */
@Stateless
@Local(HelloWorldLocal.class)
public class HelloWorld implements HelloWorldLocal {
	private static Logger logger = Logger.getLogger(HelloWorld.class);
    /**
     * Default constructor. 
     */
    public HelloWorld() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String sayHello() {
		logger.debug("Calling Hello world service method");
		return "Hello World";
	}

}
