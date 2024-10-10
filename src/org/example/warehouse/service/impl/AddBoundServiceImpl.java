package org.example.warehouse.service.impl;

import org.example.warehouse.dao.boundDao;
import org.example.warehouse.service.AddBoundService;
import org.example.warehouse.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddBoundServiceImpl implements AddBoundService {
    @Override
    public void addBound(boundDao bound) {
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
