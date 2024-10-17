package org.example.warehouse.service.impl;

import org.example.warehouse.dao.boundDao;
import org.example.warehouse.service.ReportService;
import org.example.warehouse.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ReportServiceImpl implements ReportService {
    @Override
    public List<boundDao> bound(String type, String startTime, String endTime) {
        String sql;
        if ("所有出入库".equals(type)) {
            // 查询所有出入库记录
            sql = "select Danhao, id, number, boundtype, name, time FROM inventory WHERE time BETWEEN ? AND ?";
        } else {
            // 仅查询入库或出库记录
            sql = "select Danhao, id, number, boundtype, name, time FROM inventory WHERE boundtype = ? AND time BETWEEN ? AND ?";
        }

        List<boundDao> list = new LinkedList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            if ("所有出入库".equals(type)) {
                ps.setString(1, startTime);
                ps.setString(2, endTime);
            } else {
                ps.setString(1, type);
                ps.setString(2, startTime);
                ps.setString(3, endTime);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                boundDao bound = new boundDao();
                bound.setDanhao(rs.getString("Danhao"));
                bound.setId(rs.getString("id"));
                bound.setNumber(rs.getString("number"));
                bound.setBoundtype(rs.getString("boundtype"));
                bound.setName(rs.getString("name"));
                bound.setTime(rs.getString("time"));
                list.add(bound);
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}