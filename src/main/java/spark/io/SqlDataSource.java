package spark.io;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlDataSource {
    
    private static String url;
	private static String user;
	private static String password;

	static {
		url = "jdbc:postgresql://18.188.248.154:5432/sparkdb";
		user = "sparkdb";
		password = "sparkdb";
	}

	private SqlDataSource() {
	}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}