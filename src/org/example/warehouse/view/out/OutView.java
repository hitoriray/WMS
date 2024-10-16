package org.example.warehouse.view.out;

import org.example.warehouse.handler.outHander.OutboundHandler;
import org.example.warehouse.view.LoginView;

import javax.swing.*;
import java.awt.*;

public class OutView extends JFrame {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 70, 50));

    JButton SimpleBtn = new JButton("简单物料出库");
    JButton moreBtn = new JButton("多物料出库");
    OutboundHandler outboundHandler;

    public OutView(LoginView loginView) {
        super("出库");

        outboundHandler = new OutboundHandler(loginView);
        Container contentPane = getContentPane();
        SimpleBtn.setFont(new Font("华文行楷", Font.PLAIN, 20));
        moreBtn.setFont(new Font("华文行楷", Font.PLAIN, 20));
        jPanel.add(SimpleBtn);
        jPanel.add(moreBtn);
        SimpleBtn.addActionListener(outboundHandler);
        moreBtn.addActionListener(outboundHandler);
        contentPane.add(jPanel);


        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

    }

}
