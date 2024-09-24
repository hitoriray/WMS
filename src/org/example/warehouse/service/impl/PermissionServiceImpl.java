package org.example.warehouse.service.impl;

import org.example.warehouse.dao.PermissionDao;
import org.example.warehouse.service.PermissionService;
import org.example.warehouse.utils.JDBCUtil;
import org.example.warehouse.view.LoginView;
import org.example.warehouse.view.MainView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PermissionServiceImpl implements PermissionService {
    private LoginView loginView;
    private MainView mainView;

    public PermissionServiceImpl(MainView mainView, LoginView loginView) {
        this.loginView = loginView;
        this.mainView = mainView;
    }

    public int yanzhengInquire(PermissionDao permissionDao) {
        String username = loginView.getUserTxt().getText();
        String sql = "select inquire from permissions where name=?";
        Connection conn = null;
        PreparedStatement ps = null;
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int inquire = rs.getInt(1);
                System.out.println(inquire);
                return inquire;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int yanzhengInbound(PermissionDao permissionDao) {
        String username = loginView.getUserTxt().getText();
        System.out.println(username);
        String sql = "select inbound from permissions where name=?";
        Connection conn = null;
        PreparedStatement ps = null;
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int in = rs.getInt(1);
                System.out.println(in);
                return in;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int yanzhengOutbound(PermissionDao permissionDao) {
        String username = loginView.getUserTxt().getText();
        String sql = "select Outbound from permissions where name=?";
        Connection conn;
        PreparedStatement ps;
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int outbound = rs.getInt(1);
                return outbound;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int yanzhengManager(PermissionDao permissionDao) {
        String username = loginView.getUserTxt().getText();
        String sql = "select manager from permissions where name=?";
        Connection conn;
        PreparedStatement ps;
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int manager = rs.getInt(1);
                return manager;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int yanzhengFile(PermissionDao permissionDao) {
        String username = loginView.getUserTxt().getText();
        String sql = "select file from permissions where name=?";
        Connection conn;
        PreparedStatement ps;
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int file = rs.getInt(1);
                return file;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int yanzhengPerson(PermissionDao permissionDao) {
        String username = loginView.getUserTxt().getText();
        String sql = "select person from permissions where name=?";
        Connection conn;
        PreparedStatement ps;
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int file = rs.getInt(1);
                return file;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }


}
