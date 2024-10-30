package org.example.warehouse.view.in;

import org.example.warehouse.dao.warehouseDao;
import org.example.warehouse.handler.inHandler.MoreInHandler;
import org.example.warehouse.service.impl.ShowDataInformation;
import org.example.warehouse.view.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MoreInView extends JFrame {
    public DefaultTableModel inventoryTableModel; // 货物信息表格模型
    JLabel label = new JLabel("请选择要入库的货物和数量:    ");
    JTextField searchField = new JTextField(15); // 搜索框
    JButton searchButton = new JButton("搜索"); // 搜索按钮
    JButton sureButton = new JButton("确认添加");
    public JTable inventoryTable; // 用于展示所有货物信息的表格
    MoreInHandler moreInHandler;

    public MoreInView(LoginView loginView) {
        super("多物料入库");
        moreInHandler = new MoreInHandler(this, loginView);

        // 初始化显示所有货物信息的表格
        String[] inventoryIndex = {"选择", "货物编号", "货物名称", "货物类型", "单位", "库存", "最小库存", "最大库存", "入库数量"};
        List<warehouseDao> inventoryList = ShowDataInformation.getck();
        Object[][] inventoryData = new Object[inventoryList.size()][inventoryIndex.length];

        for (int i = 0; i < inventoryList.size(); i++) {
            warehouseDao item = inventoryList.get(i);
            inventoryData[i][0] = false; // 默认不选中
            inventoryData[i][1] = item.getId();
            inventoryData[i][2] = item.getName();
            inventoryData[i][3] = item.getType();
            inventoryData[i][4] = item.getUnit();
            inventoryData[i][5] = item.getInventory();
            inventoryData[i][6] = item.getMin();
            inventoryData[i][7] = item.getMax();
            inventoryData[i][8] = ""; // 输入入库数量的文本框
        }

        inventoryTableModel = new DefaultTableModel(inventoryData, inventoryIndex) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Boolean.class; // 第一列是复选框
                } else {
                    return String.class; // 其他列为字符串
                }
            }
        };

        inventoryTable = new JTable(inventoryTableModel);

        // 设置布局
        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        label.setFont(new Font("楷体", Font.PLAIN, 25));
        sureButton.setFont(new Font("楷体", Font.PLAIN, 18));
        searchField.setFont(new Font("楷体", Font.PLAIN, 18));
        searchButton.setFont(new Font("楷体", Font.PLAIN, 18));

        jPanel.add(label);
        jPanel.add(searchField); // 添加搜索框
        jPanel.add(searchButton); // 添加搜索按钮
        jPanel.add(sureButton);

        JScrollPane inventoryScrollPane = new JScrollPane(inventoryTable);

        sureButton.addActionListener(moreInHandler);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText().toLowerCase();
                filterTable(searchTerm);
            }
        });

        getContentPane().add(jPanel, BorderLayout.NORTH);
        getContentPane().add(inventoryScrollPane, BorderLayout.CENTER); // 添加货物信息表格

        setSize(800, 400); // 调整窗口大小以适应表格
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void filterTable(String searchTerm) {
        // 重新创建表格模型，以便根据搜索条件过滤数据
        List<warehouseDao> inventoryList = ShowDataInformation.getck();
        Object[][] filteredData = inventoryList.stream()
                .filter(item -> item.getName().toLowerCase().contains(searchTerm) ||
                        item.getId().toLowerCase().contains(searchTerm)) // 也可以根据其他字段过滤
                .map(item -> new Object[]{
                        false, // 复选框
                        item.getId(),
                        item.getName(),
                        item.getType(),
                        item.getUnit(),
                        item.getInventory(),
                        item.getMin(),
                        item.getMax(),
                        "" // 输入入库数量的文本框
                })
                .toArray(Object[][]::new);

        inventoryTableModel.setDataVector(filteredData, new String[]{"选择", "货物编号", "货物名称", "货物类型", "单位", "库存", "最小库存", "最大库存", "入库数量"});
    }
}