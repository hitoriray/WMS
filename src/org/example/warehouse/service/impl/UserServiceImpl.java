package org.example.warehouse.service.impl;

import org.example.warehouse.dao.UserDao;
import org.example.warehouse.service.UserService;
import org.example.warehouse.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    @Override
    public boolean yanzhengadmin(UserDao userDao) {
        String username = userDao.getName();
        String pwd1 = userDao.getPwd();
        String sql = "select pwd from users where name=?";
        Connection conn = null;
        PreparedStatement ps = null;
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                String pwd = resultSet.getString(1);
                System.out.println(pwd);
                if (pwd1.equals(pwd)) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }


    @Override
    public boolean yanzhengIDnumber(String IDnumber, String name) {
//        String username= userDao.getName();
//        String pwd1= userDao.getPwd();
        String sql = "select IDnumber from userinfo where name=?";
        Connection conn = null;
        PreparedStatement ps = null;
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString(1);
                System.out.println("iiiiiiiiiiiiii" + id);
                System.out.println("IDnumber" + IDnumber);
//                System.out.println(pwd);
                if (id.equals(IDnumber)) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    @Override
    public boolean yanzhengOldpwd(String oldpwd) {
        String sql = "select pwd from users where pwd=?";
        Connection conn = null;
        PreparedStatement ps = null;
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, oldpwd);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                String pwd = resultSet.getString(1);
//                System.out.println(pwd);
                if (oldpwd.equals(pwd)) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    @Override
    public boolean yanzhengid(String id, String type) {
        String sql = "select id,type from warehouse where id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                String id1 = resultSet.getString(1);
                String type1 = resultSet.getString(2);
                if (id1.equals(id) && type1.equals(type)) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    @Override
    public boolean yanzhengname(String name) {
        String sql = "select name from userinfo where name=?";
        Connection conn = null;
        PreparedStatement ps = null;
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                String name1 = resultSet.getString(1);
                System.out.println(name);
                if (name1.equals(name)) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    @Override
    public String selectID(String name) {
        String sql = "select IDnumber from userinfo where name=?";
        Connection conn = null;
        PreparedStatement ps = null;
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString(1);
                return id;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;

    }


}
