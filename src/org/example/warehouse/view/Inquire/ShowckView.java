package org.example.warehouse.view.Inquire;

import org.example.warehouse.dao.warehouseDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ShowckView extends JFrame {
    JFrame jFrame = new JFrame("查询");
    JTable table;
    Object[][] data; // 存储原始数据
    String[] index = {"编号", "名称", "类型", "单位", "品牌", "剩余库存", "最小值", "最大值"};

    public ShowckView(List<warehouseDao> list) {
        // 初始化数据
        data = new Object[list.size()][index.length];
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

        // 创建表格
        table = new JTable(data, index);
        table.setEnabled(false);
        table.getTableHeader().setResizingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(table);

        // 搜索面板
        JPanel searchPanel = new JPanel();
        JTextField searchField = new JTextField(15);
        JButton searchButton = new JButton("搜索");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText().toLowerCase();
                List<warehouseDao> filteredList = new ArrayList<>();

                for (warehouseDao ck : list) {
                    if (ck.getName().toLowerCase().contains(searchTerm) ||
                            ck.getType().toLowerCase().contains(searchTerm) ||
                            ck.getUnit().toLowerCase().contains(searchTerm) ||
                            ck.getRemark().toLowerCase().contains(searchTerm)) {
                        filteredList.add(ck);
                    }
                }

                // 更新表格数据
                updateTable(filteredList);
            }
        });

        searchPanel.add(new JLabel("搜索: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // 设置布局
        jFrame.getContentPane().add(searchPanel, BorderLayout.NORTH);
        jFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        jFrame.setSize(800, 600);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jFrame.setVisible(true);
    }

    // 更新表格数据的方法
    private void updateTable(List<warehouseDao> filteredList) {
        Object[][] newData = new Object[filteredList.size()][index.length];
        for (int i = 0; i < filteredList.size(); i++) {
            warehouseDao ck = filteredList.get(i);
            newData[i][0] = ck.getId();
            newData[i][1] = ck.getName();
            newData[i][2] = ck.getType();
            newData[i][3] = ck.getUnit();
            newData[i][4] = ck.getRemark();
            newData[i][5] = ck.getInventory();
            newData[i][6] = ck.getMin();
            newData[i][7] = ck.getMax();
        }

        // 更新表格模型
        table.setModel(new DefaultTableModel(newData, index));
        table.revalidate(); // 重新验证以更新表格
        table.repaint(); // 重绘表格
    }
}
