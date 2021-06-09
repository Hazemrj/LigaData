import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;

public class SurveyDAO {
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;

	public SurveyDAO() {

	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionClass.getInstance().getConnection();
		return conn;
	}

	public void add(Survey survey) {
		try {
			String queryString = "INSERT INTO SurveyTable (ID, Name) VALUES(?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, survey.getID());
			ptmt.setString(2, survey.getName());
			ptmt.executeUpdate();
			System.out.println("Data Added Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				
            if (ptmt != null)
					 ptmt.close();
                
				if (connection != null)
					connection.close();
               
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
   
  //  public void addFile(String file) {
// 		   
//          try{
//             
//             String sql = "INSERT INTO surveyTable (ID, name) VALUES (?, ?)";
//             ptmt = connection.prepareStatement(sql);
//             
//             BufferedReader lineReader = new BufferedReader(new FileReader(file));
//             String lineText = null;
//  
//             int count = 0;
//             
//             int batchSize = 20;
//  
//             lineReader.readLine();
//  
//             while ((lineText = lineReader.readLine()) != null) {
//                 String[] data = lineText.split(",");
//                 
//                 String ID = data[0];
//                 String name = data[1];
//  
//                 ptmt.setString(1, ID);
//                 ptmt.setString(2, name);
//  
//                 ptmt.addBatch();
//  
//                 if (count % batchSize == 0) {
//                     ptmt.executeBatch();
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
// 				if (ptmt != null)
// 					ptmt.close();
// 				if (connection != null)
// 					connection.close();
// 			}
// 
// 			catch (SQLException e) {
// 				e.printStackTrace();
// 			} catch (Exception e) {
// 				e.printStackTrace();
// 
// 			}
// 
// 	   }
//    }
   

	public void update(Survey survey) {

		try {
			String queryString = "UPDATE surveyTable SET Name=? WHERE ID=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, survey.getID());
			ptmt.setString(2, survey.getName());
			ptmt.executeUpdate();
			System.out.println("Table Updated Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			}

			catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}

	public void delete(int ID) {

		try {
			String queryString = "DELETE FROM surveyTable WHERE ID=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, ID);
			ptmt.executeUpdate();
			System.out.println("Data deleted Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public void findAll() {
		try {
			String queryString = "SELECT * FROM surveyTable";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();
			while (resultSet.next()) {
				System.out.println("ID " + resultSet.getInt("ID")
						+ ", Name " + resultSet.getString("Name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}