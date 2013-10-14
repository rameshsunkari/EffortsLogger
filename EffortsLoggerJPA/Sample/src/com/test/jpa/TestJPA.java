package com.test.jpa;

import com.test.jdbc.DBConnection;


public class TestJPA {

	/*public static void main(String[] args) {
		
		DBConnection con = null;
		
		Class obj = con.getClass();
		System.out.println(obj.getPackage());
	}
*/
	public static String getPackageName(Class c) {
	String fullyQualifiedName = c.getName();
	System.out.println(c.getName());
    int lastDot = fullyQualifiedName.lastIndexOf('.');
    if (lastDot == -1) {
      return "";
    }
    return fullyQualifiedName.substring(0, lastDot);
  }

  public static void main(String[] args) {
	DBConnection con = new DBConnection();
	System.out.println(con.getClass());
    System.out.println(getPackageName(DBConnection.class));
  }
}
