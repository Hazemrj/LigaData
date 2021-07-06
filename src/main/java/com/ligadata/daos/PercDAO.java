package com.ligadata.daos;

import com.ligadata.beans.Perc;
import com.ligadata.dbUtils.ConnectionClass;
import com.ligadata.dbUtils.ConnectionInterface;

import java.sql.*;
import java.io.*;

//Class for Data Access Object
public class PercDAO implements ConnectionInterface {

    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;
    private ConnectionClass con;

    //parametrized constructor
    public PercDAO(ConnectionClass con) {
        this.con = con;
    }

    //getting the connection
    @Override
    public Connection getConnection() throws SQLException {
        Connection conn = con.getConnection();
// 		conn = ConnectionClass.getInstance().getConnection();
        return conn;
    }

//    @Override
//    public <E extends SuperCSV> void add(E perc) {
//
//        Perc p = (Perc) perc;
//
//        try {
//            String queryString = "INSERT INTO percTable (ID, fname, bd) VALUES(?,?,?)";
//            connection = getConnection();
//            ptmt = connection.prepareStatement(queryString);
//
//            ptmt.setInt(1, p.getID());
//            ptmt.setString(2, p.getFirstName());
//            ptmt.setInt(3, p.getGrade());
//            ptmt.setString(4, p.getCountry());
//
//            ptmt.executeUpdate();
//
//            System.out.println("Data Added Successfully");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//
//                if (ptmt != null)
//                    ptmt.close();
//
//                closeConnection();
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//
//
//    }

    public void closeConnection(){
        try{
            if (connection != null)
                connection.close();
        }

        catch(SQLException e)
        {e.printStackTrace();}
    }

    //method to add values to the dapercase percle


    public void addPercFile(Perc perc) {

        try{

            String sql = "INSERT INTO percTable (ID, fname, grade, country) VALUES(?,?,?,?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(perc.getPercFile()));
            String lineText = null;

            int count = 0;

            int batchSize = 20;

            lineReader.readLine();

            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");

                String ID = data[0];
                String fname = data[1];
                String grade = data[2];
                String country = data[3];


                ptmt.setString(1, ID);
                ptmt.setString(2, fname);
                ptmt.setString(3, grade);
                ptmt.setString(4, country);


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
    public void update(Perc perc) {

        try {
            String queryString = "UPDATE percTable SET name=? WHERE ID=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);

            ptmt.setInt(1, perc.getID());
            ptmt.setString(2, perc.getFirstName());
            ptmt.setInt(3, perc.getGrade());
            ptmt.setString(4, perc.getCountry());
            ptmt.executeUpdate();

            System.out.println("percTable Updated Successfully");

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

    //method to add values to the table percTable
    public void delete(int ID) {

        try {
            String queryString = "DELETE FROM perctable WHERE ID=?";
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

    //method to get all values from the percTable
    public void findAll() {
        try {
            String queryString = "SELECT * FROM percTable";
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