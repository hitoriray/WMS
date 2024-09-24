package org.example.warehouse.view.in;

import org.example.warehouse.handler.inHandler.SubmitHandler;

import org.example.warehouse.view.LoginView;

import javax.swing.*;
import java.awt.*;

public class SimpleInView extends JFrame {
    JLabel  nameLabel=new JLabel("简单物料入库",JLabel.CENTER);
    SpringLayout springLayout=new SpringLayout();
    JPanel centerPanel=new JPanel(springLayout);
    JLabel idLabel=new JLabel("货物编号:");
    JTextField idTxt=new JTextField();
    JLabel numberLabel=new JLabel("数量:");
    JComboBox numberJB = new JComboBox();

    String[] strArray1 = {"50", "100"};

    {
        numberJB.addItem(strArray1[0]);
        numberJB.addItem(strArray1[1]);
    }
    JButton resetBtn=new JButton("重置");
    JButton TJBtn=new JButton("提交");
    SubmitHandler submitHandler;

    public SimpleInView(LoginView loginView){
        super("简单物料入库");
        submitHandler = new SubmitHandler(this,loginView);
        Container contentPane=getContentPane();
        Font font1 = new Font("楷体", Font.PLAIN, 50);
        Font font2 = new Font("楷体", Font.PLAIN, 30);
        Dimension dimension = new Dimension(200, 30);
        nameLabel.setFont(font1);
        nameLabel.setPreferredSize(new Dimension(0,80));
        idLabel.setFont(font2);
        idTxt.setPreferredSize(dimension);
        numberLabel.setFont(font2);

        resetBtn.setFont(font2);
        TJBtn.setFont(font2);
        //组件加入面板
        centerPanel.add(idLabel);
        centerPanel.add(idTxt);
        centerPanel.add(numberLabel);
        numberJB.setEditable(true);
        centerPanel.add(numberJB);
        TJBtn.addActionListener(submitHandler);
        resetBtn.addActionListener(submitHandler);
        centerPanel.add(resetBtn);
        centerPanel.add(TJBtn);
        //spring布局
        Spring child =Spring.sum(Spring.sum(Spring.width(idLabel),Spring.width(idTxt)),Spring.constant(20));
        int i = child.getValue() / 2;
        //usernamePanel
        springLayout.putConstraint(SpringLayout.WEST,idLabel,-i,SpringLayout.HORIZONTAL_CENTER,centerPanel);
        springLayout.putConstraint(springLayout.NORTH,idLabel,20,SpringLayout.NORTH,centerPanel);
        //userTxt
        springLayout.putConstraint(SpringLayout.WEST,idTxt,20,SpringLayout.EAST,idLabel);
        springLayout.putConstraint(SpringLayout.NORTH,idTxt,0,SpringLayout.NORTH,idLabel);
        //pwdLabel
        springLayout.putConstraint(SpringLayout.NORTH,numberLabel,20,SpringLayout.SOUTH,idLabel);
        springLayout.putConstraint(SpringLayout.EAST,numberLabel,0,SpringLayout.EAST,idLabel);
        //pwdField
        springLayout.putConstraint(SpringLayout.WEST,numberJB,20,SpringLayout.EAST,numberLabel);
        springLayout.putConstraint(SpringLayout.NORTH,numberJB,0,springLayout.NORTH,numberLabel);
        //loginBtn
        springLayout.putConstraint(SpringLayout.NORTH,resetBtn,50,springLayout.SOUTH,numberLabel);
        springLayout.putConstraint(SpringLayout.WEST,resetBtn,20,springLayout.WEST,numberLabel);
        //resetBtn
        springLayout.putConstraint(SpringLayout.WEST,TJBtn,50,SpringLayout.EAST,resetBtn);
        springLayout.putConstraint(SpringLayout.NORTH,TJBtn,0,SpringLayout.NORTH,resetBtn);


        contentPane.add(nameLabel,BorderLayout.NORTH);
        contentPane.add(centerPanel,BorderLayout.CENTER);
        setSize(600,400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

    }


    public String getNumberField() {
        return numberJB.getSelectedItem().toString();
    }
    public JTextField getIdTxt() {
        return idTxt;
    }

}

