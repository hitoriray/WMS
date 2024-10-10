package org.example.warehouse.view;

import org.example.warehouse.handler.MainHandler;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    private JMenuBar menuBar;
    private JMenu menuOperations, menuSettings, menuAccount;
    private JMenuItem inquireItem, inItem, outItem, managerItem, fileItem, personItem, logoutItem, exitItem;
    private MainHandler mainHandler;

    public MainView(LoginView loginView) {
        super("仓库管理系统");

        mainHandler = new MainHandler(this, loginView);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // 设置菜单栏
        menuBar = new JMenuBar();

        // 操作菜单
        menuOperations = new JMenu("操作");
        inquireItem = createMenuItem("查询");
        inItem = createMenuItem("入库");
        outItem = createMenuItem("出库");
        managerItem = createMenuItem("仓库管理");

        menuOperations.add(inquireItem);
        menuOperations.add(inItem);
        menuOperations.add(outItem);
        menuOperations.add(managerItem);

        // 设置菜单
        menuSettings = new JMenu("设置");
        fileItem = createMenuItem("人员档案管理");
        personItem = createMenuItem("个人中心");

        menuSettings.add(fileItem);
        menuSettings.add(personItem);

        // 账户菜单
        menuAccount = new JMenu("账户");
        logoutItem = createMenuItem("注销");
        exitItem = createMenuItem("退出");

        menuAccount.add(logoutItem);
        menuAccount.add(exitItem);

        // 添加菜单到菜单栏
        menuBar.add(menuOperations);
        menuBar.add(menuSettings);
        menuBar.add(menuAccount);

        // 将菜单栏添加到窗口
        setJMenuBar(menuBar);

        // 顶部标题
        JLabel nameLabel = new JLabel("仓库管理系统中心界面", JLabel.CENTER);
        nameLabel.setFont(new Font("楷体", Font.PLAIN, 60));
        contentPane.add(nameLabel, BorderLayout.NORTH);

        // 背景图片
        JLabel background = new JLabel(new ImageIcon("src/org/example/warehouse/BG.jpg"));
        contentPane.add(background, BorderLayout.CENTER);

        // 窗体设置
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }

    // 创建菜单项并添加事件监听
    private JMenuItem createMenuItem(String text) {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.addActionListener(mainHandler);  // 使用与按钮相同的事件处理程序
        return menuItem;
    }
}
