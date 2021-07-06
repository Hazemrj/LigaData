package com.ligadata;

import com.ligadata.beans.Fslash;
import com.ligadata.daos.PercDAO;
import com.ligadata.daos.PipeDAO;
import com.ligadata.daos.SurveyDAO;
import com.ligadata.daos.FslashDAO;
import com.ligadata.dbUtils.ConnectionClass;
import com.ligadata.dbUtils.ConnectionInfo;
import com.ligadata.util.ListFiles;

//test class
public class MainClass {
   static String fslashPath = "C:\\Users\\hazem\\OneDrive\\Desktop\\github\\Java\\fslash";
   //main method
	public static void main(String[] args) {
   
      String driverClassName = "com.mysql.jdbc.Driver";
	   String jdbcURL = "jdbc:mysql://localhost:3306/" + args[0];
	   String user = args[1];
	   String pass = args[2];
   
      // String file = "survey.csv";
      
      ConnectionInfo info = new ConnectionInfo(driverClassName, jdbcURL, user, pass);
      
      ConnectionClass c = new ConnectionClass(info);

      FslashDAO f = new FslashDAO(c);
      SurveyDAO s = new SurveyDAO(c);
      PercDAO perc = new PercDAO(c);
      PipeDAO pipe = new PipeDAO(c);

      //f.addRecord(new Fslash(), "test");
      
      //ConnectionClass.setConnectionInfo(c);
        f.addFiles(ListFiles.getReaderList(fslashPath));
        f.add(fslashPath, "fslashtable", "/");

	}
}