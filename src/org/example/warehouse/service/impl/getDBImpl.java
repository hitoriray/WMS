package org.example.warehouse.service.impl;

import org.example.warehouse.dao.ckDao;
import org.example.warehouse.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class getDBImpl {
    public List<ckDao> get() {
        List<ckDao> list;
        Connection conn;
        PreparedStatement ps = null;
        String sql = "select name,min,max from warehouse";
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            list = new LinkedList<>();
            while (rs.next()) {
                ckDao ck = new ckDao();
                ck.setName(rs.getString("name"));
                ck.setMax(rs.getString("max"));
                ck.setMin(rs.getString("min"));
                list.add(ck);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}