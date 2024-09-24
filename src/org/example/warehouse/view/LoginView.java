package org.example.warehouse.view;

import org.example.warehouse.handler.LoginHandler;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    JLabel nameLabel = new JLabel("仓库管理系统", JLabel.CENTER);
    SpringLayout springLayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springLayout);
    JLabel usernameLabel = new JLabel("用户名:");
    JTextField userTxt = new JTextField();
    JLabel pwdLabel = new JLabel("密码:");
    JTextField pwdField = new JTextField();
    JButton loginBtn = new JButton("登录");
    JButton resetBtn = new JButton("重置");
    LoginHandler loginHandler;

    public LoginView() {
        super("仓库管理系统");
        loginHandler = new LoginHandler(this);
        Container contentPane = getContentPane();
        Font font1 = new Font("宋体", Font.PLAIN, 50);
        Font font2 = new Font("宋体", Font.PLAIN, 30);
        Dimension dimension = new Dimension(200, 30);
        nameLabel.setFont(font1);
        nameLabel.setPreferredSize(new Dimension(0, 80));
        usernameLabel.setFont(font2);
        userTxt.setPreferredSize(dimension);
        pwdLabel.setFont(font2);
        pwdField.setPreferredSize(dimension);
        loginBtn.setFont(font2);
        resetBtn.setFont(font2);
        nameLabel.setForeground(Color.BLACK);
        userTxt.setCaretColor(Color.RED);
        pwdField.setCaretColor(Color.RED);
        usernameLabel.setForeground(Color.BLACK);
        pwdLabel.setForeground(Color.BLACK);
        //组件加入面板
        centerPanel.add(usernameLabel);
        centerPanel.add(userTxt);
        centerPanel.add(pwdLabel);
        centerPanel.add(pwdField);
        loginBtn.addActionListener(loginHandler);
        loginBtn.addKeyListener(loginHandler);
        getRootPane().setDefaultButton(loginBtn);
        centerPanel.add(loginBtn);
        resetBtn.addActionListener(loginHandler);
        centerPanel.add(resetBtn);

        //spring布局
        Spring child = Spring.sum(Spring.sum(Spring.width(usernameLabel), Spring.width(userTxt)), Spring.constant(20));
        int i = child.getValue() / 2;
        //usernamePanel
        springLayout.putConstraint(SpringLayout.WEST, usernameLabel, -i, SpringLayout.HORIZONTAL_CENTER, centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH, usernameLabel, 20, SpringLayout.NORTH, centerPanel);
        //userTxt
        springLayout.putConstraint(SpringLayout.WEST, userTxt, 20, SpringLayout.EAST, usernameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, userTxt, 0, SpringLayout.NORTH, usernameLabel);
        //pwdLabel
        springLayout.putConstraint(SpringLayout.NORTH, pwdLabel, 20, SpringLayout.SOUTH, usernameLabel);
        springLayout.putConstraint(SpringLayout.EAST, pwdLabel, 0, SpringLayout.EAST, usernameLabel);
        //pwdField
        springLayout.putConstraint(SpringLayout.WEST, pwdField, 20, SpringLayout.EAST, pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH, pwdField, 0, springLayout.NORTH, pwdLabel);
        //loginBtn
        springLayout.putConstraint(SpringLayout.NORTH, loginBtn, 50, springLayout.SOUTH, pwdLabel);
        springLayout.putConstraint(SpringLayout.WEST, loginBtn, 20, springLayout.WEST, pwdLabel);
        //resetBtn
        springLayout.putConstraint(SpringLayout.WEST, resetBtn, 50, SpringLayout.EAST, loginBtn);
        springLayout.putConstraint(SpringLayout.NORTH, resetBtn, 0, SpringLayout.NORTH, loginBtn);

        JLabel imgLabel = new JLabel();
        ImageIcon img = new ImageIcon("src/org/example/warehouse/background.jpg");
        img.setImage(img.getImage().getScaledInstance(2000, 800, Image.SCALE_DEFAULT));
        imgLabel.setIcon(img);
        centerPanel.add(imgLabel);
        usernameLabel.setOpaque(false);
        userTxt.setOpaque(false);
        pwdLabel.setOpaque(false);
        pwdField.setOpaque(false);
        loginBtn.setOpaque(false);
        resetBtn.setOpaque(false);
        contentPane.add(nameLabel, BorderLayout.NORTH);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }


    public JTextField getUserTxt() {
        return userTxt;
    }

    public JTextField getPwdField() {
        return pwdField;
    }

    public static void main(String[] args) {
        new LoginView();


    }
}
