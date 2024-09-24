package org.example.仓库管理系统.service.impl;

import org.example.仓库管理系统.dao.PermissionDao;
import org.example.仓库管理系统.dao.UserTotalDao;
import org.example.仓库管理系统.dao.boundDao;
import org.example.仓库管理系统.dao.ckDao;
import org.example.仓库管理系统.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ShowDataInformation {

    public static  boolean deleteInformation(String name){

        String sql = "delete from userinfo where name=?";
        String sql1 = "delete from users where name=?";
        String sql2 = "delete from permissions where name=?";

        Connection conn;
        Connection conn1;
        Connection conn2;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        System.out.println(name+"111111111111");
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps1 = conn.prepareStatement(sql1);
            ps2 = conn.prepareStatement(sql2);
            ps.setString(1,name);
            ps1.setString(1,name);
            ps2.setString(1,name);
            ps1.executeUpdate();
            ps2.executeUpdate();
            return ps.executeUpdate()==1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static  boolean deleteck(String id){

        String sql = "delete from warehouse where id=?";
        Connection conn;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,id);
            return ps.executeUpdate()==1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<UserTotalDao> getInformation() {
        List<UserTotalDao> list;
        Connection conn;
        PreparedStatement ps = null;
        String sql = "select name,IDnumber,date,gender,origin,address,type,phone from userinfo";
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            list = new LinkedList<>();
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

    public String getid(){
        List<ckDao> list;
        Connection conn;
        PreparedStatement ps = null;
        String sql = "select id from warehouse";
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            list = new LinkedList<>();
            while (rs.next()) {
                ckDao ck = new ckDao();
                ck.setId(rs.getString("id"));
                list.add(ck);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list.get(list.size()-1).getId();


    }
    public static List<UserTotalDao> getInformationsingle(String name) {
        List<UserTotalDao> list;
        Connection conn;
        PreparedStatement ps = null;
        String sql = "select name,IDnumber,date,gender,origin,address,type,phone from userinfo";
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            list = new LinkedList<>();
            while (rs.next()) {
                if (rs.getString("name").contains(name)) {
                    System.out.println(rs.getString("name"));
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
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;


    }
    public  static  List<PermissionDao> getPermissionInformation(){
        List<PermissionDao> list;
        Connection conn;
        PreparedStatement ps = null;
        String sql = "select name,IDnumber,inquire,inbound,outbound,manager,file from permissions";
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            list = new LinkedList<>();
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
    public  static  List<PermissionDao> getPermissionInformation1(String s){
        List<PermissionDao> list;
        Connection conn;
        PreparedStatement ps = null;
        String sql = "select name,IDnumber,inquire,inbound,outbound,manager,file from permissions";
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            list = new LinkedList<>();
            while (rs.next()) {
                if (rs.getString("name").contains(s)) {
                    PermissionDao permissionDao = new PermissionDao();
                    permissionDao.setName(rs.getString("name"));
                    permissionDao.setName(rs.getString("IDnumber"));
                    permissionDao.setInquire(rs.getString("inquire"));
                    permissionDao.setInbound(rs.getString("inbound"));
                    permissionDao.setOutbound(rs.getString("outbound"));
                    permissionDao.setManager(rs.getString("manager"));
                    permissionDao.setFile(rs.getString("file"));
                    list.add(permissionDao);
                }

            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;

    }
    public  static  List<ckDao> getck(){
        List<ckDao> list;
        Connection conn;
        PreparedStatement ps = null;
        String sql = "select id,name,type,unit,remark,inventory,min,max from warehouse";
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            list = new LinkedList<>();
            while (rs.next()) {
                ckDao ck = new ckDao();
                ck.setId(rs.getString("id"));
                ck.setName(rs.getString("name"));
                ck.setType(rs.getString("type"));
                ck.setUnit(rs.getString("unit"));
                ck.setRemark(rs.getString("remark"));
                ck.setInventory(rs.getString("inventory"));
                ck.setMin(rs.getString("min"));
                ck.setMax(rs.getString("max"));
                list.add(ck);
                System.out.println("1111111111111");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;

    }
    public  static  List<ckDao> getckSetup(){
        List<ckDao> list;
        Connection conn;
        PreparedStatement ps = null;
        String sql = "select id,min,max from warehouse";
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            list = new LinkedList<>();
            while (rs.next()) {
                ckDao ck = new ckDao();
                ck.setId(rs.getString("id"));
                ck.setMin(rs.getString("min"));
                ck.setMax(rs.getString("max"));
                list.add(ck);
                System.out.println("1111111111111");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;

    }
    public static List<ckDao> getckSingle(String name) {
        List<ckDao> list;
        Connection conn;
        PreparedStatement ps = null;
        String sql = "select  id,name,type,unit,remark,inventory,min,max from warehouse";
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            list = new LinkedList<>();
            while (rs.next()) {
                if (rs.getString("id").contains(name)||rs.getString("name").contains(name)||rs.getString("type").contains(name)) {
                    ckDao ck = new ckDao();
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
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public  static  List<boundDao> getIn(){
        List<boundDao> list;
        Connection conn;
        PreparedStatement ps = null;
        String sql = "select Danhao,id,number,boundtype,name,time from inventory";
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            list = new LinkedList<>();
            while (rs.next()) {
                if(rs.getString("boundtype").equals("入库")) {
                    boundDao bo = new boundDao();
                    bo.setDanhao(rs.getString("Danhao"));
                    bo.setId(rs.getString("id"));
                    bo.setNumber(rs.getString("number"));
                    bo.setBoundtype(rs.getString("boundtype"));
                    bo.setName(rs.getString("name"));
                    bo.setTime(rs.getString("time"));
                    list.add(bo);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public  static  List<boundDao> getOut(){
        List<boundDao> list;
        Connection conn;
        PreparedStatement ps = null;
        String sql = "select Danhao,id,number,boundtype,name,time from inventory";
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            list = new LinkedList<>();
            while (rs.next()) {
                if(rs.getString("boundtype").equals("出库")) {
                    boundDao bo = new boundDao();
                    bo.setDanhao(rs.getString("Danhao"));
                    bo.setId(rs.getString("id"));
                    bo.setNumber(rs.getString("number"));
                    bo.setBoundtype(rs.getString("boundtype"));
                    bo.setName(rs.getString("name"));
                    bo.setTime(rs.getString("time"));
                    list.add(bo);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }


}
