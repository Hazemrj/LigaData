package com.ligadata.dbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

//Class for database connection
public class ConnectionClass {
   
   private ConnectionInfo con;
   
   public ConnectionClass(ConnectionInfo con){
      this.con = con;
   }


    //connect to database using the jdbc URL and username/password
	public Connection getConnection() throws SQLException {
	try {
      Connection conn = null;
      //Class.forName(ConnectionInfo.getDriverClassName());
		conn = DriverManager.getConnection(con.getURL(), con.getUser(), con.getPass());
		return conn;
      
      }catch(Exception e)
      {e.printStackTrace();}

      return null;
	}  
}
