package com.ligadata.daos;

import com.ligadata.beans.Pipe;
import com.ligadata.dbUtils.ConnectionClass;
import com.ligadata.dbUtils.ConnectionInterface;

import java.sql.*;
import java.io.*;

//Class for Data Access Object
public class PipeDAO implements ConnectionInterface {

    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;
    private ConnectionClass con;

    //parametrized constructor
    public PipeDAO(ConnectionClass con) {
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
//    public <E extends SuperCSV> void add(E pipe) {
//
//        Pipe p = (Pipe) pipe;
//
//        try {
//            String queryString = "INSERT INTO pipetable (ID, fname, bd) VALUES(?,?,?)";
//            connection = getConnection();
//            ptmt = connection.prepareStatement(queryString);
//
//            ptmt.setInt(1, p.getAge());
//            ptmt.setString(2, p.getFirstName());
//            ptmt.setString(3, p.getLastName());
//            ptmt.setString(4, p.getEmail());
//            ptmt.setString(5, p.getCity());
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

    //method to add values to the dapipease pipele


    public void addpipeFile(Pipe pipe) {

        try{

            String sql = "INSERT INTO pipetable (ID, fname, bd) VALUES(?,?,?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(pipe.getPipeFile()));
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

    //method to update values to the dapipease pipele


    //method to add values to the dapipease pipele
    public void delete(int age) {

        try {
            String queryString = "DELETE FROM pipetable WHERE age=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);

            ptmt.setInt(1, age);
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



}