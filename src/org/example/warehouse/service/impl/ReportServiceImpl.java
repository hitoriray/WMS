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
    public List<boundDao> bound(String type, String Danhao) {
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
                if (type.equals(rs.getString("boundtype")) && Danhao.equals(rs.getString("Danhao"))) {
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
