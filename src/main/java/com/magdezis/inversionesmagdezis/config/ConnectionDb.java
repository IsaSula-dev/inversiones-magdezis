package main.java.com.magdezis.inversionesmagdezis.config;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionDb {
    
    private static Connection conn;

    private ConnectionDb() {
    }
    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(CredentialsDb.URL_DB, CredentialsDb.USER_DB, CredentialsDb.PASS_DB);
        }
        return conn;
    }

   
    
}
