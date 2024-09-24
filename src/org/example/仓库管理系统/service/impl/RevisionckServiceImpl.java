package org.example.仓库管理系统.service.impl;

import org.example.仓库管理系统.dao.ckDao;
import org.example.仓库管理系统.service.RevisionckService;
import org.example.仓库管理系统.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RevisionckServiceImpl implements RevisionckService {
    @Override
    public boolean Revisionck(ckDao ck) {
        StringBuilder sql = new StringBuilder();
        sql.append("update warehouse set name=?,type=?,unit=?,remark=?");
        sql.append("where id=?");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql.toString());
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
        StringBuilder sql = new StringBuilder();
        sql.append("update warehouse set max=?,min=?");
        sql.append("where name=?");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql.toString());
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
        StringBuilder sql1 = new StringBuilder();
        String sql0 = "select id,inventory,min,max from warehouse";
        sql1.append("update warehouse set inventory=?");
        sql1.append("where id=?");
        Connection conn = null;
        PreparedStatement ps0 = null;
        PreparedStatement ps1 = null;
        ResultSet rs0 = null;
        ResultSet rs1 = null;
        try {
            conn = JDBCUtil.getConnection();
            ps0 = conn.prepareStatement(sql0);
            ps1 = conn.prepareStatement(sql1.toString());
            rs0 = ps0.executeQuery();
            String inventory;
            while (rs0.next()) {
                inventory = rs0.getString("inventory");
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
        StringBuilder sql1 = new StringBuilder();
        String sql0 = "select id,inventory,min,max from warehouse";
        sql1.append("update warehouse set inventory=?");
        sql1.append("where id=?");
        Connection conn = null;
        PreparedStatement ps0 = null;
        PreparedStatement ps1 = null;
        ResultSet rs0 = null;
        ResultSet rs1 = null;
        try {
            conn = JDBCUtil.getConnection();
            ps0 = conn.prepareStatement(sql0);
            ps1 = conn.prepareStatement(sql1.toString());
            rs0 = ps0.executeQuery();
            String inventory;
            while (rs0.next()) {
                inventory = rs0.getString("inventory");
                if (rs0.getString("id").equals(ck.getId())) {
                    if (Integer.parseInt(rs0.getString("max")) < Integer.parseInt(ck.getInventory())) {
                        return "2";
                    } else if (Integer.parseInt(rs0.getString("min")) > Integer.parseInt(ck.getInventory())) {
                        return "3";
                    } else if ((Integer.parseInt(inventory) - Integer.parseInt(ck.getInventory())) < 0) {
                        return "5";
                    } else {
                        ps1.setString(1, String.valueOf(Integer.parseInt(inventory)-Integer.parseInt(ck.getInventory())));
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
        StringBuilder sql1 = new StringBuilder();
        String sql0 = "select inventory from warehouse where id=?";
        sql1.append("update warehouse set inventory=?");
        sql1.append("where id=?");
        Connection conn = null;
        PreparedStatement ps0 = null;
        PreparedStatement ps1 = null;
        ResultSet rs0 = null;
        ResultSet rs1 = null;
        try {
            conn = JDBCUtil.getConnection();
            ps0 = conn.prepareStatement(sql0);
            ps0.setString(1,ck.getId());
            ps1 = conn.prepareStatement(sql1.toString());
            rs0 = ps0.executeQuery();
            String inventory;
            rs0.next();
            inventory = rs0.getString("inventory");

            ps1.setString(1, String.valueOf(Integer.parseInt(ck.getInventory()) + Integer.parseInt(inventory)));
            ps1.setString(2, ck.getId());
            System.out.println("ps1"+inventory);
            System.out.println("ps1"+ck.getInventory());
            ps1.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public String revisionMoreNumber_out(ckDao ck) {
        StringBuilder sql1 = new StringBuilder();
        String sql0 = "select id,inventory,min,max from warehouse where id=?";
        sql1.append("update warehouse set inventory=?");
        sql1.append("where id=?");
        Connection conn = null;
        PreparedStatement ps0 = null;
        PreparedStatement ps1 = null;
        ResultSet rs0 = null;
        ResultSet rs1 = null;
        try {
            conn = JDBCUtil.getConnection();
            ps0 = conn.prepareStatement(sql0);
            ps0.setString(1,ck.getId());
            ps1 = conn.prepareStatement(sql1.toString());
            rs0 = ps0.executeQuery();
            String inventory;
            while(rs0.next()) {
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
        StringBuilder sql1 = new StringBuilder();
        String sql0 = "select id,inventory,min,max from warehouse where id=?";
        sql1.append("update warehouse set inventory=?");
        sql1.append("where id=?");
        Connection conn = null;
        PreparedStatement ps0 = null;
        PreparedStatement ps1 = null;
        ResultSet rs0 = null;
        ResultSet rs1 = null;
        try {
            conn = JDBCUtil.getConnection();
            ps0 = conn.prepareStatement(sql0);
            ps0.setString(1,ck.getId());
            ps1 = conn.prepareStatement(sql1.toString());
            rs0 = ps0.executeQuery();
            String inventory;
            while(rs0.next()) {
                inventory = rs0.getString("inventory");
                System.out.println(inventory + "inventory1");
                    ps1.setString(1, String.valueOf(Integer.parseInt(inventory)-Integer.parseInt(ck.getInventory())));
                    ps1.setString(2, ck.getId());
                    ps1.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
