package spark.io;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.DriverManager;

public class SqlDataset {

    public static void write() throws ClassNotFoundException {

        String sql = "insert into sparkdb(course_title, is_paid, price, num_subscribers, num_lectures, level, content_duration, subject) values(?, ?, ?, ?, ?, ?, ?, ?)";
        Class.forName("org.postgresql.Driver");
        try(Connection conn = SqlDataSource.getConnection();
                
                PreparedStatement statement = conn.prepareStatement(sql);
                Statement rmStatement = conn.createStatement();
                ) {
            
            
            statement.setString(1, "xx");
            Boolean ex = true;
            statement.setBoolean(2, ex);
            statement.setInt(3, 10);
            statement.setInt(4, 5);
            statement.setInt(5, 6);
            statement.setString(6, "Novice");
            statement.setString(7, "1 hour");
            statement.setString(8, "Business");
            statement.addBatch();
            statement.executeBatch();
           
            System.out.println("Successfully wrote to database");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to write to database");
        }
    }
}