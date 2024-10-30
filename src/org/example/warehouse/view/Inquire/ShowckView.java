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
    private JFrame jFrame = new JFrame("查询");
    private JTable table;
    private String[] index = {"编号", "名称", "类型", "单位", "品牌", "剩余库存", "最小值", "最大值"};
    private int currentPage = 0; // 当前页
    private int rowsPerPage = 10; // 每页显示的行数
    private List<warehouseDao> originalList; // 原始数据列表

    public ShowckView(List<warehouseDao> list) {
        this.originalList = list; // 保存原始数据

        // 创建表格
        table = new JTable();
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

                for (warehouseDao ck : originalList) {
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

        // 分页控制面板
        JPanel paginationPanel = new JPanel();
        JButton previousButton = new JButton("上一页");
        JButton nextButton = new JButton("下一页");
        JLabel pageLabel = new JLabel();

        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPage > 0) {
                    currentPage--;
                    updateTable(originalList); // 更新表格数据
                }
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((currentPage + 1) * rowsPerPage < originalList.size()) {
                    currentPage++;
                    updateTable(originalList); // 更新表格数据
                }
            }
        });

        paginationPanel.add(previousButton);
        paginationPanel.add(nextButton);
        paginationPanel.add(pageLabel);

        // 设置布局
        jFrame.getContentPane().add(searchPanel, BorderLayout.NORTH);
        jFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        jFrame.getContentPane().add(paginationPanel, BorderLayout.SOUTH);

        // 设置窗口属性
        jFrame.setSize(800, 600);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jFrame.setVisible(true);

        // 初始表格数据
        updateTable(originalList);
    }

    // 更新表格数据的方法
    private void updateTable(List<warehouseDao> list) {
        int totalItems = list.size();
        int start = currentPage * rowsPerPage;
        int end = Math.min(start + rowsPerPage, totalItems);

        // 使用子列表来获取当前页的数据
        List<warehouseDao> currentPageData = list.subList(start, end);

        Object[][] newData = new Object[currentPageData.size()][index.length];
        for (int i = 0; i < currentPageData.size(); i++) {
            warehouseDao ck = currentPageData.get(i);
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

        // 更新页数标签
        updatePageLabel((JLabel) ((JPanel) jFrame.getContentPane().getComponent(2)).getComponent(2));
    }

    // 更新页数标签的方法
    private void updatePageLabel(JLabel pageLabel) {
        int totalPageCount = (int) Math.ceil((double) originalList.size() / rowsPerPage);
        String labelText = "第 " + (currentPage + 1) + " 页 / 共 " + totalPageCount + " 页";
        pageLabel.setText(labelText);
    }
}