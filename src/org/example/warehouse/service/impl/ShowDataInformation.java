package org.example.warehouse.service.impl;

import org.example.warehouse.dao.PermissionDao;
import org.example.warehouse.dao.UserTotalDao;
import org.example.warehouse.dao.boundDao;
import org.example.warehouse.dao.warehouseDao;
import org.example.warehouse.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShowDataInformation {

    public static String getItemNameById(String id) {
        String sql = "select name from warehouse where id=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("name");
            } else {
                return "null";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteInformation(String name) {
        String sql = "delete from userinfo where name=?";
        String sql1 = "delete from users where name=?";
        String sql2 = "delete from permissions where name=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps.setString(1, name);
            ps1.setString(1, name);
            ps2.setString(1, name);
//            ps1.executeUpdate();
//            ps2.executeUpdate();
            return ps.executeUpdate() == 1 && ps1.executeUpdate() == 1 && ps2.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteck(String id) {
        String sql = "delete from warehouse where id=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<UserTotalDao> getInformation() {
        String sql = "select name,IDnumber,date,gender,origin,address,type,phone from userinfo";
        List<UserTotalDao> list = new LinkedList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserTotalDao userTotalDao = new UserTotalDao();
                userTotalDao.setName(rs.getString("name"));
                userTotalDao.setIDnumber(rs.getString("IDnumber"));
                userTotalDao.setDate(rs.getString("date"));
                userTotalDao.setGender(rs.getString("gender"));
                userTotalDao.setOrigin(rs.getString("origin"));
                userTotalDao.setAddress(rs.getString("address"));
                userTotalDao.setType(rs.getString("type"));
                userTotalDao.setPhone(rs.getString("phone"));
                list.add(userTotalDao);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public String getid() {
        String sql = "select id from warehouse";
        List<warehouseDao> list = new LinkedList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                warehouseDao ck = new warehouseDao();
                ck.setId(rs.getString("id"));
                list.add(ck);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list.get(list.size() - 1).getId();
    }

    public static List<UserTotalDao> getInformationsingle(String name) {
        String sql = "select name,IDnumber,date,gender,origin,address,type,phone from userinfo";
        List<UserTotalDao> list = new LinkedList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("name").contains(name)) {
                    UserTotalDao userTotalDao = new UserTotalDao();
                    userTotalDao.setName(rs.getString("name"));
                    userTotalDao.setIDnumber(rs.getString("IDnumber"));
                    userTotalDao.setDate(rs.getString("date"));
                    userTotalDao.setGender(rs.getString("gender"));
                    userTotalDao.setOrigin(rs.getString("origin"));
                    userTotalDao.setAddress(rs.getString("address"));
                    userTotalDao.setType(rs.getString("type"));
                    userTotalDao.setPhone(rs.getString("phone"));
                    list.add(userTotalDao);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static List<PermissionDao> getPermissionInformation() {
        String sql = "select name,IDnumber,inquire,inbound,outbound,manager,file from permissions";
        List<PermissionDao> list = new LinkedList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PermissionDao permissionDao = new PermissionDao();
                permissionDao.setName(rs.getString("name"));
                permissionDao.setIDnumber(rs.getString("IDnumber"));
                permissionDao.setInquire(rs.getString("inquire"));
                permissionDao.setInbound(rs.getString("inbound"));
                permissionDao.setOutbound(rs.getString("outbound"));
                permissionDao.setManager(rs.getString("manager"));
                permissionDao.setFile(rs.getString("file"));
                list.add(permissionDao);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static List<PermissionDao> getPermissionInformationByName(String name) {
        String sql = "select name,IDnumber,inquire,inbound,outbound,manager,file from permissions";
        List<PermissionDao> list = new LinkedList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("name").contains(name)) {
                    PermissionDao permissionDao = new PermissionDao();
                    permissionDao.setName(rs.getString("name"));
                    permissionDao.setIDnumber(rs.getString("IDnumber"));
                    permissionDao.setInquire(rs.getString("inquire"));
                    permissionDao.setInbound(rs.getString("inbound"));
                    permissionDao.setOutbound(rs.getString("outbound"));
                    permissionDao.setManager(rs.getString("manager"));
                    permissionDao.setFile(rs.getString("file"));
                    list.add(permissionDao);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static PermissionDao getPermissionByName(String name) {
        String sql = "select name,IDnumber,inquire,inbound,outbound,manager,file from permissions where name=?";
        PermissionDao permissionDao = new PermissionDao();
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                permissionDao.setName(rs.getString("name"));
                permissionDao.setIDnumber(rs.getString("IDnumber"));
                permissionDao.setInquire(rs.getString("inquire"));
                permissionDao.setInbound(rs.getString("inbound"));
                permissionDao.setOutbound(rs.getString("outbound"));
                permissionDao.setManager(rs.getString("manager"));
                permissionDao.setFile(rs.getString("file"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return permissionDao;
    }

    public static List<warehouseDao> getck() {
        String sql = "select id,name,type,unit,remark,inventory,min,max from warehouse";
        List<warehouseDao> list = new LinkedList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                warehouseDao ck = new warehouseDao();
                ck.setId(rs.getString("id"));
                ck.setName(rs.getString("name"));
                ck.setType(rs.getString("type"));
                ck.setUnit(rs.getString("unit"));
                ck.setRemark(rs.getString("remark"));
                ck.setInventory(rs.getString("inventory"));
                ck.setMin(rs.getString("min"));
                ck.setMax(rs.getString("max"));
                list.add(ck);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static List<warehouseDao> getckSetup() {
        String sql = "select id,min,max from warehouse";
        List<warehouseDao> list = new LinkedList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                warehouseDao ck = new warehouseDao();
                ck.setId(rs.getString("id"));
                ck.setMin(rs.getString("min"));
                ck.setMax(rs.getString("max"));
                list.add(ck);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static List<warehouseDao> getckSingle(String s) {
        String sql = "select  id,name,type,unit,remark,inventory,min,max from warehouse";
        List<warehouseDao> list = new LinkedList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("id").contains(s) || rs.getString("name").contains(s) || rs.getString("type").contains(s)) {
                    warehouseDao ck = new warehouseDao();
                    ck.setId(rs.getString("id"));
                    ck.setName(rs.getString("name"));
                    ck.setType(rs.getString("type"));
                    ck.setUnit(rs.getString("unit"));
                    ck.setRemark(rs.getString("remark"));
                    ck.setInventory(rs.getString("inventory"));
                    ck.setMin(rs.getString("min"));
                    ck.setMax(rs.getString("max"));
                    list.add(ck);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static List<boundDao> getInbound() {
        String sql = "select Danhao,id,number,boundtype,name,time from inventory";
        List<boundDao> list = new LinkedList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("boundtype").equals("入库")) {
                    boundDao inbound = new boundDao();
                    inbound.setDanhao(rs.getString("Danhao"));
                    inbound.setId(rs.getString("id"));
                    inbound.setNumber(rs.getString("number"));
                    inbound.setBoundtype(rs.getString("boundtype"));
                    inbound.setName(rs.getString("name"));
                    inbound.setTime(rs.getString("time"));
                    list.add(inbound);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static List<boundDao> getOutbound() {
        String sql = "select Danhao,id,number,boundtype,name,time from inventory";
        List<boundDao> list = new LinkedList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("boundtype").equals("出库")) {
                    boundDao outbound = new boundDao();
                    outbound.setDanhao(rs.getString("Danhao"));
                    outbound.setId(rs.getString("id"));
                    outbound.setNumber(rs.getString("number"));
                    outbound.setBoundtype(rs.getString("boundtype"));
                    outbound.setName(rs.getString("name"));
                    outbound.setTime(rs.getString("time"));
                    list.add(outbound);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static List<boundDao> getStatisticsByItem(String startDate, String endDate) {
        List<boundDao> result = new ArrayList<>();
        String query = "SELECT id, boundtype, SUM(CAST(number AS UNSIGNED)) as total FROM inventory " +
                "WHERE time BETWEEN ? AND ? " +
                "GROUP BY id, boundtype ORDER BY total DESC";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, startDate);
            stmt.setString(2, endDate);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                boundDao data = new boundDao();
                data.setId(rs.getString("id"));
                data.setBoundtype(rs.getString("boundtype"));
                data.setNumber(String.valueOf(rs.getInt("total")));
                result.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<boundDao> getStatisticsByUser(String startDate, String endDate) {
        List<boundDao> result = new ArrayList<>();
        String query = "SELECT name, boundtype, SUM(CAST(number AS UNSIGNED)) as total FROM inventory " +
                "WHERE time BETWEEN ? AND ? " +
                "GROUP BY name, boundtype ORDER BY total DESC";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, startDate);
            stmt.setString(2, endDate);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                boundDao data = new boundDao();
                data.setName(rs.getString("name"));
                data.setBoundtype(rs.getString("boundtype"));
                data.setNumber(String.valueOf(rs.getInt("total")));
                result.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
