package org.example.warehouse.handler.inHandler;

import org.example.warehouse.view.LoginView;
import org.example.warehouse.view.in.InView;
import org.example.warehouse.view.in.MoreInView;
import org.example.warehouse.view.in.SimpleInView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class INBoundHandler implements ActionListener {
    private InView inView;
    private LoginView loginView;

    public INBoundHandler(InView inView, LoginView loginView) {
        this.loginView = loginView;
        this.inView = inView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if (text.equals("简单物料入库")) {
            new SimpleInView(loginView);
        } else if (text.equals("多物料入库")) {
            new MoreInView(loginView);
        }
    }
}
