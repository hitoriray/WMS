package org.example.warehouse.service.impl;

import org.example.warehouse.dao.UserTotalDao;
import org.example.warehouse.service.MainUserService;
import org.example.warehouse.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddUserImpl implements MainUserService {

    @Override
    public boolean verifyAdd(UserTotalDao userTotalDao) {
        String sql0 = "select IDnumber from userinfo where name=?";
        String sql1 = "insert into userinfo(name,IDnumber,date,gender,origin,address,type,phone) values(?,?,?,?,?,?,?,?)";
        String sql2 = "insert into permissions(name,IDnumber,inquire,inbound,outbound,manager,file) values(?,?,?,?,?,?,?)";
        String sql3 = "insert into users(name,pwd) values(?,?)";
        Connection conn0 = null;
        Connection conn1 = null;
        Connection conn2 = null;
        Connection conn3 = null;
        PreparedStatement ps0 = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        try {
            conn0 = JDBCUtil.getConnection();
            ps0 = conn0.prepareStatement(sql0);
            ps0.setString(1, userTotalDao.getName());
            ResultSet rs0 = ps0.executeQuery();
            if (rs0.next()) {
                String ID = rs0.getString(1);
//                System.out.println("ID = " + ID);
//                System.out.println("ID2 = " + userTotalDao.getIDnumber());
                if (ID.equals(userTotalDao.getIDnumber())) {
                    return false;
                }
            }
            conn1 = JDBCUtil.getConnection();
            ps1 = conn1.prepareStatement(sql1);
            ps1.setString(1, userTotalDao.getName());
            ps1.setString(2, userTotalDao.getIDnumber());
            ps1.setString(3, userTotalDao.getDate());
            ps1.setString(4, userTotalDao.getGender());
            ps1.setString(5, userTotalDao.getOrigin());
            ps1.setString(6, userTotalDao.getAddress());
            ps1.setString(7, userTotalDao.getType());
            ps1.setString(8, userTotalDao.getPhone());

            conn2 = JDBCUtil.getConnection();
            ps2 = conn2.prepareStatement(sql2);
            ps2.setString(1, userTotalDao.getName());
            ps2.setString(2, userTotalDao.getIDnumber());
            ps2.setInt(3, 0);
            ps2.setInt(4, 0);
            ps2.setInt(5, 0);
            ps2.setInt(6, 0);
            ps2.setInt(7, 0);
            ps2.executeUpdate();

            conn3 = JDBCUtil.getConnection();
            ps3 = conn3.prepareStatement(sql3);
            String IDNumber = userTotalDao.getIDnumber();
            String substring = IDNumber.substring(12);
            ps3.setString(1, userTotalDao.getName());
            ps3.setString(2, substring);
            System.out.println(substring);
            ps3.executeUpdate();

            return ps1.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
