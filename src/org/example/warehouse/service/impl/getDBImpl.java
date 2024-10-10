package org.example.warehouse.service.impl;

import org.example.warehouse.dao.warehouseDao;
import org.example.warehouse.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class getDBImpl {
    public List<warehouseDao> get() {
        String sql = "select name,min,max from warehouse";
        List<warehouseDao> list = new LinkedList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                warehouseDao ck = new warehouseDao();
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
