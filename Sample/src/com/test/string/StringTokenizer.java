package com.test.string;

public class StringTokenizer {

	public static void main(String[] args) {
		String str = "UserServiceBean#com.efforts.service.login.UserServiceBeanLocal";
		
		String[] s1 = str.split("#");
		System.out.println(s1[0] + "\t" + s1[1]);

	}

}
