package org.example.warehouse.view.Inquire;

import org.example.warehouse.dao.warehouseDao;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RemainView extends JFrame {
    JFrame jFrame = new JFrame("查询");

    public RemainView(List<warehouseDao> list) {
        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        //table添加
        String[] index = {"编号", "剩余库存"};
        Object[][] data = new Object[list.size()][index.length];
        System.out.println("size:" + list.size());
        for (int i = 0; i < list.size(); i++) {
            warehouseDao ck = list.get(i);
            data[i][0] = ck.getId();
            data[i][1] = ck.getInventory();

        }
        System.out.println("2222222222222");
        JTable table = new JTable(data, index);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setEnabled(false);
        jFrame.getContentPane().add(jPanel, BorderLayout.NORTH);
        jFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        jFrame.setSize(800, 600);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jFrame.setVisible(true);


    }

}
