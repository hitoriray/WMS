package org.example.warehouse.utils;

import java.sql.*;

public class JDBCUtil {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/wms?useSSL=false&serverTimezone=Asia/Shanghai",
                    "root", "Zhz.0220");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Connection conn, Statement statement, ResultSet resultSet) throws SQLException {
        if (conn != null) {
            conn.close();
            conn = null;
        }
        if (statement != null) {
            statement.close();
            statement = null;
        }
        if (resultSet != null) {
            resultSet.close();
            resultSet = null;
        }

    }
}
