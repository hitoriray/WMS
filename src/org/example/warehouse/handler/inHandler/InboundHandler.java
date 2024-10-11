package org.example.warehouse.handler.inHandler;

import org.example.warehouse.view.LoginView;
import org.example.warehouse.view.in.MoreInView;
import org.example.warehouse.view.in.SimpleInView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InboundHandler implements ActionListener {
    private LoginView loginView;

    // 构造函数不再需要 InView 参数
    public InboundHandler(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 获取事件的源，应该是 JMenuItem
        JMenuItem menuItem = (JMenuItem) e.getSource();
        String text = menuItem.getText(); // 获取菜单项的文本

        // 根据菜单项的文本执行相应的操作
        if (text.equals("简单物料入库")) {
            new SimpleInView(loginView);
        } else if (text.equals("多物料入库")) {
            new MoreInView(loginView);
        }
    }
}
