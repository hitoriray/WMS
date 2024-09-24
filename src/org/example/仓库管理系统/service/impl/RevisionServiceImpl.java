package org.example.仓库管理系统.service.impl;

import org.example.仓库管理系统.dao.PermissionDao;
import org.example.仓库管理系统.service.ReviosionPermissionService;
import org.example.仓库管理系统.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RevisionServiceImpl implements ReviosionPermissionService {

    @Override
    public boolean ReviosionPermission(PermissionDao permissionDao) {
        StringBuilder sql = new StringBuilder();
        sql.append("update permissions set inquire=?,inbound=?,outbound=?,manager=?,file=?");
        sql.append("where name=?");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        try {
            conn = JDBCUtil.getConnection();
            ps=conn.prepareStatement(sql.toString());

            ps.setString(1,permissionDao.getInquire());
            ps.setString(2,permissionDao.getInbound());
            ps.setString(3,permissionDao.getOutbound());
            ps.setString(4,permissionDao.getManager());
            ps.setString(5,permissionDao.getFile());
            ps.setString(6,permissionDao.getName());
            System.out.println(permissionDao.getName()+" wwwwwwwwww");

            return ps.executeUpdate()==1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
