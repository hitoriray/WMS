package org.example.warehouse.view.Inquire;

import org.example.warehouse.dao.boundDao;
import org.example.warehouse.dao.warehouseDao;
import org.example.warehouse.service.impl.ShowDataInformation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InquireView extends JFrame {
//    JFrame jFrame = new JFrame();
    JMenuBar menuBar = new JMenuBar();

    public InquireView() {
        super("查询");
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu1 = new JMenu("货物查询");
        menuBar.add(menu1);

        JMenuItem menuItem1 = new JMenuItem("查询所有货物");
        menuItem1.addActionListener(new ItemListener());
        menu1.add(menuItem1);

        JMenuItem menuItem2 = new JMenuItem("查询单个货物");
        menuItem2.addActionListener(new ItemListener());
        menu1.add(menuItem2);

        JMenu menu2 = new JMenu("库存查询");
        menuBar.add(menu2);

        JMenuItem menuItem3 = new JMenuItem("查询剩余库存");
        menuItem3.addActionListener(new ItemListener());
        menu2.add(menuItem3);

        JMenuItem menuItem4 = new JMenuItem("查询入库情况");
        menuItem4.addActionListener(new ItemListener());
        menu2.add(menuItem4);

        JMenuItem menuItem5 = new JMenuItem("查询出库情况");
        menuItem5.addActionListener(new ItemListener());
        menu2.add(menuItem5);

        Font font = new Font("宋体", Font.PLAIN, 20);


        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

    }

    private class ItemListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            JMenuItem menuItem = (JMenuItem) e.getSource();
            String text = menuItem.getText();
            if (text.equals("查询单个货物")) {
                new SingleckView();

            } else if (text.equals("查询所有货物")) {
                List<warehouseDao> list = ShowDataInformation.getck();
                new ShowckView(list);
            } else if (text.equals("查询剩余库存")) {
                List<warehouseDao> list = ShowDataInformation.getck();
                new RemainView(list);
            } else if (text.equals("查询入库情况")) {
                List<boundDao> list1 = ShowDataInformation.getInbound();
                new INBoundView(list1);
            } else if (text.equals("查询出库情况")) {
                List<boundDao> list1 = ShowDataInformation.getOutbound();
                new OUTBoundView(list1);
            }
        }
    }

    public static void main(String[] args) {
        new InquireView();
    }

}
