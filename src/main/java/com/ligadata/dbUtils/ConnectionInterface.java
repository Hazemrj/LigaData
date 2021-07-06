package com.ligadata.dbUtils;

import com.ligadata.beans.Fslash;
import com.ligadata.beans.SuperCSV;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;

public interface ConnectionInterface {


   public Connection getConnection() throws SQLException;

   public void closeConnection();

}