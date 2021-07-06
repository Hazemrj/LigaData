package com.ligadata.dbUtils;

public class ConnectionInfo{

   private static String driverClassName;
	private String jdbcURL;
	private String user;
	private String pass;
  
   
   public ConnectionInfo(String driverClassName, String jdbcURL, String user, String pass)
   {
      this.driverClassName = driverClassName;
      this.jdbcURL = jdbcURL;
      this.user = user;
      this.pass = pass;
   }
   
   public static String getDriverClassName()
   {
      return driverClassName;
   }
   
   public String getURL()
   {
      return jdbcURL;
   }
   
   public String getUser()
   {
      return user;
   }
   
   public String getPass()
   {
      return pass;
   }
   
   public void setDriverClassName(String driverClassName)
   {
      this.driverClassName = driverClassName;
   }
   
   public void setURL(String jdbcURL)
   {
      this.jdbcURL = jdbcURL;
   }
   
   public void setUser(String user)
   {
      this.user = user;
   }
   
   public void setPass(String pass)
   {
      this.pass = pass;
   }
}
  