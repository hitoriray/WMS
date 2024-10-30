package org.example.warehouse.view.Manager;

import org.example.warehouse.dao.warehouseDao;
import org.example.warehouse.service.impl.ShowDataInformation;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LookSetupView extends JFrame {
    JFrame jFrame = new JFrame("查询");

    public LookSetupView(List<warehouseDao> list) {
        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        //table添加
        String[] index = {"物品编号", "物品名称", "最小值", "最大值"};
        Object[][] data = new Object[list.size()][index.length];
        for (int i = 0; i < list.size(); i++) {
            warehouseDao ck = list.get(i);
            data[i][0] = ck.getId();
            data[i][1] = ShowDataInformation.getItemNameById(ck.getId());
            data[i][2] = ck.getMin();
            data[i][3] = ck.getMax();
        }
        JTable table = new JTable(data, index);
        JScrollPane scrollPane = new JScrollPane(table);
        jFrame.getContentPane().add(jPanel, BorderLayout.NORTH);
        jFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        jFrame.setSize(800, 600);
        table.setEnabled(false);
        table.getTableHeader().setResizingAllowed(false);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
