package org.example.仓库管理系统.view.Manager;

import org.example.仓库管理系统.dao.ckDao;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LookSetupView extends JFrame {
    JFrame jFrame = new JFrame("查询");

    public LookSetupView(List<ckDao> list) {
        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        //table添加
        String[] index = {"编号", "最小值", "最大值"};
        Object[][] data = new Object[list.size()][index.length];
        System.out.println("size:" + list.size());
        for (int i = 0; i < list.size(); i++) {
            ckDao ck = list.get(i);
            data[i][0] = ck.getId();
            data[i][1] = ck.getMin();
            data[i][2] = ck.getMax();
        }
        System.out.println("2222222222222");
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
