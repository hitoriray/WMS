package org.example.warehouse.view.Manager;

import org.example.warehouse.dao.warehouseDao;
import org.example.warehouse.service.impl.RevisionItemServiceImpl;
import org.example.warehouse.service.impl.ShowDataInformation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RevisionItemView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField searchField; // 搜索框
    private JButton searchButton; // 搜索按钮
    private JButton confirmButton; // 确认修改按钮

    public RevisionItemView() {
        super("修改货物信息");
        setTitle("Warehouse Information");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 创建表格模型
        String[] columnNames = {"ID", "名称", "类型", "单位", "备注", "库存", "最小库存", "最大库存"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // 允许所有单元格编辑
                return true;
            }
        };
        table = new JTable(tableModel);

        // 添加滚动面板
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // 创建搜索框和搜索按钮
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(15); // 搜索框
        searchButton = new JButton("搜索"); // 搜索按钮
        confirmButton = new JButton("确认修改"); // 确认修改按钮

        // 设置按钮大小与搜索框一致
        searchButton.setPreferredSize(new Dimension(100, 30));
        confirmButton.setPreferredSize(new Dimension(100, 30));
        confirmButton.addActionListener(new ConfirmButtonListener());

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTable(searchField.getText().trim());
            }
        });

        searchPanel.add(new JLabel("搜索:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(confirmButton); // 将确认修改按钮添加到同一行

        add(searchPanel, BorderLayout.NORTH); // 将搜索面板添加到窗口顶部

        // 加载货物信息
        loadWarehouseData();
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void loadWarehouseData() {
        List<warehouseDao> warehouseList = ShowDataInformation.getck();
        for (warehouseDao item : warehouseList) {
            Object[] row = {
                    item.getId(),
                    item.getName(),
                    item.getType(),
                    item.getUnit(),
                    item.getRemark(),
                    item.getInventory(),
                    item.getMin(),
                    item.getMax()
            };
            tableModel.addRow(row);
        }
    }

    private void filterTable(String searchTerm) {
        // 重新创建表格模型，以便根据搜索条件过滤数据
        List<warehouseDao> warehouseList = ShowDataInformation.getck(); // 重新获取所有材料
        tableModel.setRowCount(0); // 清空当前表格
        for (warehouseDao item : warehouseList) {
            if (item.getName().contains(searchTerm) || item.getId().contains(searchTerm)) { // 根据名称或ID过滤
                Object[] row = {
                        item.getId(),
                        item.getName(),
                        item.getType(),
                        item.getUnit(),
                        item.getRemark(),
                        item.getInventory(),
                        item.getMin(),
                        item.getMax()
                };
                tableModel.addRow(row);
            }
        }
    }

    private class ConfirmButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 遍历表格中的数据并提交修改
            int rowCount = tableModel.getRowCount();
            System.out.println("rowCount: " + rowCount);
            for (int i = 0; i < rowCount; i++) {
                warehouseDao updatedWarehouse = new warehouseDao();
                updatedWarehouse.setId(tableModel.getValueAt(i, 0).toString());
                updatedWarehouse.setName(tableModel.getValueAt(i, 1).toString());
                updatedWarehouse.setType(tableModel.getValueAt(i, 2).toString());
                updatedWarehouse.setUnit(tableModel.getValueAt(i, 3).toString());
                updatedWarehouse.setRemark(tableModel.getValueAt(i, 4).toString());
                updatedWarehouse.setInventory(tableModel.getValueAt(i, 5).toString());
                updatedWarehouse.setMin(tableModel.getValueAt(i, 6).toString());
                updatedWarehouse.setMax(tableModel.getValueAt(i, 7).toString());

                // 调用修改方法
                RevisionItemServiceImpl revisionService = new RevisionItemServiceImpl();
                boolean success = revisionService.revisionItemAll(updatedWarehouse);

                if (!success) {
                    JOptionPane.showMessageDialog(RevisionItemView.this, "ID: " + updatedWarehouse.getId() + " 的更新失败");
                }
            }
            JOptionPane.showMessageDialog(RevisionItemView.this, "所有更新已完成！");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RevisionItemView ui = new RevisionItemView();
            ui.setVisible(true);
        });
    }
}