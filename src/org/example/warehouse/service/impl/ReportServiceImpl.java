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
        String sql = "select Danhao,id,number,boundtype,name,time from inventory";
        List<boundDao> list = new LinkedList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (type.equals(rs.getString("boundtype")) && Danhao.equals(rs.getString("Danhao"))) {
                    boundDao bound = new boundDao();
                    bound.setDanhao(rs.getString("Danhao"));
                    bound.setId(rs.getString("id"));
                    bound.setNumber(rs.getString("number"));
                    bound.setBoundtype(rs.getString("boundtype"));
                    bound.setName(rs.getString("name"));
                    bound.setTime(rs.getString("time"));
                    list.add(bound);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
