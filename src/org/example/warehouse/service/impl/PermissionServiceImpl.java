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
    private String username;

    public PermissionServiceImpl(MainView mainView, LoginView loginView) {
        this.loginView = loginView;
        this.mainView = mainView;
        this.username = loginView.getUserTxt().getText();
    }

    public int verifyPermission(PermissionDao permissionDao) {
        String sql = "select inquire from permissions where name=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int inquire = rs.getInt(1);
//                System.out.println(inquire);
                return inquire;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int verifyInbound(PermissionDao permissionDao) {
        String sql = "select inbound from permissions where name=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int in = rs.getInt(1);
//                System.out.println(in);
                return in;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int verifyOutbound(PermissionDao permissionDao) {
        String sql = "select Outbound from permissions where name=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int verifyManager(PermissionDao permissionDao) {
        String sql = "select manager from permissions where name=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int verifyFile(PermissionDao permissionDao) {
        String sql = "select file from permissions where name=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int verifyPerson(PermissionDao permissionDao) {
        String sql = "select person from permissions where name=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
