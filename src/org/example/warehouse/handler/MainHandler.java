package org.example.warehouse.handler;

import org.example.warehouse.view.*;
import org.example.warehouse.view.Person.PersonView;
import org.example.warehouse.view.Report.ReportView;

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

        System.out.println("text:"+text);
        switch (text) {
            case "退出":
                handleExit();
                break;
            case "注销":
                handleLogout();
                break;
            case "修改密码":
                new PersonView(loginView);
                break;
            case "打印报表":
                new ReportView();
                break;
            default:
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

}
