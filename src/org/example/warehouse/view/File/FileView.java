package org.example.warehouse.view.File;

import org.example.warehouse.dao.UserTotalDao;
import org.example.warehouse.service.impl.ShowDataInformation;

import org.example.warehouse.view.File.Permission.PermissionView;
import org.example.warehouse.view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FileView extends JFrame {
    JFrame jFrame = new JFrame();
    private LoginView loginView;

    public FileView(LoginView loginView) {
        super("人员档案管理");
        this.loginView = loginView;

        setTitle("人员档案管理");
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("人员信息管理");
        menuBar.add(menu);

        JMenuItem menuItem1 = new JMenuItem("查询（删除）人员信息");
        menuItem1.addActionListener(new ItemListener());
        menu.add(menuItem1);

        JMenuItem MenuItem2 = new JMenuItem("添加人员信息");
        MenuItem2.addActionListener(new ItemListener());
        menu.add(MenuItem2);

        JMenuItem menuItem3 = new JMenuItem("修改人员信息");
        menuItem3.addActionListener(new ItemListener());
        menu.add(menuItem3);

        JMenu menu2 = new JMenu("人员权限管理");
        menuBar.add(menu2);

        JMenuItem menuItem4 = new JMenuItem("查看（修改）人员权限");
        menuItem4.addActionListener(new ItemListener());
        menu2.add(menuItem4);
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);


    }

    private class ItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JMenuItem menuItem = (JMenuItem) e.getSource();
            String text = menuItem.getText();
            if (text.equals("查询（删除）人员信息")) {
                List<UserTotalDao> list = ShowDataInformation.getInformation();
                new InquirePersonView(list);
            } else if (text.equals("查看（修改）人员权限")) {
                new PermissionView();
            } else if (text.equals("添加人员信息")) {
                new AddPersonView();
            } else if (text.equals("修改人员信息")) {
                new RevisionPersonView(loginView);
            }

        }
    }

}
