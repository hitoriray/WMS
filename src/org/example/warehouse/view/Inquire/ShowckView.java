package org.example.warehouse.view.Inquire;

import org.example.warehouse.dao.warehouseDao;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShowckView extends JFrame {
    JFrame jFrame = new JFrame("查询");
    public ShowckView(List<warehouseDao> list) {
        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        //table添加
        String[] index = {"编号", "名称", "类型", "单位", "品牌", "剩余库存", "最小值", "最大值"};
        Object[][] data = new Object[list.size()][index.length];
        for (int i = 0; i < list.size(); i++) {
            warehouseDao ck = list.get(i);
            data[i][0] = ck.getId();
            data[i][1] = ck.getName();
            data[i][2] = ck.getType();
            data[i][3] = ck.getUnit();
            data[i][4] = ck.getRemark();
            data[i][5] = ck.getInventory();
            data[i][6] = ck.getMin();
            data[i][7] = ck.getMax();
        }
        JTable table = new JTable(data, index);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setEnabled(false);
        table.getTableHeader().setResizingAllowed(false);
        jFrame.getContentPane().add(jPanel, BorderLayout.NORTH);
        jFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        jFrame.setSize(800, 600);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
