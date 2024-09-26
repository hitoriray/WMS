package org.example.warehouse.service.impl;

import org.example.warehouse.dao.ckDao;
import org.example.warehouse.service.RevisionckService;
import org.example.warehouse.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RevisionckServiceImpl implements RevisionckService {
    @Override
    public boolean Revisionck(ckDao ck) {
        String sql = "update warehouse set name=?,type=?,unit=?,remark=? where id=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setString(1, ck.getName());
            ps.setString(2, ck.getType());
            ps.setString(3, ck.getUnit());
            ps.setString(4, ck.getRemark());
            ps.setString(5, ck.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean revisionsetup(ckDao ck) {
        String sql = "update warehouse set max=?,min=? where name=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setString(1, ck.getMax());
            ps.setString(2, ck.getMin());
            ps.setString(3, ck.getName());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String revisionnumber(ckDao ck) {
        String sql0 = "select id,inventory,min,max from warehouse";
        String sql1 = "update warehouse set inventory=? where id=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps0 = conn.prepareStatement(sql0);
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ResultSet rs0 = ps0.executeQuery();
            while (rs0.next()) {
                String inventory = rs0.getString("inventory");
                if (rs0.getString("id").equals(ck.getId())) {
                    if (Integer.parseInt(rs0.getString("max")) < Integer.parseInt(ck.getInventory())) {
                        return "2";
                    } else if (Integer.parseInt(rs0.getString("min")) > Integer.parseInt(ck.getInventory())) {
                        return "3";
                    } else {
                        ps1.setString(1, String.valueOf(Integer.parseInt(ck.getInventory()) + Integer.parseInt(inventory)));
                        ps1.setString(2, ck.getId());
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
    public String revisionnumber1(ckDao ck) {
        String sql0 = "select id,inventory,min,max from warehouse";
        String sql1 = "update warehouse set inventory=? where id=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps0 = conn.prepareStatement(sql0);
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ResultSet rs0 = ps0.executeQuery();
            while (rs0.next()) {
                String inventory = rs0.getString("inventory");
                if (rs0.getString("id").equals(ck.getId())) {
                    if (Integer.parseInt(rs0.getString("max")) < Integer.parseInt(ck.getInventory())) {
                        return "2";
                    } else if (Integer.parseInt(rs0.getString("min")) > Integer.parseInt(ck.getInventory())) {
                        return "3";
                    } else if ((Integer.parseInt(inventory) - Integer.parseInt(ck.getInventory())) < 0) {
                        return "5";
                    } else {
                        ps1.setString(1, String.valueOf(Integer.parseInt(inventory) - Integer.parseInt(ck.getInventory())));
                        ps1.setString(2, ck.getId());
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
    public void revisionMoreNumber(ckDao ck) {
        String sql0 = "select inventory from warehouse where id=?";
        String sql1 = "update warehouse set inventory=? where id=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps0 = conn.prepareStatement(sql0);
            ps0.setString(1, ck.getId());
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ResultSet rs0 = ps0.executeQuery();
            if (rs0.next()) {
                String inventory = rs0.getString("inventory");
                ps1.setString(1, String.valueOf(Integer.parseInt(ck.getInventory()) + Integer.parseInt(inventory)));
                ps1.setString(2, ck.getId());
                ps1.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String revisionMoreNumber_out(ckDao ck) {
        String sql0 = "select id,inventory,min,max from warehouse where id=?";
        String sql1 = "update warehouse set inventory=? where id=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps0 = conn.prepareStatement(sql0);
            ps0.setString(1, ck.getId());
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ResultSet rs0 = ps0.executeQuery();
            String inventory = null;
            while (rs0.next()) {
                inventory = rs0.getString("inventory");
                System.out.println(inventory + "inventory1");
                if (Integer.parseInt(rs0.getString("max")) < Integer.parseInt(ck.getInventory())) {
                    return "2";
                } else if (Integer.parseInt(rs0.getString("min")) > Integer.parseInt(ck.getInventory())) {
                    return "3";
                } else if ((Integer.parseInt(inventory) - Integer.parseInt(ck.getInventory())) < 0) {
                    System.out.println(inventory + "<>inventory2");
                    System.out.println(ck.getInventory() + "<>inventory");
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
    public void revisionMoreNumber_outnew(ckDao ck) {
        String sql0 = "select id,inventory,min,max from warehouse where id=?";
        String sql1 = "update warehouse set inventory=? where id=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps0 = conn.prepareStatement(sql0);
            ps0.setString(1, ck.getId());
            PreparedStatement ps1 = conn.prepareStatement(sql1.toString());
            ResultSet rs0 = ps0.executeQuery();
            String inventory = null;
            while (rs0.next()) {
                inventory = rs0.getString("inventory");
                System.out.println("inventory: " + inventory);
                ps1.setString(1, String.valueOf(Integer.parseInt(inventory) - Integer.parseInt(ck.getInventory())));
                ps1.setString(2, ck.getId());
                ps1.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
