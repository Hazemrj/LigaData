import java.io.*;
import java.sql.*;
 
public class CsvFileToDatabase { 
 
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/survey";
        String username = "root";
        String password = "1312";
 
        String file = "survey.csv";
 
        int batchSize = 20;
 
        Connection connection = null;
 
        try {
 
            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);
            
            PreparedStatement create = connection.prepareStatement("CREATE TABLE IF NOT EXISTS surveyTable(ID int NOT NULL AUTO_INCREMENT, name varchar(20), PRIMARY KEY(ID))");
			   create.executeUpdate();
 
            String sql = "INSERT INTO surveyTable (ID, name) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
 
            BufferedReader lineReader = new BufferedReader(new FileReader(file));
            String lineText = null;
 
            int count = 0;
 
            lineReader.readLine();
 
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                
                String ID = data[0];
                String name = data[1];
 
                statement.setString(1, ID);
                statement.setString(2, name);
 
                statement.addBatch();
 
                if (count % batchSize == 0) {
                    statement.executeBatch();
                }
            }
 
            lineReader.close();
 
            
            //statement.executeBatch();
 
            connection.commit();
            connection.close();
 
        }   catch (IOException ex) 
            {ex.printStackTrace();} 
            
            catch (SQLException ex) {
            ex.printStackTrace();
 
               try {
                   connection.rollback();//
               } catch (SQLException e) 
                 {e.printStackTrace();}
            
            }
 
    }
}