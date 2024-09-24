package org.example.仓库管理系统.view.Manager;

import org.example.仓库管理系统.dao.ckDao;
import org.example.仓库管理系统.service.impl.ShowDataInformation;
import org.example.仓库管理系统.view.Inquire.ShowckView1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManagerView extends  JFrame {
    JMenuBar menuBar = new JMenuBar();


    public ManagerView(){
        super("仓库管理");
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu1 = new JMenu("货物信息管理");
        menuBar.add(menu1);

        JMenuItem menuItem1 = new JMenuItem("添加货物");
        menuItem1.addActionListener(new ManagerView.ItemListener());
        menu1.add(menuItem1);

        JMenuItem menuItem2 = new JMenuItem("修改货物");
        menuItem2.addActionListener(new ManagerView.ItemListener());
        menu1.add(menuItem2);

        JMenuItem menuItem3 = new JMenuItem("查询（删除）货物");
        menuItem3.addActionListener(new ManagerView.ItemListener());
        menu1.add(menuItem3);


        JMenu menu2 = new JMenu("仓库设置");
        menuBar.add(menu2);

        JMenuItem menuItema = new JMenuItem("查看设置");
        menuItema.addActionListener(new ManagerView.ItemListener());
        menu2.add(menuItema);

        JMenuItem menuItemb = new JMenuItem("修改设置");
        menuItemb.addActionListener(new ManagerView.ItemListener());
        menu2.add(menuItemb);
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
            if(text.equals("添加货物")){
                new AddckView();
            }else if(text.equals("修改货物")){
                new RevisionckView();
            }else if(text.equals("查询（删除）货物")) {
                List<ckDao> list = ShowDataInformation.getck();
                new ShowckView1(list);
            }else if(text.equals("查看设置")){
                List<ckDao> list=ShowDataInformation.getckSetup();
                new LookSetupView(list);

            }else if(text.equals("修改设置")){
                new RevisionSetupView();
            }

        }
    }

    public static void main(String[] args) {
        new ManagerView();
    }
}
