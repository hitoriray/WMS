package org.example.warehouse.view;

import org.example.warehouse.dao.PermissionDao;
import org.example.warehouse.dao.UserTotalDao;
import org.example.warehouse.handler.MainHandler;
import org.example.warehouse.dao.boundDao;
import org.example.warehouse.dao.warehouseDao;
import org.example.warehouse.handler.inHandler.InboundHandler;
import org.example.warehouse.handler.outHander.OutboundHandler;
import org.example.warehouse.view.File.AddPersonView;
import org.example.warehouse.view.File.InquirePersonView;
import org.example.warehouse.view.File.Permission.RevisionPermissionView;
import org.example.warehouse.view.File.Permission.ShowPermission;
import org.example.warehouse.view.File.RevisionPersonView;
import org.example.warehouse.view.Manager.*;
import org.example.warehouse.service.impl.ShowDataInformation;
import org.example.warehouse.view.Inquire.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainView extends JFrame {

    private JMenuBar menuBar;
    private JMenu menuOperations, menuSettings, menuAccount;
    private JMenuItem personItem, logoutItem, exitItem;
    private JMenu menuInquire, menuInbound, menuOutbound, menuManager, menuPersonManager;  // 添加仓库管理菜单
    private MainHandler mainHandler;
    private InboundHandler inboundHandler;
    private OutboundHandler outboundHandler;

    public MainView(LoginView loginView) {
        super("仓库管理系统");

        // 初始化事件处理器
        mainHandler = new MainHandler(this, loginView);
        inboundHandler = new InboundHandler(loginView);  // 初始化 InboundHandler
        outboundHandler = new OutboundHandler(loginView);  // 初始化 OutboundHandler

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // 设置菜单栏
        menuBar = new JMenuBar();

        // 操作菜单
        menuOperations = new JMenu("操作");

        // 添加查询子菜单
        menuInquire = new JMenu("查询");
        JMenuItem menuItem1 = new JMenuItem("查询所有货物");
        JMenuItem menuItem2 = new JMenuItem("查询单个货物");
        JMenuItem menuItem3 = new JMenuItem("查询剩余库存");
        JMenuItem menuItem4 = new JMenuItem("查询入库情况");
        JMenuItem menuItem5 = new JMenuItem("查询出库情况");

        // 将查询选项加入到查询子菜单
        menuInquire.add(menuItem1);
        menuInquire.add(menuItem2);
        menuInquire.addSeparator();  // 分隔线
        menuInquire.add(menuItem3);
        menuInquire.add(menuItem4);
        menuInquire.add(menuItem5);

        // 添加事件监听器
        menuItem1.addActionListener(new InquireMenuActionListener());
        menuItem2.addActionListener(new InquireMenuActionListener());
        menuItem3.addActionListener(new InquireMenuActionListener());
        menuItem4.addActionListener(new InquireMenuActionListener());
        menuItem5.addActionListener(new InquireMenuActionListener());

        menuOperations.add(menuInquire);

        // 入库子菜单
        menuInbound = new JMenu("入库");
        JMenuItem simpleInbound = new JMenuItem("简单物料入库");
        JMenuItem multipleInbound = new JMenuItem("多物料入库");

        menuInbound.add(simpleInbound);
        menuInbound.add(multipleInbound);

        // 为入库菜单项添加事件监听器
        simpleInbound.addActionListener(inboundHandler);  // 绑定 inboundHandler
        multipleInbound.addActionListener(inboundHandler);  // 绑定 inboundHandler

        menuOperations.add(menuInbound);

        // 出库子菜单
        menuOutbound = new JMenu("出库");
        JMenuItem simpleOutbound = new JMenuItem("简单物料出库");
        JMenuItem multipleOutbound = new JMenuItem("多物料出库");

        menuOutbound.add(simpleOutbound);
        menuOutbound.add(multipleOutbound);

        // 为出库菜单项添加事件监听器
        simpleOutbound.addActionListener(outboundHandler);  // 绑定 outboundHandler
        multipleOutbound.addActionListener(outboundHandler);  // 绑定 outboundHandler

        menuOperations.add(menuOutbound);  // 将出库菜单添加到操作菜单

        // **仓库管理菜单及其子菜单**
        menuManager = new JMenu("仓库管理");  // 仓库管理主菜单

        // 货物信息管理子菜单
        JMenu itemManagementMenu = new JMenu("货物信息管理");
        JMenuItem addItem = new JMenuItem("添加货物");
        JMenuItem modifyItem = new JMenuItem("修改货物");
        JMenuItem deleteItem = new JMenuItem("查询（删除）货物");



        // 为货物信息管理子菜单项添加事件监听器
        addItem.addActionListener(e -> new AddItemView());
        modifyItem.addActionListener(e -> new RevisionItemView());
        deleteItem.addActionListener(e -> {
            List<warehouseDao> list = ShowDataInformation.getck();
            new ShowckView1(list);
        });

        // 添加货物管理子菜单项到货物信息管理子菜单
        itemManagementMenu.add(addItem);
        itemManagementMenu.add(modifyItem);
        itemManagementMenu.add(deleteItem);

        // 仓库设置子菜单
        JMenu warehouseSetupMenu = new JMenu("仓库设置");
        JMenuItem viewSetup = new JMenuItem("查看设置");
        JMenuItem modifySetup = new JMenuItem("修改设置");

        // 为仓库设置子菜单项添加事件监听器
        viewSetup.addActionListener(e -> {
            List<warehouseDao> list = ShowDataInformation.getckSetup();
            new LookSetupView(list);
        });
        modifySetup.addActionListener(e -> new RevisionSetupView());

        // 添加仓库设置子菜单项到仓库设置子菜单
        warehouseSetupMenu.add(viewSetup);
        warehouseSetupMenu.add(modifySetup);

        // 将货物信息管理和仓库设置子菜单加入到仓库管理菜单
        menuManager.add(itemManagementMenu);
        menuManager.add(warehouseSetupMenu);

        // 将仓库管理菜单添加到操作菜单
        menuOperations.add(menuManager);

        // 设置菜单
        menuSettings = new JMenu("设置");
//        fileItem = createMenuItem("人员档案管理");

        menuPersonManager = new JMenu("人员档案管理");

        JMenu personInformationManagementMenu = new JMenu("人员信息管理");
        JMenuItem inquireDeleteItem = new JMenuItem("查询（删除）人员信息");
        JMenuItem addPersonInfoItem = new JMenuItem("添加人员信息");
        JMenuItem revisionPersonInfoItem = new JMenuItem("修改人员信息");

        inquireDeleteItem.addActionListener(e -> {
            List<UserTotalDao> list = ShowDataInformation.getInformation();
            new InquirePersonView(list);
        });
        addPersonInfoItem.addActionListener(e->new AddPersonView());
        revisionPersonInfoItem.addActionListener(e->new RevisionPersonView(loginView));

        personInformationManagementMenu.add(inquireDeleteItem);
        personInformationManagementMenu.add(addPersonInfoItem);
        personInformationManagementMenu.add(revisionPersonInfoItem);

        JMenu personPermissionManagementMenu = new JMenu("人员权限管理");
        JMenuItem checkRevisionPermissionItem = new JMenuItem("查看（修改）人员权限");
        checkRevisionPermissionItem.addActionListener(e-> {
            List<PermissionDao> list = ShowDataInformation.getPermissionInformation();
            new ShowPermission(list);
        });
        personPermissionManagementMenu.add(checkRevisionPermissionItem);

        menuPersonManager.add(personInformationManagementMenu);
        menuPersonManager.add(personPermissionManagementMenu);

        personItem = createMenuItem("个人中心");

        menuSettings.add(menuPersonManager);
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
//        menuItem.setFont(new Font("华文行楷", Font.PLAIN, 18));
        menuItem.addActionListener(mainHandler);  // 使用与按钮相同的事件处理程序
        return menuItem;
    }

    // 处理查询子菜单的事件
    private class InquireMenuActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem menuItem = (JMenuItem) e.getSource();
            String text = menuItem.getText();

            if (text.equals("查询单个货物")) {
                new SingleckView();
            } else if (text.equals("查询所有货物")) {
                List<warehouseDao> list = ShowDataInformation.getck();
                new ShowckView(list);
            } else if (text.equals("查询剩余库存")) {
                List<warehouseDao> list = ShowDataInformation.getck();
                new RemainView(list);
            } else if (text.equals("查询入库情况")) {
                List<boundDao> list1 = ShowDataInformation.getInbound();
                new INBoundView(list1);
            } else if (text.equals("查询出库情况")) {
                List<boundDao> list1 = ShowDataInformation.getOutbound();
                new OUTBoundView(list1);
            }
        }
    }
}
