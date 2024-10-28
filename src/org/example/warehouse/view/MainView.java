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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class MainView extends JFrame {

    private JMenuBar menuBar;
    private JMenu menuOperations, menuSettings, menuAccount;
    private JMenuItem personItem, logoutItem, exitItem, reportItem;
    private JMenu menuInquire, menuInbound, menuOutbound, menuManager, menuPersonManager;  // 添加仓库管理菜单
    private MainHandler mainHandler;
    private InboundHandler inboundHandler;
    private OutboundHandler outboundHandler;

    private JLabel totalFlowLabel;
    private JLabel leastFlowLabel;
    private JTable inboundTable;
    private JTable outboundTable;
    private JPanel chartPanel;

    public MainView(LoginView loginView) {
        super("仓库管理系统");

//        setLayout(new BorderLayout());
        JPanel statsPanel = new JPanel(new GridLayout(2, 1));
        totalFlowLabel = new JLabel("总进出仓流量");
        leastFlowLabel = new JLabel("流动量最小的物料：");
        // 添加标签到统计面板
        statsPanel.add(totalFlowLabel);
        statsPanel.add(leastFlowLabel);
        // 更新统计数据
        updateStatistics();
        // 显示入库数据
        JPanel inboundPanel = createBoundTablePanel("入库数据", ShowDataInformation.getInbound());
        // 显示出库数据
        JPanel outboundPanel = createBoundTablePanel("出库数据", ShowDataInformation.getOutbound());
        // 创建图表展示区域
        chartPanel = createFlowChartPanel();
        // 将统计面板、图表面板添加到主界面
//        add(statsPanel, BorderLayout.NORTH);
//        add(inboundPanel, BorderLayout.WEST);
//        add(outboundPanel, BorderLayout.EAST);
//        add(chartPanel, BorderLayout.CENTER);


        // 初始化事件处理器
        mainHandler = new MainHandler(this, loginView);
        inboundHandler = new InboundHandler(loginView);  // 初始化 InboundHandler
        outboundHandler = new OutboundHandler(loginView);  // 初始化 OutboundHandler

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // 设置菜单栏
        menuBar = new JMenuBar();

        // 操作菜单
        menuOperations = createMenu("操作");

        // 添加查询子菜单
        menuInquire = createMenu("查询");
        JMenuItem menuItem1 = createMenuItem("查询所有货物");
        JMenuItem menuItem2 = createMenuItem("查询单个货物");
        JMenuItem menuItem3 = createMenuItem("查询剩余库存");
        JMenuItem menuItem4 = createMenuItem("查询入库情况");
        JMenuItem menuItem5 = createMenuItem("查询出库情况");

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
        menuInbound = createMenu("入库");
        JMenuItem simpleInbound = createMenuItem("简单物料入库");
        JMenuItem multipleInbound = createMenuItem("多物料入库");

        menuInbound.add(simpleInbound);
        menuInbound.add(multipleInbound);

        // 为入库菜单项添加事件监听器
        simpleInbound.addActionListener(inboundHandler);  // 绑定 inboundHandler
        multipleInbound.addActionListener(inboundHandler);  // 绑定 inboundHandler

        menuOperations.add(menuInbound);

        // 出库子菜单
        menuOutbound = createMenu("出库");
        JMenuItem simpleOutbound = createMenuItem("简单物料出库");
        JMenuItem multipleOutbound = createMenuItem("多物料出库");

        menuOutbound.add(simpleOutbound);
        menuOutbound.add(multipleOutbound);

        // 为出库菜单项添加事件监听器
        simpleOutbound.addActionListener(outboundHandler);  // 绑定 outboundHandler
        multipleOutbound.addActionListener(outboundHandler);  // 绑定 outboundHandler

        menuOperations.add(menuOutbound);  // 将出库菜单添加到操作菜单

        // **仓库管理菜单及其子菜单**
        menuManager = createMenu("仓库管理");  // 仓库管理主菜单

        // 货物信息管理子菜单
        JMenu itemManagementMenu = createMenu("货物信息管理");
        JMenuItem addItem = createMenuItem("添加货物");
        JMenuItem modifyItem = createMenuItem("修改货物");
        JMenuItem deleteItem = createMenuItem("查询（删除）货物");

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
        JMenu warehouseSetupMenu = createMenu("仓库设置");
        JMenuItem viewSetup = createMenuItem("查看设置");
        JMenuItem modifySetup = createMenuItem("修改设置");

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

        reportItem = createItem("打印报表");
        menuOperations.add(reportItem);

        // 设置菜单
        menuSettings = createMenu("设置");
        menuPersonManager = createMenu("人员档案管理");
        JMenu personInformationManagementMenu = createMenu("人员信息管理");
        JMenuItem inquireDeleteItem = createMenuItem("查询（删除）人员信息");
        JMenuItem addPersonInfoItem = createMenuItem("添加人员信息");
        JMenuItem revisionPersonInfoItem = createMenuItem("修改人员信息");

        inquireDeleteItem.addActionListener(e -> {
            List<UserTotalDao> list = ShowDataInformation.getInformation();
            new InquirePersonView(list);
        });
        addPersonInfoItem.addActionListener(e->new AddPersonView());
        revisionPersonInfoItem.addActionListener(e->new RevisionPersonView(loginView));

        personInformationManagementMenu.add(inquireDeleteItem);
        personInformationManagementMenu.add(addPersonInfoItem);
        personInformationManagementMenu.add(revisionPersonInfoItem);

        JMenu personPermissionManagementMenu = createMenu("人员权限管理");
        JMenuItem checkRevisionPermissionItem = createMenuItem("查看（修改）人员权限");
        checkRevisionPermissionItem.addActionListener(e-> {
            List<PermissionDao> list = ShowDataInformation.getPermissionInformation();
            new ShowPermission(list);
        });
        personPermissionManagementMenu.add(checkRevisionPermissionItem);

        menuPersonManager.add(personInformationManagementMenu);
        menuPersonManager.add(personPermissionManagementMenu);

        personItem = createItem("修改密码");

        menuSettings.add(menuPersonManager);
        menuSettings.add(personItem);

        // 账户菜单
        menuAccount = createMenu("账户");
        logoutItem = createItem("注销");
        exitItem = createItem("退出");

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
        nameLabel.setFont(new Font("楷体", Font.PLAIN, 50));
        contentPane.add(nameLabel, BorderLayout.NORTH);

        // 背景图片
//        JLabel background = new JLabel(new ImageIcon("src/org/example/warehouse/BG.jpg"));
//        contentPane.add(background, BorderLayout.CENTER);
        setContentPane(new JLabel(new ImageIcon("src/org/example/warehouse/BG.jpg")));
        setLayout(new BorderLayout());
        add(statsPanel, BorderLayout.NORTH);
        add(inboundPanel, BorderLayout.WEST);
        add(outboundPanel, BorderLayout.EAST);
        add(chartPanel, BorderLayout.CENTER); // 图表依然在中间

        // 窗体设置
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }

    // 计算总的进出仓流量
    private int calculateTotalFlow() {
        List<boundDao> inboundList = ShowDataInformation.getInbound();
        List<boundDao> outboundList = ShowDataInformation.getOutbound();
        int totalFlow = inboundList.size() + outboundList.size();
        return totalFlow;
    }

    // 更新统计数据
    private void updateStatistics() {
        // 从服务中获取数据
        int totalFlow = calculateTotalFlow(); // 你可以从ShowDataInformation中提取数据计算
        String leastFlowMaterial = findLeastFlowMaterial(); // 从数据库中找到流动量最小的物料

        totalFlowLabel.setText("总进出仓流量：" + totalFlow);
        leastFlowLabel.setText("流动量最小的物料：" + leastFlowMaterial);
    }

    // 查找流动量最小的物料
    private String findLeastFlowMaterial() {
        // 逻辑：遍历物料，计算每个物料的流动量，返回最小流动量的物料名称
        // 这里简化为返回固定值
        return "物料X";
    }

    // 创建显示入库、出库数据的表格
    private JPanel createBoundTablePanel(String title, List<boundDao> boundList) {
        String[] columnNames = {"单号", "物料ID", "数量", "类型", "物料名称", "时间"};
        Object[][] data = new Object[boundList.size()][columnNames.length];

        for (int i = 0; i < boundList.size(); i++) {
            boundDao bound = boundList.get(i);
            data[i][0] = bound.getDanhao();
            data[i][1] = bound.getId();
            data[i][2] = bound.getNumber();
            data[i][3] = bound.getBoundtype();
            data[i][4] = bound.getName();
            data[i][5] = bound.getTime();
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(title), BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    // 创建物料进出仓流量的图表
    private JPanel createFlowChartPanel() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(100, "进仓", "一月");
        dataset.addValue(80, "出仓", "一月");
        dataset.addValue(120, "进仓", "二月");
        dataset.addValue(90, "出仓", "二月");

        JFreeChart chart = ChartFactory.createBarChart(
                "物料进出仓流量",           // 标题
                "月份",                    // X轴标签
                "数量",                    // Y轴标签
                dataset,                   // 数据集
                PlotOrientation.VERTICAL,  // 图表方向（垂直）
                true,                      // 是否显示图例
                true,                      // 是否生成工具提示
                false                      // 是否生成URL
        );


        return new ChartPanel(chart);
    }

    private JMenu createMenu(String text) {
        JMenu menu = new JMenu(text);
        menu.setFont(new Font("楷体", Font.PLAIN, 20));
        return menu;
    }

    private JMenuItem createMenuItem(String text) {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.setFont(new Font("楷体", Font.PLAIN, 20));
        return menuItem;
    }

    // 创建菜单项并添加事件监听
    private JMenuItem createItem(String text) {
        JMenuItem item = new JMenuItem(text);
        item.setFont(new Font("楷体", Font.PLAIN, 20));
        item.addActionListener(mainHandler);  // 使用与按钮相同的事件处理程序
        return item;
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
