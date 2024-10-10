package org.example.warehouse.handler.outHander;

import org.example.warehouse.dao.boundDao;
import org.example.warehouse.dao.warehouseDao;
import org.example.warehouse.service.AddBoundService;
import org.example.warehouse.service.RevisionItemService;
import org.example.warehouse.service.UserService;
import org.example.warehouse.service.impl.AddBoundServiceImpl;
import org.example.warehouse.service.impl.RevisionItemServiceImpl;
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
            if (rows == 0) {
                JOptionPane.showMessageDialog(null, "出库信息不完整！", " 出库", 2);
                return;
            }
            int cols = moreOutView.table.getColumnCount();
//            System.out.println("rows" + rows);
//            System.out.println("cols" + cols);
            UserService userService = new UserServiceImpl();
            warehouseDao warehouseDao = new warehouseDao();
            boundDao boundDao = new boundDao();
            AddBoundService addboundService = new AddBoundServiceImpl();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (moreOutView.table.getValueAt(i, j).equals("") || moreOutView.table.getValueAt(i, j) == null) {
                        JOptionPane.showMessageDialog(null, "您填入的信息不完整！", " 出库", 2);
                        return;
                    }
                }
            }
            for (int i = 0; i < rows; i++) {
                boolean ok = userService.verifyId((String) moreOutView.table.getValueAt(i, 0), (String) moreOutView.table.getValueAt(i, 1));
                if (!ok) {
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
                warehouseDao.setId((String) moreOutView.table.getValueAt(i, 0));
                warehouseDao.setInventory((String) moreOutView.table.getValueAt(i, 2));
                RevisionItemService revisionItemService = new RevisionItemServiceImpl();
                flag = revisionItemService.revisionMoreNumberOut(warehouseDao);
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
                    warehouseDao.setId((String) moreOutView.table.getValueAt(i, 0));
                    warehouseDao.setInventory((String) moreOutView.table.getValueAt(i, 2));
                    RevisionItemService revisionItemService = new RevisionItemServiceImpl();
                    revisionItemService.revisionMoreNumberOut1(warehouseDao);
                    boundDao.setDanhao("OUT_MoreBound" + num);
                    boundDao.setId((String) moreOutView.table.getValueAt(i, 0));
                    boundDao.setNumber((String) moreOutView.table.getValueAt(i, 2));
                    boundDao.setBoundtype("出库");
                    boundDao.setName(loginView.getUserTxt().getText());
                    Date date = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    boundDao.setTime(formatter.format(date));
                    addboundService.addBound(boundDao);
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
            List<warehouseDao> list = ShowDataInformation.getck();
            new ShowckView(list);
        } else if (text.equals("报表打印")) {
            new ReportView();
        }
    }
}
