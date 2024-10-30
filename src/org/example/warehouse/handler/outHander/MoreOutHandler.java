package org.example.warehouse.handler.outHander;

import org.example.warehouse.dao.boundDao;
import org.example.warehouse.dao.warehouseDao;
import org.example.warehouse.service.AddBoundService;
import org.example.warehouse.service.AddItemService;
import org.example.warehouse.service.RevisionItemService;
import org.example.warehouse.service.UserService;
import org.example.warehouse.service.impl.*;
import org.example.warehouse.view.Inquire.ShowckView;
import org.example.warehouse.view.LoginView;
import org.example.warehouse.view.Report.ReportView;
import org.example.warehouse.view.out.MoreOutView;
import org.example.warehouse.view.out.SimpleOutView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MoreOutHandler implements ActionListener {
    private MoreOutView moreOutView;
    private UserService userService = new UserServiceImpl();
    private AddBoundService addBoundService = new AddBoundServiceImpl();

    public MoreOutHandler(MoreOutView moreOutView, LoginView loginView) {
        this.moreOutView = moreOutView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();

        if (text.equals("确认出库")) {
            int rows = moreOutView.table.getRowCount();
            if (rows == 0) {
                JOptionPane.showMessageDialog(null, "没有可出库的物料", "出库", JOptionPane.WARNING_MESSAGE);
                return;
            }
            System.out.println("rows: " + rows);
            for (int i = 0; i < rows; i++) {
                Boolean isSelected = (Boolean) moreOutView.table.getValueAt(i, 0);
                if (isSelected != null && isSelected) {
                    String materialId = (String) moreOutView.table.getValueAt(i, 1);
                    int outboundQty;
                    try {
                        outboundQty = Integer.parseInt(moreOutView.table.getValueAt(i, 6).toString());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "请输入有效的出库数量", "出库", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (outboundQty <= 0) {
                        JOptionPane.showMessageDialog(null, "出库数量必须大于0", "出库", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    processOutbound(materialId, outboundQty);
                }
            }
        } else if (text.equals("查询货物信息")) {
            // 查询并加载所有物料信息
            List<warehouseDao> materialsData = ShowDataInformation.getck();
            moreOutView.loadMaterials(materialsData);
        }
    }

    public void processOutbound(String materialId, int quantity) {
        var res = ShowDataInformation.getckSingle(materialId);
        if (res.isEmpty()) {
            JOptionPane.showMessageDialog(null, "物料ID无效：" + materialId, "出库", 2);
            return;
        }
        warehouseDao material = res.get(0);

        System.out.println("material: " + material);

        if (material == null) {
            JOptionPane.showMessageDialog(null, "物料ID无效：" + materialId, "出库", 2);
            return;
        }
        if (quantity <= 0) {
            JOptionPane.showMessageDialog(null, "出库数量无效，请输入正整数", "出库", 2);
            return;
        }

        int availableStock = Integer.parseInt(material.getInventory());
        if (quantity > availableStock) {
            JOptionPane.showMessageDialog(null, "库存不足，当前库存为 " + availableStock + " 件", "出库", 2);
            return;
        }
        // 更新库存信息
        RevisionItemService revisionItemService = new RevisionItemServiceImpl();
        System.out.println("remain inventory: " + String.valueOf(Integer.parseInt(material.getInventory()) - quantity));
        material.setInventory(String.valueOf(Integer.parseInt(material.getInventory()) - quantity));
        String flag = revisionItemService.revisionInventory(material);
        if (flag == "1") {
            int num1 = (int) (Math.random() * 10);
            int num2 = (int) (Math.random() * 10);
            int num3 = (int) (Math.random() * 10);
            int num4 = (int) (Math.random() * 10);
            String num = num1 + String.valueOf(num2) + num3 + num4;
            boundDao newRecord = new boundDao();
            newRecord.setDanhao("OUT_MultiBound" + num);
            newRecord.setId(materialId);
            newRecord.setNumber(String.valueOf(quantity));
            newRecord.setBoundtype("出库");
            newRecord.setName(material.getName());
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            newRecord.setTime(formatter.format(date));
            AddItemService addItemService = new AddItemServiceImpl();
            addItemService.addInbound(newRecord);
            JOptionPane.showMessageDialog(null, " 出库成功!" + "您的出库编号为" + "OUT_MultiBound" + num, "出库", 1);
        } else if (flag == "-1") {
            JOptionPane.showMessageDialog(null, "抱歉，暂无该产品", "添加", 2);
        } else if (flag == "2") {
            JOptionPane.showMessageDialog(null, "您出库的数量过多，请重新输入", "出库", 2);
        } else if (flag == "3") {
            JOptionPane.showMessageDialog(null, "您出库的数量过少，请重新输入", "出库", 2);
        } else if (flag == "4") {
            JOptionPane.showMessageDialog(null, "请输入数量！！！！", "出库", 2);
        } else if (flag == "5") {
            JOptionPane.showMessageDialog(null, "库存不够！", "出库", 2);
        }
    }
}