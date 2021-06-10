import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Class for database connection
public class ConnectionClass {

   //initializing the database server attributes
	String driverClassName = "com.mysql.cj.jdbc.Driver";
	String jdbcURL = "jdbc:mysql://localhost:3306/survey";
	String user = "root";
	String pass = "1312";
   
   String file = "survey.csv";
   
   int count = 0;

	private static ConnectionClass connectionClass = null;
   
   //getting an instance of the class
	private ConnectionClass() {
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
   
   //connect to databse using the jdbc URL and username/password
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(jdbcURL, user, pass);
		return conn;
	}  

   //method for using the instance of the class
	public static ConnectionClass getInstance() {
		if (connectionClass == null) {
			connectionClass = new ConnectionClass();
		}
		return connectionClass;
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