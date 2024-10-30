package org.example.warehouse.view.out;

import org.example.warehouse.dao.warehouseDao;
import org.example.warehouse.handler.outHander.MoreOutHandler;
import org.example.warehouse.service.impl.ShowDataInformation;
import org.example.warehouse.view.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class MoreOutView extends JFrame {
    public DefaultTableModel tableModel;
    JLabel label = new JLabel("请选择要出库的物料并输入数量:    ");
    JButton sureButton = new JButton("确认出库");
    JTextField searchField = new JTextField(20); // 新增搜索框
    JButton searchButton = new JButton("搜索"); // 新增搜索按钮

    public JTable table;
    MoreOutHandler moreOutHandler;

    public MoreOutView(LoginView loginView) {
        super("多物料出库");
        moreOutHandler = new MoreOutHandler(this, loginView);

        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        label.setFont(new Font("楷体", Font.PLAIN, 25));
        searchField.setFont(new Font("楷体", Font.PLAIN, 18));
        searchButton.setFont(new Font("楷体", Font.PLAIN, 18));
        sureButton.setFont(new Font("楷体", Font.PLAIN, 18));

        jPanel.add(label);
        jPanel.add(searchField);
        jPanel.add(searchButton);
        jPanel.add(sureButton);

        // 定义表头
        String[] columnNames = {"选择", "物料编号", "物料名称", "物料单位", "物料类型", "最小库存", "最大库存", "库存数量", "出库数量"};
        tableModel = new DefaultTableModel(null, columnNames) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : super.getColumnClass(columnIndex);
            }
        };
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        loadMaterials(ShowDataInformation.getck());

        sureButton.addActionListener(moreOutHandler);

        // 添加搜索按钮的事件处理
        searchButton.addActionListener(e -> filterMaterials(searchField.getText().trim()));

        // 监听搜索框的键盘输入
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterMaterials(searchField.getText().trim());
            }
        });

        getContentPane().add(jPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        setSize(900, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void loadMaterials(List<warehouseDao> materialsData) {
        tableModel.setRowCount(0);
        for (warehouseDao w : materialsData) {
            tableModel.addRow(new Object[]{false, w.getId(), w.getName(), w.getUnit(), w.getType(), w.getMin(), w.getMax(), w.getInventory(), 0});
        }
    }

    // 根据搜索框中的关键字过滤材料
    private void filterMaterials(String keyword) {
        List<warehouseDao> materialsData = ShowDataInformation.getck(); // 重新获取所有材料
        tableModel.setRowCount(0); // 清空当前表格
        for (warehouseDao w : materialsData) {
            if (w.getName().contains(keyword) || w.getId().contains(keyword)) { // 根据名称或编号过滤
                tableModel.addRow(new Object[]{false, w.getId(), w.getName(), w.getUnit(), w.getType(), w.getMin(), w.getMax(), w.getInventory(), 0});
            }
        }
    }
}