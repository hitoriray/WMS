package org.example.warehouse.service.impl;

import org.example.warehouse.dao.PermissionDao;
import org.example.warehouse.service.RevisionPermissionService;
import org.example.warehouse.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RevisionServiceImpl implements RevisionPermissionService {
    @Override
    public boolean RevisionPermission(PermissionDao permissionDao) {
        String sql = "update permissions set inquire=?,inbound=?,outbound=?,manager=?,file=? where name=?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, permissionDao.getInquire());
            ps.setString(2, permissionDao.getInbound());
            ps.setString(3, permissionDao.getOutbound());
            ps.setString(4, permissionDao.getManager());
            ps.setString(5, permissionDao.getFile());
            ps.setString(6, permissionDao.getName());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
