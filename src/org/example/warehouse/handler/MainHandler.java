package org.example.warehouse.handler;

import org.example.warehouse.dao.PermissionDao;
import org.example.warehouse.service.PermissionService;
import org.example.warehouse.service.impl.PermissionServiceImpl;
import org.example.warehouse.view.*;
import org.example.warehouse.view.File.FileView;
import org.example.warehouse.view.Inquire.InquireView;
import org.example.warehouse.view.Manager.ManagerView;
import org.example.warehouse.view.Person.PersonView;
import org.example.warehouse.view.in.InView;
import org.example.warehouse.view.out.OutView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class MainHandler extends KeyAdapter implements ActionListener {
    private MainView mainView;
    private LoginView loginView;

    public MainHandler(MainView mainView, LoginView loginView) {
        this.mainView = mainView;
        this.loginView = loginView;
    }

    PermissionDao permissionDao = new PermissionDao();

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        PermissionService permissionService = new PermissionServiceImpl(mainView, loginView);
        if (text.equals("退出")) {
            int i = JOptionPane.showConfirmDialog(null, "是否退出", "退出", JOptionPane.OK_CANCEL_OPTION);
            if (JOptionPane.OK_CANCEL_OPTION == i) {
                return;
            } else {
                System.exit(0);
            }
        } else if (text.equals("注销")) {
            int i = JOptionPane.showConfirmDialog(null, "是否注销", "注销", JOptionPane.OK_CANCEL_OPTION);
            if (JOptionPane.OK_CANCEL_OPTION == i) {
                return;
            }
            new LoginView();
            mainView.dispose();
        } else if (text.equals("查询")) {
            if (permissionService.yanzhengInquire(permissionDao) == 1) {
                new InquireView();
            } else {
                JOptionPane.showMessageDialog(null, "抱歉你暂无此权限", "查询", 2);
            }
        } else if (text.equals("入库")) {
            if (permissionService.yanzhengInbound(permissionDao) == 1) {
                new InView(loginView);
            } else {
                JOptionPane.showMessageDialog(null, "抱歉你暂无此权限", "入库", 2);
            }
        } else if (text.equals("出库")) {
            if (permissionService.yanzhengOutbound(permissionDao) == 1) {
                new OutView(loginView);
            } else {
                JOptionPane.showMessageDialog(null, "抱歉你暂无此权限", "出库", 2);
            }
        } else if (text.equals("仓库管理")) {
            if (permissionService.yanzhengManager(permissionDao) == 1) {
                new ManagerView();
            } else {
                JOptionPane.showMessageDialog(null, "抱歉你暂无此权限", "仓库管理", 2);
            }
        } else if (text.equals("人员档案管理")) {
            if (permissionService.yanzhengFile(permissionDao) == 1) {
                new FileView(loginView);
            } else {
                JOptionPane.showMessageDialog(null, "抱歉你暂无此权限", "人员档案管理", 2);
            }
        } else if (text.equals("个人中心")) {
            new PersonView(loginView);
        }
    }
}
