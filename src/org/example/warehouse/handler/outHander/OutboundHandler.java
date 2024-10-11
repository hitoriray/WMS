package org.example.warehouse.handler.outHander;

import org.example.warehouse.view.LoginView;
import org.example.warehouse.view.out.MoreOutView;
import org.example.warehouse.view.out.OutView;
import org.example.warehouse.view.out.SimpleOutView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OutboundHandler implements ActionListener {
    public LoginView loginView;

    public OutboundHandler(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 获取事件的源，应该是 JMenuItem
        JMenuItem menuItem = (JMenuItem) e.getSource();
        String text = menuItem.getText(); // 获取菜单项的文本
        if (text.equals("简单物料出库")) {
            new SimpleOutView(loginView);
        } else if (text.equals("多物料出库")) {
            new MoreOutView(loginView);
        }
    }
}
