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
        Object source = e.getSource();  // 获取事件源
        String text = "";

        if (source instanceof JButton) {
            // 如果事件源是 JButton
            JButton jButton = (JButton) source;
            text = jButton.getText();
        } else if (source instanceof JMenuItem) {
            // 如果事件源是 JMenuItem
            JMenuItem menuItem = (JMenuItem) source;
            text = menuItem.getText();
        }

        PermissionService permissionService = new PermissionServiceImpl(mainView, loginView);

        switch (text) {
            case "退出":
                handleExit();
                break;
            case "注销":
                handleLogout();
                break;
            case "查询":
                handleQuery(permissionService);
                break;
            case "入库":
                handleInbound(permissionService);
                break;
            case "出库":
                handleOutbound(permissionService);
                break;
            case "仓库管理":
                handleManager(permissionService);
                break;
            case "人员档案管理":
                handleFileManagement(permissionService);
                break;
            case "个人中心":
                new PersonView(loginView);
                break;
        }
    }

    private void handleExit() {
        int i = JOptionPane.showConfirmDialog(null, "是否退出", "退出", JOptionPane.OK_CANCEL_OPTION);
        if (JOptionPane.OK_OPTION == i) {
            System.exit(0);
        }
    }

    private void handleLogout() {
        int i = JOptionPane.showConfirmDialog(null, "是否注销", "注销", JOptionPane.OK_CANCEL_OPTION);
        if (JOptionPane.OK_OPTION == i) {
            new LoginView();
            mainView.dispose();
        }
    }

    private void handleQuery(PermissionService permissionService) {
        if (permissionService.verifyPermission(permissionDao) == 1) {
            new InquireView();
        } else {
            JOptionPane.showMessageDialog(null, "抱歉你暂无此权限", "查询", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void handleInbound(PermissionService permissionService) {
        if (permissionService.verifyInbound(permissionDao) == 1) {
            new InView(loginView);
        } else {
            JOptionPane.showMessageDialog(null, "抱歉你暂无此权限", "入库", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void handleOutbound(PermissionService permissionService) {
        if (permissionService.verifyOutbound(permissionDao) == 1) {
            new OutView(loginView);
        } else {
            JOptionPane.showMessageDialog(null, "抱歉你暂无此权限", "出库", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void handleManager(PermissionService permissionService) {
        if (permissionService.verifyManager(permissionDao) == 1) {
            new ManagerView();
        } else {
            JOptionPane.showMessageDialog(null, "抱歉你暂无此权限", "仓库管理", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void handleFileManagement(PermissionService permissionService) {
        if (permissionService.verifyFile(permissionDao) == 1) {
            new FileView(loginView);
        } else {
            JOptionPane.showMessageDialog(null, "抱歉你暂无此权限", "人员档案管理", JOptionPane.WARNING_MESSAGE);
        }
    }
}
