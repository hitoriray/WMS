package org.example.warehouse.view.Inquire;

import org.example.warehouse.dao.boundDao;
import org.example.warehouse.service.impl.ShowDataInformation;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class OUTBoundView extends JFrame {
    private JTable table;
    private TableRowSorter<TableModel> sorter; // 用于排序和搜索
    private JTextField searchField; // 搜索框
    private int currentPage = 0; // 当前页
    private int rowsPerPage = 10; // 每页显示的行数
    private List<boundDao> allData; // 存储所有数据

    public OUTBoundView(List<boundDao> list) {
        super("查询");
        this.allData = list; // 保存所有数据

        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // 创建搜索框和按钮
        searchField = new JTextField(15); // 搜索框
        JButton searchButton = new JButton("搜索"); // 搜索按钮

        // 搜索按钮的事件处理
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText().trim();
                filterTable(searchText);
            }
        });

        // 将搜索框和按钮添加到面板
        jPanel.add(new JLabel("搜索:"));
        jPanel.add(searchField);
        jPanel.add(searchButton);

        String[] index = {"单号", "物品编号", "物品名称", "出库数量", "类型", "操作人员名称", "出库时间"};
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

        // 使用 TableRowSorter 实现排序功能
        sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

        // 设置滚动面板
        getContentPane().add(jPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // 创建分页控制面板
        JPanel paginationPanel = new JPanel();
        JButton previousButton = new JButton("上一页");
        JButton nextButton = new JButton("下一页");
        JLabel pageLabel = new JLabel();

        // 上一页按钮事件
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPage > 0) {
                    currentPage--;
                    updateTable();
                }
            }
        });

        // 下一页按钮事件
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((currentPage + 1) * rowsPerPage < allData.size()) {
                    currentPage++;
                    updateTable();
                }
            }
        });

        // 添加组件到分页面板
        paginationPanel.add(previousButton);
        paginationPanel.add(nextButton);
        paginationPanel.add(pageLabel);

        getContentPane().add(paginationPanel, BorderLayout.SOUTH);

        // 设置窗口大小和自适应行为
        setSize(800, 600);
        setResizable(true);  // 允许窗口大小调整
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        // 初始化表格数据
        updateTable();
    }

    // 更新表格数据
    private void updateTable() {
        String[] index = {"单号", "物品编号", "物品名称", "出库数量", "类型", "操作人员名称", "出库时间"};
        int start = currentPage * rowsPerPage;
        int end = Math.min(start + rowsPerPage, allData.size());

        Object[][] data = new Object[end - start][index.length];
        for (int i = start; i < end; i++) {
            boundDao bo = allData.get(i);
            data[i - start][0] = bo.getDanhao();
            data[i - start][1] = bo.getId();
            data[i - start][2] = ShowDataInformation.getItemNameById(bo.getId());
            data[i - start][3] = bo.getNumber();
            data[i - start][4] = bo.getBoundtype();
            data[i - start][5] = bo.getName();
            data[i - start][6] = bo.getTime(); // 出库时间列
        }

        // 更新表格模型
        table.setModel(new DefaultTableModel(data, index));
        updatePageLabel(); // 更新页码标签
        sorter.setModel(table.getModel()); // 更新 sorter 的模型
    }

    // 更新页码标签
    private void updatePageLabel() {
        int totalPageCount = (int) Math.ceil((double) allData.size() / rowsPerPage);
        String labelText = "第 " + (currentPage + 1) + " 页 / 共 " + totalPageCount + " 页";
        ((JLabel) ((JPanel) getContentPane().getComponent(2)).getComponent(2)).setText(labelText);
    }

    // 过滤表格数据
    private void filterTable(String searchText) {
        if (searchText.trim().length() == 0) {
            sorter.setRowFilter(null); // 如果搜索框为空，显示所有行
            currentPage = 0; // 重置当前页
            updateTable(); // 更新表格以显示所有数据
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText)); // 忽略大小写的搜索
            // 过滤后更新表格
            int filteredRowCount = sorter.getViewRowCount();
            if (filteredRowCount > 0) {
                currentPage = 0; // 重置当前页
                updateTable();
            } else {
                // 如果没有数据，清空表格
                table.setModel(new DefaultTableModel(new Object[0][0], new String[] {}));
            }
        }
    }
}