package org.example.warehouse.service.impl;

import org.example.warehouse.dao.UserDao;
import org.example.warehouse.dao.warehouseDao;
import org.example.warehouse.service.UserService;
import org.example.warehouse.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public boolean verifyAdmin(UserDao userDao) {
        String sql = "select pwd from users where name=?";
        String username = userDao.getName();
        String pwd = userDao.getPwd();
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String rightPwd = rs.getString(1);
                return pwd.equals(rightPwd);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean verifyIDnumberByName(String IDnumber, String name) {
        String sql = "select IDnumber from userinfo where name=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString(1);
                return id.equals(IDnumber);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean verifyPwd(String oldpwd) {
        String sql = "select pwd from users where pwd=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, oldpwd);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                String pwd = resultSet.getString(1);
                return oldpwd.equals(pwd);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean verifyId(String id, String type) {
        String sql = "select id,type from warehouse where id=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                String id1 = resultSet.getString(1);
                String type1 = resultSet.getString(2);
                return id1.equals(id) && type1.equals(type);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean verifyName(String name) {
        String sql = "select name from userinfo where name=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                String name1 = resultSet.getString(1);
                return name1.equals(name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public String selectID(String name) {
        String sql = "select IDnumber from userinfo where name=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
