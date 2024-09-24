package org.example.仓库管理系统.service.impl;

import org.example.仓库管理系统.dao.boundDao;
import org.example.仓库管理系统.service.AddboundService;
import org.example.仓库管理系统.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddboundServiceImpl implements AddboundService {
    @Override
    public void addbound(boundDao bo) {
        StringBuilder sql1 = new StringBuilder();
        sql1.append("insert into inventory(Danhao,id,number,boundtype,name,time)");
        sql1.append("values(?,?,?,?,?,?)");
        Connection conn1 = null;
        PreparedStatement ps1 = null;
        try {
            conn1 = JDBCUtil.getConnection();
            ps1=conn1.prepareStatement(sql1.toString());
            ps1.setString(1, bo.getDanhao());
            ps1.setString(2,bo.getId());
            ps1.setString(3,bo.getNumber());
            ps1.setString(4,bo.getBoundtype());
            ps1.setString(5,bo.getName());
            ps1.setString(6,bo.getTime());
            ps1.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
