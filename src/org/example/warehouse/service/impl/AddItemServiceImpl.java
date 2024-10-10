package org.example.warehouse.service.impl;

import org.example.warehouse.dao.boundDao;
import org.example.warehouse.dao.warehouseDao;
import org.example.warehouse.service.AddItemService;
import org.example.warehouse.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddItemServiceImpl implements AddItemService {
    @Override
    public boolean addItem(warehouseDao warehouseDao) {
        String sql = "insert into warehouse(id,name,type,unit,remark,inventory,min,max) values(?,?,?,?,?,?,?,?)";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, warehouseDao.getId());
            ps.setString(2, warehouseDao.getName());
            ps.setString(3, warehouseDao.getType());
            ps.setString(4, warehouseDao.getUnit());
            ps.setString(5, warehouseDao.getRemark());
            ps.setString(6, "1");
            ps.setString(7, "2");
            ps.setString(8, "1000");
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addInbound(boundDao boundDao) {
        String sql1 = "select Danhao from inventory where Danhao=?";
        String sql2 = "insert into inventory(Danhao,id,number,boundtype,name,time) values(?,?,?,?,?,?)";
        String newDanhao = boundDao.getDanhao();
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
            ps1.setString(1, boundDao.getDanhao());
            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                String danhao = rs.getString(1);
                if (danhao.equals(boundDao.getDanhao())) {
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
            ps2.setString(2, boundDao.getId());
            ps2.setString(3, boundDao.getNumber());
            ps2.setString(4, boundDao.getBoundtype());
            ps2.setString(5, boundDao.getName());
            ps2.setString(6, boundDao.getTime());
            ps2.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addOutbound(boundDao boundDao) {
        String sql = "insert into inventory(Danhao,id,number,boundtype,name,time) values(?,?,?,?,?,?)";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, boundDao.getDanhao());
            ps.setString(2, boundDao.getId());
            ps.setString(3, boundDao.getNumber());
            ps.setString(4, boundDao.getBoundtype());
            ps.setString(5, boundDao.getName());
            ps.setString(6, boundDao.getTime());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
