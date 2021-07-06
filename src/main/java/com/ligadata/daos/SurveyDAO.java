package com.ligadata.daos;

import com.ligadata.beans.Survey;
import com.ligadata.dbUtils.ConnectionClass;
import com.ligadata.dbUtils.ConnectionInterface;

import java.sql.*;
import java.io.*;

//Class for Data Access Object
public class SurveyDAO implements ConnectionInterface {

    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;
    private ConnectionClass con;

    //parametrized constructor
    public SurveyDAO(ConnectionClass con) {
        this.con = con;
    }

    //getting the connection
    @Override
    public Connection getConnection() throws SQLException {
        Connection conn = con.getConnection();
// 		conn = ConnectionClass.getInstance().getConnection();
        return conn;
    }




    public void closeConnection(){
        try{
            if (connection != null)
                connection.close();
        }

        catch(SQLException e)
        {e.printStackTrace();}
    }

    //method to add values to the dapercase percle


    public void addpercFile(Survey survey) {

        try{

            String sql = "INSERT INTO surveyTable (ID, name) VALUES(?,?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(survey.getSurveyFile()));
            String lineText = null;

            int count = 0;

            int batchSize = 20;

            lineReader.readLine();

            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");

                String ID = data[0];
                String fname = data[1];
                String bd = data[2];


                ptmt.setString(1, ID);
                ptmt.setString(2, fname);
                ptmt.setString(3, bd);


                ptmt.addBatch();

                if (count % batchSize == 0) {
                    ptmt.executeBatch();
                }
            }

            lineReader.close();

        }   catch (IOException ex)
        {ex.printStackTrace();}

        catch (SQLException ex)
        {ex.printStackTrace();}

        finally {
            try {
                if (ptmt != null)
                    ptmt.close();
                closeConnection();
            }

            catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();

            }

        }
    }

    //method to update values to the dapercase percle
    public void update(Survey survey) {

        try {
            String queryString = "UPDATE surveyTable SET name=? WHERE ID=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);

            ptmt.setInt(1, survey.getID());
            ptmt.setString(2, survey.getName());
            ptmt.executeUpdate();

            System.out.println("surveyTable Updated Successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptmt != null)
                    ptmt.close();

                closeConnection();
            }

            catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    //method to add values to the dapercase percle
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

                closeConnection();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    //method to get all values from the percle
    public void findAll() {
        try {
            String queryString = "SELECT * FROM surveyTable";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);

            resultSet = ptmt.executeQuery();

            ResultSetMetaData md = resultSet.getMetaData();


            int columnNum = md.getColumnCount();
//			md.getColumnType()
            String result = "";
            while (resultSet.next()) {
                for (int i =0 ; i <= columnNum; i++){
                    String columnName = md.getColumnName(i);
                    result += columnName + ": " +resultSet.getString(i) + ", ";
                }
//				System.out.println("ID " + resultSet.getString("ID")
//						+ ", fname " + resultSet.getString("fname")
//                  + ", bd " + resultSet.getString("bd"));
                result = result.substring(0, result.length() -2);
                System.out.println(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (resultSet != null)
                    resultSet.close();

                if (ptmt != null)
                    ptmt.close();

                closeConnection();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}