package spark.io;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class SqlDataset {
    public static SqlDataSource dataSource;

    public static void write() {

        dataSource = SqlDataSource.getInstance();
        String sql = "insert into sparkdb(course_title, is_paid, price, num_subscribers, num_lectures, level, content_duration, subject) values(?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = dataSource.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
                Statement rmStatement = conn.createStatement();) {
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
            System.out.println("Failed to write to database");
        }
    }
}