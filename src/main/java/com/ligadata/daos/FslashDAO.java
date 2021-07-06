package com.ligadata.daos;

import com.ligadata.beans.Fslash;
import com.ligadata.dbUtils.ConnectionClass;
import com.ligadata.util.ListFiles;

import java.sql.*;
import java.io.*;
import java.io.File;
import java.util.List;

//Class for Data Access Object
public class FslashDAO extends Dao<Fslash> {

    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;
    Statement statement = null;
    private ConnectionClass con;

    //parametrized constructor
    public FslashDAO(ConnectionClass con) {
        this.con = con;
    }

    //getting the connection
//    @Override
    public Connection getConnection() throws SQLException {
        Connection conn = con.getConnection();
        return conn;
    }
//

//    @Override
//    public void addRecord(Fslash fslash, String tableName){
//
//    }

    public void add(String filePath, String tableName, String regex) {

        //ListFiles.getReaderList(filePath);

            try {

                String sql = "INSERT INTO tableName (id, fname, bd) VALUES(?,?,?)";
                connection = getConnection();
                ptmt = connection.prepareStatement(sql);

                BufferedReader lineReader = new BufferedReader(new FileReader((File) ListFiles.getReaderList(filePath)));
                String lineText = null;

                int count = 0;

                int batchSize = 20;

                lineReader.readLine();

                while ((lineText = lineReader.readLine()) != null) {
                    String[] data = lineText.split(regex);

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

    public void closeConnection() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //method to update values to the database table
    public void update(Fslash fslash) {

        try {
            String queryString = "UPDATE tabTable SET fname=? WHERE ID=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);

            ptmt.setInt(1, fslash.getID());
            ptmt.setString(2, fslash.getFirstName());
            ptmt.executeUpdate();

            System.out.println("Table Updated Successfully");

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

    //method to add values to the database table
    public void delete(int ID) {

        try {
            String queryString = "DELETE FROM fslashTable WHERE ID=?";
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

    //method to get all values from the table
    public void findAll() {
        try {
            String queryString = "SELECT * FROM flashTable";
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
//				System.out.println("ID " + resultSet.getString("ID")
//						+ ", fname " + resultSet.getString("fname")
//                  + ", bd " + resultSet.getString("bd"));
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





