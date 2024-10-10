package org.example.warehouse.view.Inquire;

import org.example.warehouse.dao.warehouseDao;
import org.example.warehouse.handler.ckHandler.DeleteItemHandler;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShowckView1 extends JFrame {

    JLabel label = new JLabel("请输入需要删除的货物编号：");
    JTextField Txt = new JTextField();
    JButton jButton = new JButton("删除");
    DeleteItemHandler deleteItemHandler;

    public String getTxt() {
        return Txt.getText();
    }

    public ShowckView1(List<warehouseDao> list) {
        super("查询");
        deleteItemHandler = new DeleteItemHandler(this);
        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        Font font = new Font("宋体", Font.PLAIN, 20);
        Txt.setPreferredSize(new Dimension(150, 30));
        jButton.setFont(new Font("楷体", Font.PLAIN, 10));
        jPanel.add(label);
        jPanel.add(Txt);
        jPanel.add(jButton);
        jButton.addActionListener(deleteItemHandler);

        //table添加
        String[] index = {"编号", "名称", "类型", "单位", "品牌", "剩余库存", "最小值", "最大值"};
        Object[][] data = new Object[list.size()][index.length];
        System.out.println("size:" + list.size());
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
        System.out.println("2222222222222");
        JTable table = new JTable(data, index);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setEnabled(false);
        table.getTableHeader().setResizingAllowed(false);
        getContentPane().add(jPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        setSize(800, 600);
        setResizable(false);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);


    }

}
