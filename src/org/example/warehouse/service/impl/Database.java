package org.example.warehouse.service.impl;

import org.example.warehouse.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

    // 获取当前版本号
    public String getCurrentVersion() {
        String currentVersion = null;
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT version FROM version_table LIMIT 1");
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                currentVersion = resultSet.getString("version");
            }
        } catch (SQLException e) {
            System.err.println("获取当前版本时发生错误：" + e.getMessage());
        }
        return currentVersion;
    }

    // 更新版本号
    public void updateVersion(String newVersion) {
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE version_table SET version = ?")) {
            statement.setString(1, newVersion);
            statement.executeUpdate();
            System.out.println("数据库中的版本号已更新为：" + newVersion);
        } catch (SQLException e) {
            System.err.println("更新数据库版本号时发生错误：" + e.getMessage());
        }
    }
}
