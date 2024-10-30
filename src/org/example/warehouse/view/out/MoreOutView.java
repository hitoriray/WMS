package org.example.warehouse.view.out;

import org.example.warehouse.dao.warehouseDao;
import org.example.warehouse.handler.outHander.MoreOutHandler;
import org.example.warehouse.service.impl.ShowDataInformation;
import org.example.warehouse.view.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MoreOutView extends JFrame {
    public DefaultTableModel tableModel;
    JLabel label = new JLabel("请选择要出库的物料并输入数量:    ");
    JButton sureButton = new JButton("确认出库");
    JButton queryButton = new JButton("查询货物信息");

    public JTable table;
    MoreOutHandler moreOutHandler;

    public MoreOutView(LoginView loginView) {
        super("多物料出库");
        moreOutHandler = new MoreOutHandler(this, loginView);

        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        label.setFont(new Font("楷体", Font.PLAIN, 25));
        queryButton.setFont(new Font("楷体", Font.PLAIN, 18));
        sureButton.setFont(new Font("楷体", Font.PLAIN, 18));
        jPanel.add(label);
        jPanel.add(sureButton);
        jPanel.add(queryButton);

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
        queryButton.addActionListener(moreOutHandler);

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
}