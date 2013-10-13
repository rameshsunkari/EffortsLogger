package com.efforts.web.test;

import com.efforts.mail.MailUtil;

public class TestMessage {

	public static void main(String[] args) {
		String msgKey = "add_efforts_content";
		String subKey = "add_efforts_subject";
		Object[] subParams = new Object[] { "Tom", "Dick" };
		Object[] msgParams = new Object[] { "Tom", "Dick" };
		
		MailUtil.sendMessage( "ramesh.sunkari@oracle.com", "ramesh.sunkari@oracle.com", subKey, subParams, msgKey, msgParams);

	}

}
