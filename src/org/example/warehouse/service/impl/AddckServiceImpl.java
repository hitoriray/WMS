package org.example.warehouse.service.impl;

import org.example.warehouse.dao.boundDao;
import org.example.warehouse.dao.ckDao;
import org.example.warehouse.service.AddckService;
import org.example.warehouse.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddckServiceImpl implements AddckService {
    @Override
    public boolean addck(ckDao ck) {
        StringBuilder sql1 = new StringBuilder();
        sql1.append("insert into warehouse(id,name,type,unit,remark,inventory,min,max) ");
        sql1.append("values(?,?,?,?,?,?,?,?)");

        Connection conn1 = null;
        PreparedStatement ps1 = null;
        try {
            conn1 = JDBCUtil.getConnection();
            ps1 = conn1.prepareStatement(sql1.toString());
            ps1.setString(1, ck.getId());
            ps1.setString(2, ck.getName());
            ps1.setString(3, ck.getType());
            ps1.setString(4, ck.getUnit());
            ps1.setString(5, ck.getRemark());
            ps1.setString(6, "1");
            ps1.setString(7, "2");
            ps1.setString(8, "1000");
            return ps1.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void addin(boundDao bo) {
        StringBuilder sql1 = new StringBuilder();
        String sql0 = "select Danhao from inventory where Danhao=?";
        sql1.append("insert into inventory(Danhao,id,number,boundtype,name,time) ");
        sql1.append("values(?,?,?,?,?,?)");

        Connection conn1 = null;
        PreparedStatement ps1 = null;
        Connection conn0 = null;
        PreparedStatement ps0 = null;
        ResultSet resultSet = null;
        String num = bo.getDanhao();
        conn0 = JDBCUtil.getConnection();
        try {
            int i = 0;
            ps0 = conn0.prepareStatement(sql0);

            while (i < 1) {
                ps0.setString(1, num);
                resultSet = ps0.executeQuery();
                if (resultSet.next()) {
                    String Danhao1 = resultSet.getString(1);
                    if (Danhao1.equals(bo.getDanhao())) {
                        int num1 = (int) (Math.random() * 10);
                        int num2 = (int) (Math.random() * 10);
                        int num3 = (int) (Math.random() * 10);
                        int num4 = (int) (Math.random() * 10);
                        num = num1 + String.valueOf(num2) + num3 + num4;
                    } else {
                        i = 1;
                    }
                }
                i = 1;
            }
            conn1 = JDBCUtil.getConnection();
            ps1 = conn1.prepareStatement(sql1.toString());
            ps1.setString(1, num);
            ps1.setString(2, bo.getId());
            ps1.setString(3, bo.getNumber());
            ps1.setString(4, bo.getBoundtype());
            ps1.setString(5, bo.getName());
            ps1.setString(6, bo.getTime());
            ps1.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addout(boundDao bo) {
        StringBuilder sql1 = new StringBuilder();
        sql1.append("insert into inventory(Danhao,id,number,boundtype,name,time) ");
        sql1.append("values(?,?,?,?,?,?)");

        Connection conn1 = null;
        PreparedStatement ps1 = null;
        try {
            conn1 = JDBCUtil.getConnection();
            ps1 = conn1.prepareStatement(sql1.toString());
            ps1.setString(1, bo.getDanhao());
            ps1.setString(2, bo.getId());
            ps1.setString(3, bo.getNumber());
            ps1.setString(4, bo.getBoundtype());
            ps1.setString(5, bo.getName());
            ps1.setString(6, bo.getTime());
            ps1.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
