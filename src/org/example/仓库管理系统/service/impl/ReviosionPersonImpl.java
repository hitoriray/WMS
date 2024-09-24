package org.example.仓库管理系统.service.impl;

import org.example.仓库管理系统.dao.UserTotalDao;
import org.example.仓库管理系统.service.RevisionService;
import org.example.仓库管理系统.utils.JDBCUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviosionPersonImpl implements RevisionService {

    @Override
    public boolean yangzhenRevision(UserTotalDao userTotalDao) {
        StringBuilder sql = new StringBuilder();
        sql.append("update userinfo set IDnumber=?,date=?,gender=?,origin=?,address=?,type=?,phone=?");
        sql.append("where name=?");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        try {
            conn = JDBCUtil.getConnection();
            ps=conn.prepareStatement(sql.toString());
            ps.setString(1,userTotalDao.getIDnumber());
            ps.setString(2,userTotalDao.getDate());
            ps.setString(3,userTotalDao.getGender());
            ps.setString(4,userTotalDao.getOrigin());
            ps.setString(5,userTotalDao.getAddress());
            ps.setString(6,userTotalDao.getType());
            ps.setString(7,userTotalDao.getPhone());
            ps.setString(8,userTotalDao.getName());

            return ps.executeUpdate()==1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"输入日期有误！","修改",2);
            throw new RuntimeException(e);

        }
    }

    @Override
    public void updatepwd(String oldpwd,String newpwd) {
        StringBuilder sql = new StringBuilder();
        sql.append("update users set pwd=?");
        sql.append("where pwd=?");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        try {
            conn = JDBCUtil.getConnection();
            ps=conn.prepareStatement(sql.toString());
            ps.setString(1,newpwd);
            ps.setString(2,oldpwd);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
