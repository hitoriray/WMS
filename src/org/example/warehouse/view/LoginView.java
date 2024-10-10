package org.example.warehouse.view;

import org.example.warehouse.handler.LoginHandler;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    JLabel nameLabel = new JLabel("仓库管理系统", JLabel.CENTER);
    SpringLayout springLayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springLayout) {
        // 覆盖paintComponent方法来绘制背景图片
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon img = new ImageIcon("src/org/example/warehouse/BG.jpg");  // 确保路径正确
            g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), this);  // 自动适应JPanel大小
        }
    };
    JLabel usernameLabel = new JLabel("用户名:");
    JTextField userTxt = new JTextField();
    JLabel pwdLabel = new JLabel("密码:");
    JPasswordField pwdField = new JPasswordField();  // 使用JPasswordField替代JTextField来输入密码
    JButton loginBtn = new JButton("登录");
    JButton resetBtn = new JButton("重置");
    LoginHandler loginHandler;

    public LoginView() {
        super("仓库管理系统");

        loginHandler = new LoginHandler(this);
        Container contentPane = getContentPane();
        Font font1 = new Font("宋体", Font.PLAIN, 50);
        Font font2 = new Font("宋体", Font.PLAIN, 30);
        Dimension dimension = new Dimension(300, 40);  // 更大的输入框尺寸

        // 设置组件样式
        nameLabel.setFont(font1);
        nameLabel.setPreferredSize(new Dimension(0, 80));
        usernameLabel.setFont(font2);
        userTxt.setPreferredSize(dimension);
        pwdLabel.setFont(font2);
        pwdField.setPreferredSize(dimension);  // 设置密码输入框尺寸
        loginBtn.setFont(font2);
        resetBtn.setFont(font2);

        // 设置颜色和光标颜色
        nameLabel.setForeground(Color.BLACK);
        userTxt.setCaretColor(Color.RED);
        pwdField.setCaretColor(Color.RED);
        usernameLabel.setForeground(Color.BLACK);
        pwdLabel.setForeground(Color.BLACK);

        // 将组件加入面板
        centerPanel.add(usernameLabel);
        centerPanel.add(userTxt);
        centerPanel.add(pwdLabel);
        centerPanel.add(pwdField);
        centerPanel.add(loginBtn);
        centerPanel.add(resetBtn);

        // 添加事件监听
        loginBtn.addActionListener(loginHandler);
        loginBtn.addKeyListener(loginHandler);
        resetBtn.addActionListener(loginHandler);
        getRootPane().setDefaultButton(loginBtn);

        // 使用SpringLayout布局
        Spring child = Spring.sum(Spring.sum(Spring.width(usernameLabel), Spring.width(userTxt)), Spring.constant(20));
        int i = child.getValue() / 2;
        // 设置布局规则
        springLayout.putConstraint(SpringLayout.WEST, usernameLabel, -i, SpringLayout.HORIZONTAL_CENTER, centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH, usernameLabel, 20, SpringLayout.NORTH, centerPanel);
        springLayout.putConstraint(SpringLayout.WEST, userTxt, 20, SpringLayout.EAST, usernameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, userTxt, 0, SpringLayout.NORTH, usernameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, pwdLabel, 20, SpringLayout.SOUTH, usernameLabel);
        springLayout.putConstraint(SpringLayout.EAST, pwdLabel, 0, SpringLayout.EAST, usernameLabel);
        springLayout.putConstraint(SpringLayout.WEST, pwdField, 20, SpringLayout.EAST, pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH, pwdField, 0, SpringLayout.NORTH, pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH, loginBtn, 50, SpringLayout.SOUTH, pwdLabel);
        springLayout.putConstraint(SpringLayout.WEST, loginBtn, 20, SpringLayout.WEST, pwdLabel);
        springLayout.putConstraint(SpringLayout.WEST, resetBtn, 50, SpringLayout.EAST, loginBtn);
        springLayout.putConstraint(SpringLayout.NORTH, resetBtn, 0, SpringLayout.NORTH, loginBtn);

        // 添加组件
        contentPane.add(nameLabel, BorderLayout.NORTH);
        contentPane.add(centerPanel, BorderLayout.CENTER);  // 将包含背景的panel作为center panel

        // 设置窗口属性
        setSize(1200, 800);  // 设置窗口大小为1200x800
        setLocationRelativeTo(null);  // 窗口居中显示
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);  // 允许调整窗口大小
        setVisible(true);
    }

    public JTextField getUserTxt() {
        return userTxt;
    }

    public JPasswordField getPwdField() {  // 改为返回JPasswordField
        return pwdField;
    }

    public static void main(String[] args) {
        new LoginView();
    }
}
