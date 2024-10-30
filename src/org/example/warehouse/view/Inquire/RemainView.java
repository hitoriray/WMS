package org.example.warehouse.view.Inquire;

import org.example.warehouse.dao.warehouseDao;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class RemainView extends JFrame {
    private JTable table;
    private Object[][] data; // 存储原始数据
    private String[] index = {"编号", "名称", "类型", "单位", "备注", "库存", "最小库存", "最大库存"};
    private int currentPage = 0; // 当前页
    private int rowsPerPage = 10; // 每页显示行数
    private List<warehouseDao> originalList; // 存储原始数据列表

    public RemainView(List<warehouseDao> list) {
        this.originalList = list; // 保存原始数据

        // Initialize the table and set its properties
        table = new JTable();
        table.setEnabled(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getTableHeader().setResizingAllowed(true);
        JScrollPane scrollPane = new JScrollPane(table);

        // Create the search panel
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
                            ck.getType().toLowerCase().contains(searchTerm)) {
                        filteredList.add(ck);
                    }
                }

                // Update table data with filtered results
                updateTableData(filteredList);
            }
        });

        searchPanel.add(new JLabel("搜索: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Create pagination panel
        JPanel paginationPanel = new JPanel();
        JButton previousButton = new JButton("上一页");
        JButton nextButton = new JButton("下一页");
        JLabel pageLabel = new JLabel();

        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPage > 0) {
                    currentPage--;
                    updateTableData(originalList); // Update table data
                }
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((currentPage + 1) * rowsPerPage < originalList.size()) {
                    currentPage++;
                    updateTableData(originalList); // Update table data
                }
            }
        });

        paginationPanel.add(previousButton);
        paginationPanel.add(nextButton);
        paginationPanel.add(pageLabel);

        // Set layout
        getContentPane().add(searchPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(paginationPanel, BorderLayout.SOUTH);

        // Initialize table data
        updateTableData(originalList);

        // Update page label
        updatePageLabel();

        // Window settings
        setResizable(true);
        setMinimumSize(new Dimension(800, 600)); // 设置窗口最小大小
        setMaximumSize(new Dimension(1200, 800)); // 设置窗口最大大小
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
        });

        // 键盘事件处理
        getRootPane().registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // 关闭窗口
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

        pack();
        setVisible(true);
    }

    // Update table data method
    private void updateTableData(List<warehouseDao> list) {
        int start = currentPage * rowsPerPage;
        int end = Math.min(start + rowsPerPage, list.size());
        data = new Object[end - start][index.length];

        for (int i = start; i < end; i++) {
            warehouseDao ck = list.get(i);
            data[i - start][0] = ck.getId();
            data[i - start][1] = ck.getName();
            data[i - start][2] = ck.getType();
            data[i - start][3] = ck.getUnit();
            data[i - start][4] = ck.getRemark();
            data[i - start][5] = ck.getInventory();
            data[i - start][6] = ck.getMin();
            data[i - start][7] = ck.getMax();
        }

        // Update table model
        table.setModel(new DefaultTableModel(data, index));
        table.revalidate(); // Revalidate to update table
        table.repaint(); // Repaint table
        updatePageLabel(); // Update page label
    }

    // Update page label
    private void updatePageLabel() {
        int totalPageCount = (int) Math.ceil((double) originalList.size() / rowsPerPage);
        String labelText = "第 " + (currentPage + 1) + " 页 / 共 " + totalPageCount + " 页";
        ((JLabel) ((JPanel) getContentPane().getComponent(2)).getComponent(2)).setText(labelText);
    }
}