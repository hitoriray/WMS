package org.example.仓库管理系统.view.in;

import org.example.仓库管理系统.handler.inHandler.INBoundHandler;
import org.example.仓库管理系统.view.LoginView;

import javax.swing.*;
import java.awt.*;

public class InView extends JFrame {
    JPanel jPanel =new JPanel(new FlowLayout(FlowLayout.CENTER,70,50));

    JButton SimpleBtn =new JButton("简单物料入库");
    JButton moreBtn =new JButton("多物料入库");
    INBoundHandler inBoundHandler;

    public InView(LoginView loginView){
        super("入库");

        inBoundHandler=new INBoundHandler(this,loginView);
        Container contentPane =getContentPane();
        SimpleBtn.setFont(new Font("华文行楷",Font.PLAIN,20));
        moreBtn.setFont(new Font("华文行楷",Font.PLAIN,20));
        jPanel.add(SimpleBtn);
        jPanel.add(moreBtn);
        SimpleBtn.addActionListener(inBoundHandler);
        moreBtn .addActionListener(inBoundHandler);
        contentPane.add(jPanel);


        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

    }

}