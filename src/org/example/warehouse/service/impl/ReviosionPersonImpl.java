package org.example.warehouse.service.impl;

import org.example.warehouse.dao.UserTotalDao;
import org.example.warehouse.service.RevisionService;
import org.example.warehouse.utils.JDBCUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviosionPersonImpl implements RevisionService {

    @Override
    public boolean verifyRevision(UserTotalDao userTotalDao) {
        String sql = "update userinfo set IDnumber=?,date=?,gender=?,origin=?,address=?,type=?,phone=? where name=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userTotalDao.getIDnumber());
            ps.setString(2, userTotalDao.getDate());
            ps.setString(3, userTotalDao.getGender());
            ps.setString(4, userTotalDao.getOrigin());
            ps.setString(5, userTotalDao.getAddress());
            ps.setString(6, userTotalDao.getType());
            ps.setString(7, userTotalDao.getPhone());
            ps.setString(8, userTotalDao.getName());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "输入日期有误！", "修改", 2);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modifyPwd(String oldPwd, String newPwd) {
        String sql = "update users set pwd=? where pwd=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newPwd);
            ps.setString(2, oldPwd);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
