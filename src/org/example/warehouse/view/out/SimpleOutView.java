package org.example.warehouse.view.out;

import org.example.warehouse.handler.outHander.SubmitOutHandler;
import org.example.warehouse.view.LoginView;

import javax.swing.*;
import java.awt.*;

public class SimpleOutView extends JFrame {
    JLabel nameLabel = new JLabel("简单物料出库", JLabel.CENTER);
    SpringLayout springLayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springLayout);
    JLabel idLabel = new JLabel("货物编号:");
    JTextField idTxt = new JTextField();
    JLabel numberLabel = new JLabel("数量:");
    JComboBox numberJB = new JComboBox();

    String[] strArray1 = {"50", "100"};

    {
        numberJB.addItem(strArray1[0]);
        numberJB.addItem(strArray1[1]);
    }

    JLabel CustomerLabel = new JLabel("客户邮箱:");
    JTextField CustomerTxt = new JTextField();
    JButton resetBtn = new JButton("重置");
    JButton TJBtn = new JButton("提交");

    SubmitOutHandler tjoutHandler;

    public SimpleOutView(LoginView loginView) {
        super("简单物料入库");
        tjoutHandler = new SubmitOutHandler(this, loginView);
        Container contentPane = getContentPane();
        Font font1 = new Font("楷体", Font.PLAIN, 50);
        Font font2 = new Font("楷体", Font.PLAIN, 30);
        Dimension dimension = new Dimension(200, 30);
        nameLabel.setFont(font1);
        nameLabel.setPreferredSize(new Dimension(0, 80));
        idLabel.setFont(font2);
        idTxt.setPreferredSize(dimension);
        CustomerLabel.setFont(font2);
        CustomerTxt.setPreferredSize(dimension);
        numberLabel.setFont(font2);
        TJBtn.setFont(font2);
        resetBtn.setFont(font2);
        //组件加入面板
        centerPanel.add(idLabel);
        centerPanel.add(idTxt);
        centerPanel.add(numberLabel);
        numberJB.setEditable(true);
        centerPanel.add(numberJB);
        centerPanel.add(CustomerLabel);
        centerPanel.add(CustomerTxt);
        resetBtn.addActionListener(tjoutHandler);
        TJBtn.addActionListener(tjoutHandler);
        centerPanel.add(resetBtn);
        centerPanel.add(TJBtn);
        //spring布局
        Spring child = Spring.sum(Spring.sum(Spring.width(idLabel), Spring.width(idTxt)), Spring.constant(20));
        int i = child.getValue() / 2;
        //usernamePanel
        springLayout.putConstraint(SpringLayout.WEST, idLabel, -i, SpringLayout.HORIZONTAL_CENTER, centerPanel);
        springLayout.putConstraint(springLayout.NORTH, idLabel, 20, SpringLayout.NORTH, centerPanel);
        //userTxt
        springLayout.putConstraint(SpringLayout.WEST, idTxt, 20, SpringLayout.EAST, idLabel);
        springLayout.putConstraint(SpringLayout.NORTH, idTxt, 0, SpringLayout.NORTH, idLabel);
        //pwdLabel
        springLayout.putConstraint(SpringLayout.NORTH, numberLabel, 20, SpringLayout.SOUTH, idLabel);
        springLayout.putConstraint(SpringLayout.EAST, numberLabel, 0, SpringLayout.EAST, idLabel);
        //pwdField
        springLayout.putConstraint(SpringLayout.WEST, numberJB, 20, SpringLayout.EAST, numberLabel);
        springLayout.putConstraint(SpringLayout.NORTH, numberJB, 0, springLayout.NORTH, numberLabel);
        //CustomerLabel
        springLayout.putConstraint(SpringLayout.NORTH, CustomerLabel, 20, SpringLayout.SOUTH, numberLabel);
        springLayout.putConstraint(SpringLayout.EAST, CustomerLabel, 0, SpringLayout.EAST, numberLabel);
        //CustomerField
        springLayout.putConstraint(SpringLayout.WEST, CustomerTxt, 20, SpringLayout.EAST, CustomerLabel);
        springLayout.putConstraint(SpringLayout.NORTH, CustomerTxt, 0, springLayout.NORTH, CustomerLabel);
        //resetBtn
        springLayout.putConstraint(SpringLayout.NORTH, resetBtn, 50, springLayout.SOUTH, CustomerLabel);
        springLayout.putConstraint(SpringLayout.WEST, resetBtn, 40, springLayout.WEST, CustomerLabel);
        //loginBtn
        springLayout.putConstraint(SpringLayout.NORTH, TJBtn, 0, springLayout.NORTH, resetBtn);
        springLayout.putConstraint(SpringLayout.WEST, TJBtn, 20, springLayout.EAST, resetBtn);

        contentPane.add(nameLabel, BorderLayout.NORTH);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        setSize(600, 400);
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

    public String getCustomer() {
        return CustomerTxt.getText().toString();
    }
}