package org.example.warehouse.handler.inHandler;

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
import org.example.warehouse.view.in.MoreInView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MoreInHandler implements ActionListener {
    private MoreInView moreInView;
    private LoginView loginView;
    private JTextField aTextField;
    private JTextField bTextField;

    private JTextField cTextField;
    private JTextField dTextField;
    private JTextField eTextField;


    public MoreInHandler(MoreInView moreInView, LoginView loginView) {
        this.moreInView = moreInView;
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();

        if (text.equals("确认添加")) {
            int rows = moreInView.inventoryTable.getRowCount();

            // 创建服务类
            UserService userService = new UserServiceImpl();
            warehouseDao ck = new warehouseDao();
            boundDao bo = new boundDao();
            AddBoundService addboundService = new AddBoundServiceImpl();

            boolean ok = false;
            for (int i = 0; i < rows; i++) {
                Boolean isSelected = (Boolean) moreInView.inventoryTable.getValueAt(i, 0); // 获取复选框状态
                String quantity = (String) moreInView.inventoryTable.getValueAt(i, 8); // 获取输入的数量

                if (isSelected != null && isSelected) { // 如果复选框被选中
                    ok = true;
                    if (quantity == null || quantity.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "请为选中的货物输入数量！", "入库", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if (Integer.parseInt(quantity) <= 0) {
                        JOptionPane.showMessageDialog(null, "货物输入数量不合法！", "入库", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
            }
            if (!ok) {
                JOptionPane.showMessageDialog(null, "请选择需要入库的物料！", "入库", JOptionPane.WARNING_MESSAGE);
                return;
            }

            for (int i = 0; i < rows; i++) {
                Boolean isSelected = (Boolean) moreInView.inventoryTable.getValueAt(i, 0); // 获取复选框状态
                String quantity = (String) moreInView.inventoryTable.getValueAt(i, 8); // 获取输入的数量
                String maxInvent = (String) moreInView.inventoryTable.getValueAt(i, 7);
                String id = (String) moreInView.inventoryTable.getValueAt(i, 1);
                String inventory = (String) moreInView.inventoryTable.getValueAt(i, 5);
                if (isSelected != null && isSelected) { // 如果复选框被选中
                    RevisionItemService revisionItemService = new RevisionItemServiceImpl();
                    ck.setId(id);
                    int newInvent = Integer.parseInt(inventory) + Integer.parseInt(quantity);
                    if (newInvent > Integer.parseInt(maxInvent)) {
                        JOptionPane.showMessageDialog(null, "超出最大库存，入库失败！", "入库", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    ck.setInventory(String.valueOf(newInvent));
                    revisionItemService.revisionMoreNumber1(ck);

                    // 记录入库信息
                    bo.setDanhao("IN_MoreBound" + generateRandomNumber());
                    bo.setId((String) moreInView.inventoryTable.getValueAt(i, 1)); // 货物编号
                    bo.setNumber(quantity); // 设置入库数量
                    bo.setBoundtype("入库");
                    bo.setName(loginView.getUserTxt().getText());
                    bo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    addboundService.addBound(bo);
                }
            }

            JOptionPane.showMessageDialog(null, "入库成功！", "入库", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private String generateRandomNumber() {
        int num1 = (int) (Math.random() * 10);
        int num2 = (int) (Math.random() * 10);
        int num3 = (int) (Math.random() * 10);
        int num4 = (int) (Math.random() * 10);
        return num1 + String.valueOf(num2) + num3 + num4;
    }
}

