package org.example.warehouse.handler.outHander;

import org.example.warehouse.view.LoginView;
import org.example.warehouse.view.out.MoreOutView;
import org.example.warehouse.view.out.OutView;
import org.example.warehouse.view.out.SimpleOutView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OUTboundHandler implements ActionListener {
    private OutView outView;
    public LoginView loginView;

    public OUTboundHandler(OutView outView, LoginView loginView) {
        this.outView = outView;
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if (text.equals("简单物料出库")) {
            new SimpleOutView(loginView);
        } else if (text.equals("多物料出库")) {
            new MoreOutView(loginView);
        }
    }
}
