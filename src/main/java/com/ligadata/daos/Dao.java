package com.ligadata.daos;

import com.ligadata.dbUtils.ConnectionClass;
import com.ligadata.dbUtils.ConnectionInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class Dao<E> {

    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;
    private ConnectionClass con;



    public Connection getConnection() throws SQLException {
        Connection conn = con.getConnection();
        return conn;
    }

    public void closeConnection() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addRecord (E bean, String tableName) {
        Statement statement = null;
        try {

            //statement = getConnection().createStatement();
            statement.executeUpdate("insert into TableName (,,,) values ()");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addFiles(List<BufferedReader> readerList){

    }


    public void getRecordById(int id, String tableName, E x){

    }
}
