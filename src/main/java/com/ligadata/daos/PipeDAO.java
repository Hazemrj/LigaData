package com.ligadata.daos;

import com.ligadata.beans.Fslash;
import com.ligadata.dbUtils.ConnectionClass;
import com.ligadata.util.ListFiles;

import java.sql.*;
import java.io.*;
import java.io.File;
import java.util.List;

//DAO for pipe
public class PipeDAO extends Dao<Fslash> {

    //attributes
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;
    Statement statement = null;
    private ConnectionClass con;

    //parametrized constructor
    public PipeDAO(ConnectionClass con) {
        this.con = con;
    }

//    //getting the connection
//    public Connection getConnection() throws SQLException {
//        Connection conn = con.getConnection();
//        return conn;
//    }
//

//    @Override
//    public void addRecord(Fslash fslash, String tableName){
//
//    }

    //method to read data files from the directory and insert into the database table
    public void add(String filePath, String tableName, String regex) {

        //ListFiles.getReaderList(filePath);

        try {

            String sql = "INSERT INTO tableName (id, fname, email, city) VALUES(?,?,?,?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(sql);

            //read csv file
            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));
            String lineText = null;

            int count = 0;

            int batchSize = 20;

            lineReader.readLine();

            //splitting the data from the csv and then inserting it to the database
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(regex);

                String ID = data[0];
                String fname = data[1];
                String email = data[2];
                String city = data[3];


                ptmt.setString(1, ID);
                ptmt.setString(2, fname);
                ptmt.setString(3, email);
                ptmt.setString(4, city);


                ptmt.addBatch();

                if (count % batchSize == 0) {
                    ptmt.executeBatch();
                }
            }

            lineReader.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
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

    public void addFiles(List<BufferedReader> readerList){

    }

//    public void closeConnection() {
//        try {
//            if (connection != null)
//                connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


    //method to update values to the database table


    //method to delete a record from the database table
    public void delete(int ID) {

        try {
            String queryString = "DELETE FROM pipeTable WHERE ID=?";
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

    //method to print the database table
    public void findAll() {
        try {
            String queryString = "SELECT * FROM pipeTable";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);

            resultSet = ptmt.executeQuery();

            ResultSetMetaData md = resultSet.getMetaData();


            int columnNum = md.getColumnCount();
//			md.getColumnType()
            String result = "";
            while (resultSet.next()) {
                for (int i = 0; i <= columnNum; i++) {
                    String columnName = md.getColumnName(i);
                    result += columnName + ": " + resultSet.getString(i) + ", ";
                }
                result = result.substring(0, result.length() - 2);
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





