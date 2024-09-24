package org.example.warehouse.handler;

import org.example.warehouse.dao.PermissionDao;
import org.example.warehouse.dao.UserDao;
import org.example.warehouse.service.UserService;
import org.example.warehouse.service.impl.UserServiceImpl;
import org.example.warehouse.view.LoginView;
import org.example.warehouse.view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class LoginHandler extends KeyAdapter implements ActionListener {
    private LoginView loginView;

    public LoginHandler(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        String text1 = loginView.getUserTxt().getText();
        String text2 = loginView.getPwdField().getText();
        if (text1.equals("") || text2.equals("")) {
            JOptionPane.showMessageDialog(null, "账号密码必填！", "登录", 2);
            return;
        }
        if (text.equals("登录")) {
            UserService userService = new UserServiceImpl();
            UserDao userDao = new UserDao();
            userDao.setName(text1);
            userDao.setPwd(text2);
            PermissionDao permissionDao = new PermissionDao();
            permissionDao.setName(text1);
            boolean flag = userService.yanzhengadmin(userDao);
            if (flag) {
                int s = JOptionPane.showConfirmDialog(null, userDao.getName() + "你好", "登录", JOptionPane.OK_CANCEL_OPTION);
                if (JOptionPane.OK_CANCEL_OPTION != s) {
                    new MainView(loginView);
                    loginView.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(null, "用户名或密码错误", "登录", 2);
            }
        } else if (text.equals("重置")) {
            loginView.getUserTxt().setText("");
            loginView.getPwdField().setText("");
        }
    }
}
