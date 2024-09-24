package org.example.仓库管理系统.view.Inquire;

import org.example.仓库管理系统.handler.SingleckHandler;

import javax.swing.*;
import java.awt.*;

public class SingleckView extends JFrame {
    JFrame jFrame=new JFrame("输入");
    JPanel jPanel =new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
    JLabel label=new JLabel("输入名称或关键字：");
    JTextField Txt=new JTextField();
    JButton button=new JButton("确认");
    SingleckHandler singleckhandler;
    public SingleckView(){
        super("查询");
        singleckhandler= new SingleckHandler(this);
        Container contentPane =getContentPane();
        Font font = new Font("宋体", Font.PLAIN, 20);
        Dimension dimension = new Dimension(100, 30);
        label.setFont(font);
        Txt.setPreferredSize(dimension);
        button.setFont(font);
        jPanel.add(label);
        jPanel.add(Txt);
        button.addActionListener(singleckhandler);
        jPanel.add(button);

        contentPane.add(jPanel);
        setSize(400,200);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

    }
    public String getname(){
        String name=Txt.getText();
        return name;
    }

    public static void main(String[] args) {
        new SingleckView();
    }

}
