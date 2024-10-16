package org.example.warehouse.view.File;

import org.example.warehouse.dao.UserTotalDao;
import org.example.warehouse.handler.inquireHandler.InquirePersonHandler;
import org.example.warehouse.service.impl.ShowDataInformation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class InquirePersonView extends JFrame {
    //    JFrame jFrame=new JFrame("人员信息");
    JTextField inTxt = new JTextField();
    public DefaultTableModel tableModel;

    JButton inBtn = new JButton("查询");
    JLabel delete = new JLabel("                提示：双击可删除");
    InquirePersonHandler inquirePersonHandler;

    public InquirePersonView(List<UserTotalDao> list) {
        super("人员信息");
        inquirePersonHandler = new InquirePersonHandler(this);
        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inTxt.setPreferredSize(new Dimension(150, 30));

        inBtn.setFont(new Font("楷体", Font.PLAIN, 18));
        delete.setFont(new Font("楷体", Font.PLAIN, 18));
        delete.setForeground(Color.RED);
        jPanel.add(inTxt);
        jPanel.add(inBtn);
        jPanel.add(delete);
        inBtn.addActionListener(inquirePersonHandler);

        //table添加
        String[] index = {"姓名", "身份证", "出生日期", "性别", "籍贯", "家庭住址", "类型", "联系电话"};
        final Object[][][] data = {new Object[list.size()][index.length]};
        for (int i = 0; i < list.size(); i++) {
            UserTotalDao userTotalDao = list.get(i);
            data[0][i][0] = userTotalDao.getName();
            data[0][i][1] = userTotalDao.getIDnumber();
            data[0][i][2] = userTotalDao.getDate();
            data[0][i][3] = userTotalDao.getGender();
            data[0][i][4] = userTotalDao.getOrigin();
            data[0][i][5] = userTotalDao.getAddress();
            data[0][i][6] = userTotalDao.getType();
            data[0][i][7] = userTotalDao.getPhone();
        }
        tableModel = new DefaultTableModel(data[0], index);
        JTable table = new JTable(tableModel);//创建指定表格模型的表格
        JScrollPane scrollPane = new JScrollPane(table);
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = table.getSelectedRow();
                    int col = table.getColumnModel().getColumnIndexAtX(e.getX());
                    int i = JOptionPane.showConfirmDialog(null, "是否删除？", "删除", JOptionPane.OK_CANCEL_OPTION);
                    if (JOptionPane.OK_CANCEL_OPTION == i) {
                        System.out.println("取消删除行：" + selectedRow);
                    } else {
                        tableModel.removeRow(selectedRow);
                        String id = data[0][selectedRow][0].toString();
                        ShowDataInformation.deleteInformation(id);
                        data[0] = removeRow(data[0], selectedRow);
                    }
                }
            }
        });
        table.getTableHeader().setResizingAllowed(false);
        getContentPane().add(jPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private Object[][] removeRow(Object[][] source, int row) {
        Object[][] result = new Object[source.length - 1][source[0].length];
        int idx = 0;
        for (int i = 0; i < source.length; i++) {
            if (i != row) {
                result[idx++] = source[i];
            }
        }
        return result;
    }

    public JTextField getInTxt() {
        return inTxt;
    }
}
