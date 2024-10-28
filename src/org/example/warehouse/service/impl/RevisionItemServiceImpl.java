package org.example.warehouse.service.impl;

import org.example.warehouse.dao.warehouseDao;
import org.example.warehouse.service.RevisionItemService;
import org.example.warehouse.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RevisionItemServiceImpl implements RevisionItemService {
    @Override
    public boolean revisionItem(warehouseDao warehouseDao) {
        String sql = "update warehouse set name=?,type=?,unit=?,remark=? where id=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setString(1, warehouseDao.getName());
            ps.setString(2, warehouseDao.getType());
            ps.setString(3, warehouseDao.getUnit());
            ps.setString(4, warehouseDao.getRemark());
            ps.setString(5, warehouseDao.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean revisionSetup(warehouseDao warehouseDao) {
        String sql = "update warehouse set max=?,min=? where name=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setString(1, warehouseDao.getMax());
            ps.setString(2, warehouseDao.getMin());
            ps.setString(3, warehouseDao.getName());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String revisionNumber(warehouseDao warehouseDao) {
        String sql0 = "select id,inventory,min,max from warehouse";
        String sql1 = "update warehouse set inventory=? where id=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps0 = conn.prepareStatement(sql0);
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ResultSet rs0 = ps0.executeQuery();
            while (rs0.next()) {
                String inventory = rs0.getString("inventory");
                if (rs0.getString("id").equals(warehouseDao.getId())) {
                    if (Integer.parseInt(rs0.getString("max")) < Integer.parseInt(warehouseDao.getInventory())) {
                        return "2";
                    } else if (Integer.parseInt(rs0.getString("min")) > Integer.parseInt(warehouseDao.getInventory())) {
                        return "3";
                    } else {
                        ps1.setString(1, String.valueOf(Integer.parseInt(warehouseDao.getInventory()) + Integer.parseInt(inventory)));
                        ps1.setString(2, warehouseDao.getId());
                        ps1.executeUpdate();
                        return "1";
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "-1";
    }

    @Override
    public String revisionInventory(warehouseDao warehouseDao) {
        String sql = "update warehouse set inventory=? where id=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, warehouseDao.getInventory());
            ps.setString(2, warehouseDao.getId());
            ps.executeUpdate();
            return "1";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String revisionNumber1(warehouseDao warehouseDao) {
        String sql0 = "select id,inventory,min,max from warehouse";
        String sql1 = "update warehouse set inventory=? where id=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps0 = conn.prepareStatement(sql0);
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ResultSet rs0 = ps0.executeQuery();
            while (rs0.next()) {
                String inventory = rs0.getString("inventory");
                if (rs0.getString("id").equals(warehouseDao.getId())) {
                    if (Integer.parseInt(rs0.getString("max")) < Integer.parseInt(warehouseDao.getInventory())) {
                        return "2";
                    } else if (Integer.parseInt(rs0.getString("min")) > Integer.parseInt(warehouseDao.getInventory())) {
                        return "3";
                    } else if ((Integer.parseInt(inventory) - Integer.parseInt(warehouseDao.getInventory())) < 0) {
                        return "5";
                    } else {
                        ps1.setString(1, String.valueOf(Integer.parseInt(inventory) - Integer.parseInt(warehouseDao.getInventory())));
                        ps1.setString(2, warehouseDao.getId());
                        ps1.executeUpdate();
                        return "1";
                    }
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "-1";
    }

    @Override
    public void revisionMoreNumber(warehouseDao warehouseDao) {
        String sql0 = "select inventory from warehouse where id=?";
        String sql1 = "update warehouse set inventory=? where id=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps0 = conn.prepareStatement(sql0);
            ps0.setString(1, warehouseDao.getId());
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ResultSet rs0 = ps0.executeQuery();
            if (rs0.next()) {
                String inventory = rs0.getString("inventory");
                ps1.setString(1, String.valueOf(Integer.parseInt(warehouseDao.getInventory()) + Integer.parseInt(inventory)));
                ps1.setString(2, warehouseDao.getId());
                ps1.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String revisionMoreNumberOut(warehouseDao warehouseDao) {
        String sql0 = "select id,inventory,min,max from warehouse where id=?";
        String sql1 = "update warehouse set inventory=? where id=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps0 = conn.prepareStatement(sql0);
            ps0.setString(1, warehouseDao.getId());
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ResultSet rs0 = ps0.executeQuery();
            String inventory = null;
            while (rs0.next()) {
                inventory = rs0.getString("inventory");
                System.out.println(inventory + "inventory1");
                if (Integer.parseInt(rs0.getString("max")) < Integer.parseInt(warehouseDao.getInventory())) {
                    return "2";
                } else if (Integer.parseInt(rs0.getString("min")) > Integer.parseInt(warehouseDao.getInventory())) {
                    return "3";
                } else if ((Integer.parseInt(inventory) - Integer.parseInt(warehouseDao.getInventory())) < 0) {
                    System.out.println(inventory + "<>inventory2");
                    System.out.println(warehouseDao.getInventory() + "<>inventory");
                    return "5";
                } else {
                    return "1";
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void revisionMoreNumberOut1(warehouseDao warehouseDao) {
        String sql0 = "select id,inventory,min,max from warehouse where id=?";
        String sql1 = "update warehouse set inventory=? where id=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps0 = conn.prepareStatement(sql0);
            ps0.setString(1, warehouseDao.getId());
            PreparedStatement ps1 = conn.prepareStatement(sql1.toString());
            ResultSet rs0 = ps0.executeQuery();
            String inventory = null;
            while (rs0.next()) {
                inventory = rs0.getString("inventory");
                System.out.println("inventory: " + inventory);
                ps1.setString(1, String.valueOf(Integer.parseInt(inventory) - Integer.parseInt(warehouseDao.getInventory())));
                ps1.setString(2, warehouseDao.getId());
                ps1.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
