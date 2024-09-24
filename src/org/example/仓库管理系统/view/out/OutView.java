package org.example.仓库管理系统.view.out;

import org.example.仓库管理系统.handler.outHander.OUTboundHandler;
import org.example.仓库管理系统.view.LoginView;

import javax.swing.*;
import java.awt.*;

public class OutView extends  JFrame {
    JPanel jPanel =new JPanel(new FlowLayout(FlowLayout.CENTER,70,50));

    JButton SimpleBtn =new JButton("简单物料出库");
    JButton moreBtn =new JButton("多物料出库");
    OUTboundHandler outboundHandler;
    public OutView(LoginView loginView){
        super("出库");

        outboundHandler=new OUTboundHandler(this,loginView);
        Container contentPane =getContentPane();
        SimpleBtn.setFont(new Font("华文行楷",Font.PLAIN,20));
        moreBtn.setFont(new Font("华文行楷",Font.PLAIN,20));
        jPanel.add(SimpleBtn);
        jPanel.add(moreBtn);
        SimpleBtn.addActionListener(outboundHandler);
        moreBtn .addActionListener(outboundHandler);
        contentPane.add(jPanel);


        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

    }

}
