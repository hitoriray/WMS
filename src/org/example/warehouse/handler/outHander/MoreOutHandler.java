package org.example.warehouse.handler.outHander;

import org.example.warehouse.dao.boundDao;
import org.example.warehouse.dao.ckDao;
import org.example.warehouse.service.AddboundService;
import org.example.warehouse.service.RevisionckService;
import org.example.warehouse.service.UserService;
import org.example.warehouse.service.impl.AddboundServiceImpl;
import org.example.warehouse.service.impl.RevisionckServiceImpl;
import org.example.warehouse.service.impl.ShowDataInformation;
import org.example.warehouse.service.impl.UserServiceImpl;
import org.example.warehouse.view.Inquire.ShowckView;
import org.example.warehouse.view.LoginView;
import org.example.warehouse.view.Report.ReportView;
import org.example.warehouse.view.out.MoreOutView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MoreOutHandler implements ActionListener {
    private MoreOutView moreOutView;
    private LoginView loginView;

    public MoreOutHandler(MoreOutView moreOutView, LoginView loginView) {
        this.moreOutView = moreOutView;
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if (text.equals("添加行")) {
            String[] rowValues = {"", "", "", "多物料入库", loginView.getUserTxt().getText()}; //创建表格数组
            moreOutView.tableModel.addRow(rowValues);
        } else if (text.equals("确认出库")) {
            int rows = moreOutView.table.getRowCount();
            int cols = moreOutView.table.getColumnCount();
            System.out.println("rows" + rows);
            System.out.println("cols" + cols);
            UserService userService = new UserServiceImpl();
            ckDao ck = new ckDao();
            boundDao bo = new boundDao();
            AddboundService addboundService = new AddboundServiceImpl();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (moreOutView.table.getValueAt(i, j).equals("") || moreOutView.table.getValueAt(i, j) == null) {
                        JOptionPane.showMessageDialog(null, "您填入的信息不完整！", " 出库", 2);
                        return;
                    }
                }
            }
            for (int i = 0; i < rows; i++) {
                boolean yanzhengid = userService.verifyId((String) moreOutView.table.getValueAt(i, 0), (String) moreOutView.table.getValueAt(i, 1));
                if (!yanzhengid) {
                    JOptionPane.showMessageDialog(null, "您输入的信息有误", "出库", 2);
                    return;
                }
            }
            int num1 = (int) (Math.random() * 10);
            int num2 = (int) (Math.random() * 10);
            int num3 = (int) (Math.random() * 10);
            int num4 = (int) (Math.random() * 10);
            String num = num1 + String.valueOf(num2) + num3 + num4;
            String flag;
            int n = 0;
            for (int i = 0; i < rows; i++) {
                ck.setId((String) moreOutView.table.getValueAt(i, 0));
                ck.setInventory((String) moreOutView.table.getValueAt(i, 2));
                RevisionckService revisionckService = new RevisionckServiceImpl();
                flag = revisionckService.revisionMoreNumber_out(ck);
                if (flag.equals("1")) {
                    n++;
                } else if (flag.equals("2")) {
                    JOptionPane.showMessageDialog(null, "您出库的数量过多，请重新输入", "出库", 2);
                    return;
                } else if (flag.equals("3")) {
                    JOptionPane.showMessageDialog(null, "您出库的数量过少，请重新输入", "出库", 2);
                    return;
                } else if (flag.equals("5")) {
                    JOptionPane.showMessageDialog(null, "库存不够！", "出库", 2);
                    return;
                }
            }
            if (n == rows) {
                for (int i = 0; i < rows; i++) {
                    ck.setId((String) moreOutView.table.getValueAt(i, 0));
                    ck.setInventory((String) moreOutView.table.getValueAt(i, 2));
                    RevisionckService revisionckService = new RevisionckServiceImpl();
                    revisionckService.revisionMoreNumber_outnew(ck);
                    bo.setDanhao("OUT_MoreBound" + num);
                    bo.setId((String) moreOutView.table.getValueAt(i, 0));
                    bo.setNumber((String) moreOutView.table.getValueAt(i, 2));
                    bo.setBoundtype("出库");
                    bo.setName(loginView.getUserTxt().getText());
                    Date date = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    bo.setTime(formatter.format(date));
                    addboundService.addbound(bo);
                }
            }
            JOptionPane.showMessageDialog(null, "出库成功!" + "您的出库编号为" + "OUT_MoreBound" + num, "出库", 1);
        } else if (text.equals("删除行")) {
            int selectedRow = moreOutView.table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "请选择要删除的行", "删除", 2);
                return;
            }
            moreOutView.tableModel.removeRow(selectedRow);
        } else if (text.equals("查询货物信息")) {
            List<ckDao> list = ShowDataInformation.getck();
            new ShowckView(list);
        } else if (text.equals("报表打印")) {
            new ReportView();
        }
    }
}
