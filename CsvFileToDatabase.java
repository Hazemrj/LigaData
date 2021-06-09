import java.io.*;
import java.sql.*;
 
public class ConnectionClass { 

    static final String driverClassName = "com.mysql.jdbc.Driver";//
    static final String jdbcURL = "jdbc:mysql://localhost:3306/survey";//
    static final String username = "root";//
    static final String password = "1312";//
  
    static final String file = "survey.csv";//
    
    int batchSize = 20;//
 
    static final ConnectionClass connection = null;
    
    public class ConnectionClass() {
         try {
         
			   Class.forName(driverClassName);
		
            } catch (ClassNotFoundException e) 
              {e.printStackTrace();}
    }
    
    public Connection getConnection() throws SQLException {
		   Connection conn = null;
		   conn = DriverManager.getConnection(jdcbURL, username, password);
		   return conn;
	}
   
   public static ConnectionClass getInstance() {
   		 
           if (ConnectionClass == null) {
   		      ConnectionClass = new ConnectionClass();
   		  }
		      
           return ConnectionClass;
	 }
}
    // 
//         try {
//  
//             connection = DriverManager.getConnection(jdbcURL, username, password);
//             connection.setAutoCommit(false);
//             
//             PreparedStatement create = connection.prepareStatement("CREATE TABLE IF NOT EXISTS surveyTable(ID int NOT NULL AUTO_INCREMENT, name varchar(20), PRIMARY KEY(ID))");
// 			   create.executeUpdate();
//  
//             String sql = "INSERT INTO surveyTable (ID, name) VALUES (?, ?)";
//             PreparedStatement statement = connection.prepareStatement(sql);
//  
//             BufferedReader lineReader = new BufferedReader(new FileReader(file));
//             String lineText = null;
//  
//             int count = 0;
//  
//             lineReader.readLine();
//  
//             while ((lineText = lineReader.readLine()) != null) {
//                 String[] data = lineText.split(",");
//                 
//                 String ID = data[0];
//                 String name = data[1];
//  
//                 statement.setString(1, ID);
//                 statement.setString(2, name);
//  
//                 statement.addBatch();
//  
//                 if (count % batchSize == 0) {
//                     statement.executeBatch();
//                 }
//             }
//  
//             lineReader.close();
// 
//         }   catch (IOException ex) 
//             {ex.printStackTrace();} 
//             
//             catch (SQLException ex) 
//             {ex.printStackTrace();}
//             
//             finally {
//                try {
//                   connection.commit();
//                   connection.close();
//                }  
//                   catch (SQLException ex)
//                   {ex.printStackTrace();}
// 
//             }
//  
// }
// 