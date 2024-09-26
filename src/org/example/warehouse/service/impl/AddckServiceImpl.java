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
        String sql = "insert into warehouse(id,name,type,unit,remark,inventory,min,max) values(?,?,?,?,?,?,?,?)";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ck.getId());
            ps.setString(2, ck.getName());
            ps.setString(3, ck.getType());
            ps.setString(4, ck.getUnit());
            ps.setString(5, ck.getRemark());
            ps.setString(6, "1");
            ps.setString(7, "2");
            ps.setString(8, "1000");
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addInbound(boundDao bound) {
        String sql1 = "select Danhao from inventory where Danhao=?";
        String sql2 = "insert into inventory(Danhao,id,number,boundtype,name,time) values(?,?,?,?,?,?)";
        String newDanhao = bound.getDanhao();
        try {
            Connection conn1 = JDBCUtil.getConnection();
            /*while (i < 1) {
                ps1.setString(1, s);
                ResultSet rs = ps1.executeQuery();
                if (rs.next()) {
                    String danhao = rs.getString(1);
                    if (danhao.equals(bound.getDanhao())) {
                        int num1 = (int) (Math.random() * 10);
                        int num2 = (int) (Math.random() * 10);
                        int num3 = (int) (Math.random() * 10);
                        int num4 = (int) (Math.random() * 10);
                        s = num1 + String.valueOf(num2) + num3 + num4;
                    } else {
                        i = 1;
                    }
                }
                i = 1;
            }*/
            PreparedStatement ps1 = conn1.prepareStatement(sql1);
            ps1.setString(1, bound.getDanhao());
            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                String danhao = rs.getString(1);
                if (danhao.equals(bound.getDanhao())) {
                    int num1 = (int) (Math.random() * 10);
                    int num2 = (int) (Math.random() * 10);
                    int num3 = (int) (Math.random() * 10);
                    int num4 = (int) (Math.random() * 10);
                    newDanhao = String.valueOf(num1) + num2 + num3 + num4;
                }
            }
            Connection conn2 = JDBCUtil.getConnection();
            PreparedStatement ps2 = conn2.prepareStatement(sql2);
            ps2.setString(1, newDanhao);
            ps2.setString(2, bound.getId());
            ps2.setString(3, bound.getNumber());
            ps2.setString(4, bound.getBoundtype());
            ps2.setString(5, bound.getName());
            ps2.setString(6, bound.getTime());
            ps2.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addOutbound(boundDao bound) {
        String sql = "insert into inventory(Danhao,id,number,boundtype,name,time) values(?,?,?,?,?,?)";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, bound.getDanhao());
            ps.setString(2, bound.getId());
            ps.setString(3, bound.getNumber());
            ps.setString(4, bound.getBoundtype());
            ps.setString(5, bound.getName());
            ps.setString(6, bound.getTime());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
