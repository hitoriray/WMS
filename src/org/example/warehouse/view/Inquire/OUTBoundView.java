package org.example.warehouse.view.Inquire;

import org.example.warehouse.dao.boundDao;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

public class OUTBoundView extends JFrame {
    JFrame jFrame = new JFrame("查询");

    public OUTBoundView(List<boundDao> list) {
        super("查询");

        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        String[] index = {"单号", "物品编号", "出库数量", "类型", "操作人员名称", "出库时间"};
        Object[][] data = new Object[list.size()][index.length];
        System.out.println("size:" + list.size());
        for (int i = 0; i < list.size(); i++) {
            boundDao bo = list.get(i);
            data[i][0] = bo.getDanhao();
            data[i][1] = bo.getId();
            data[i][2] = bo.getNumber();
            data[i][3] = bo.getBoundtype();
            data[i][4] = bo.getName();
            data[i][5] = bo.getTime(); // 出库时间列
        }

        JTable table = new JTable(data, index);
        JScrollPane scrollPane = new JScrollPane(table);

        // 关闭自动调整列宽
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // 启用列宽调整
        table.getTableHeader().setResizingAllowed(true);

        // 设置每一列的首选宽度
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(150);  // 可以根据需要设置合适的宽度
        }

        // 使表格宽度自适应窗口大小
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);  // 自动调整列宽
        table.getTableHeader().setReorderingAllowed(true);  // 允许拖动列头进行排序

        // 使用 TableRowSorter 实现排序功能
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

        // 设置出库时间列的比较器，按照升序或降序排列
        sorter.setComparator(5, (String time1, String time2) -> {
            return time1.compareTo(time2); // 时间按照字典顺序比较
        });

        // 设置滚动面板
        jFrame.getContentPane().add(jPanel, BorderLayout.NORTH);
        jFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // 设置窗口大小和自适应行为
        jFrame.setSize(800, 600);
        jFrame.setResizable(true);  // 允许窗口大小调整
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
