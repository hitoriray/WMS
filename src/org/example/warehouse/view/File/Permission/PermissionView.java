package org.example.warehouse.view.File.Permission;

import org.example.warehouse.handler.inquireHandler.InquirePermisionHandler;

import javax.swing.*;
import java.awt.*;

public class PermissionView  extends  JFrame{
    JPanel jPanel =new JPanel(new FlowLayout(FlowLayout.CENTER,70,50));
    JButton inquireBtn =new JButton("查看（修改）权限");
    JLabel label =new JLabel("温馨提示：输入0为无权限,输入1为有权限");
    JFrame jFrame=new JFrame();
    InquirePermisionHandler inquirePermisionHandler;
    public PermissionView(){
        super("权限设置");
        inquirePermisionHandler=new InquirePermisionHandler(this);
        Container contentPane =getContentPane();
        inquireBtn.setFont(new Font("华文行楷",Font.PLAIN,20));
        label.setFont(new Font("华文行楷",Font.PLAIN,20));
        jPanel.add(inquireBtn);
        jPanel.add(label);
        inquireBtn.addActionListener(inquirePermisionHandler);
        contentPane.add(jPanel);
        setSize(600,400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new PermissionView();
    }
}
