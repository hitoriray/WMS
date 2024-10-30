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
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static org.jfree.chart.ChartFactory.createBarChart;
import static org.jfree.chart.ChartFactory.createPieChart;

public class MainView extends JFrame {

    private JMenuBar menuBar;
    private JMenu menuOperations, menuSettings, menuAccount;
    private JMenuItem personItem, logoutItem, exitItem, reportItem;
    private JMenu menuInquire, menuInbound, menuOutbound, menuManager, menuPersonManager;
    private MainHandler mainHandler;
    private InboundHandler inboundHandler;
    private OutboundHandler outboundHandler;

    public MainView(LoginView loginView) {
        super("仓库管理系统");

        // 初始化事件处理器
        mainHandler = new MainHandler(this, loginView);
        inboundHandler = new InboundHandler(loginView);
        outboundHandler = new OutboundHandler(loginView);

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
        menuInquire.addSeparator();
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

        JMenuItem statisticsItem = new JMenuItem("查看进出库统计");
        menuOperations.add(statisticsItem);

        // 入库子菜单
        menuInbound = new JMenu("入库");
        JMenuItem simpleInbound = new JMenuItem("简单物料入库");
        JMenuItem multipleInbound = new JMenuItem("多物料入库");

        menuInbound.add(simpleInbound);
        menuInbound.add(multipleInbound);
        simpleInbound.addActionListener(inboundHandler);
        multipleInbound.addActionListener(inboundHandler);
        menuOperations.add(menuInbound);

        // 出库子菜单
        menuOutbound = new JMenu("出库");
        JMenuItem simpleOutbound = new JMenuItem("简单物料出库");
        JMenuItem multipleOutbound = new JMenuItem("多物料出库");

        menuOutbound.add(simpleOutbound);
        menuOutbound.add(multipleOutbound);
        simpleOutbound.addActionListener(outboundHandler);
        multipleOutbound.addActionListener(outboundHandler);
        menuOperations.add(menuOutbound);

        // **仓库管理菜单及其子菜单**
        menuManager = new JMenu("仓库管理");
        JMenu itemManagementMenu = new JMenu("货物信息管理");
        JMenuItem addItem = new JMenuItem("添加货物");
        JMenuItem modifyItem = new JMenuItem("修改货物");
        JMenuItem deleteItem = new JMenuItem("查询（删除）货物");

        addItem.addActionListener(e -> new AddItemView());
        modifyItem.addActionListener(e -> new RevisionItemView());
        deleteItem.addActionListener(e -> {
            List<warehouseDao> list = ShowDataInformation.getck();
            new ShowckView1(list);
        });

        itemManagementMenu.add(addItem);
        itemManagementMenu.add(modifyItem);
        itemManagementMenu.add(deleteItem);

        JMenu warehouseSetupMenu = new JMenu("仓库设置");
        JMenuItem viewSetup = new JMenuItem("查看设置");
        JMenuItem modifySetup = new JMenuItem("修改设置");

        viewSetup.addActionListener(e -> {
            List<warehouseDao> list = ShowDataInformation.getckSetup();
            new LookSetupView(list);
        });
        modifySetup.addActionListener(e -> new RevisionSetupView());

        warehouseSetupMenu.add(viewSetup);
        warehouseSetupMenu.add(modifySetup);
        menuManager.add(itemManagementMenu);
        menuManager.add(warehouseSetupMenu);
        menuOperations.add(menuManager);

        // 添加检查更新菜单项
        JMenuItem checkUpdateItem = new JMenuItem("检查更新");
        checkUpdateItem.addActionListener(e -> new VersionInfoView()); // 这里调用更新查看的视图
        menuOperations.add(checkUpdateItem);

        reportItem = createMenuItem("打印报表");
        menuOperations.add(reportItem);

        // 设置菜单
        menuSettings = new JMenu("设置");
        menuPersonManager = new JMenu("人员档案管理");

        JMenu personInformationManagementMenu = new JMenu("人员信息管理");
        JMenuItem inquireDeleteItem = new JMenuItem("查询（删除）人员信息");
        JMenuItem addPersonInfoItem = new JMenuItem("添加人员信息");
        JMenuItem revisionPersonInfoItem = new JMenuItem("修改人员信息");

        inquireDeleteItem.addActionListener(e -> {
            List<UserTotalDao> list = ShowDataInformation.getInformation();
            new InquirePersonView(list);
        });
        addPersonInfoItem.addActionListener(e -> new AddPersonView());
        revisionPersonInfoItem.addActionListener(e -> new RevisionPersonView(loginView));

        personInformationManagementMenu.add(inquireDeleteItem);
        personInformationManagementMenu.add(addPersonInfoItem);
        personInformationManagementMenu.add(revisionPersonInfoItem);

        JMenu personPermissionManagementMenu = new JMenu("人员权限管理");
        JMenuItem checkRevisionPermissionItem = new JMenuItem("查看（修改）人员权限");
        checkRevisionPermissionItem.addActionListener(e -> {
            List<PermissionDao> list = ShowDataInformation.getPermissionInformation();
            new ShowPermission(list, loginView);
        });
        personPermissionManagementMenu.add(checkRevisionPermissionItem);

        menuPersonManager.add(personInformationManagementMenu);
        menuPersonManager.add(personPermissionManagementMenu);

        personItem = createMenuItem("修改密码");

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


        // 创建 JLayeredPane 以支持分层显示
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1200, 800)); // 设置大小

        // 背景图片
        JLabel background = new JLabel(new ImageIcon("src/org/example/warehouse/BG.jpg"));
        background.setBounds(0, 0, 1200, 800); // 设置位置和大小
        layeredPane.add(background, Integer.valueOf(0)); // 添加到底层

        // 创建透明背景的面板
        JPanel statisticsPanel = new JPanel();
        statisticsPanel.setLayout(new BorderLayout());
        statisticsPanel.setOpaque(false); // 设置为透明
        statisticsPanel.setBounds(0, 0, 1200, 800); // 设置与背景相同的大小
        // 调用显示统计信息的方法
        showStatisticsChartByItem(statisticsPanel);
        showStatisticsChartByUser(statisticsPanel);
        // 将统计面板添加到分层面板
        layeredPane.add(statisticsPanel, Integer.valueOf(1)); // 添加到上层
        // 将分层面板添加到内容面板
        contentPane.add(layeredPane, BorderLayout.CENTER);

        // 顶部标题
        JLabel nameLabel = new JLabel("仓库管理系统中心界面", JLabel.CENTER);
        nameLabel.setFont(new Font("楷体", Font.PLAIN, 60));
        contentPane.add(nameLabel, BorderLayout.NORTH);

        // 窗体设置
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }

    private void showStatisticsChartByItem(JPanel panel) {
        System.out.println("Loading statistics by item...");

        String startDate = "2024-01-01";
        String endDate = "2024-12-31";
        List<boundDao> statisticsData = ShowDataInformation.getStatisticsByItem(startDate, endDate);

        if (statisticsData.isEmpty()) {
            System.out.println("No data for item statistics.");
        } else {
            System.out.println("Data loaded for item statistics.");
        }

        // 选择绘制图表类型（饼状图）
        JFreeChart chart = createPieChartByItem(statisticsData);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setOpaque(false); // 确保图表面板透明
        chartPanel.setPreferredSize(new Dimension(600, 400)); // 设置合适的尺寸
        panel.add(chartPanel, BorderLayout.WEST); // 使用 CENTER 位置
    }

    private void showStatisticsChartByUser(JPanel panel) {
        System.out.println("Loading statistics by user...");

        String startDate = "2024-01-01";
        String endDate = "2024-12-31";
        List<boundDao> statisticsData = ShowDataInformation.getStatisticsByUser(startDate, endDate);

        if (statisticsData.isEmpty()) {
            System.out.println("No data for user statistics.");
        } else {
            System.out.println("Data loaded for user statistics.");
        }

        // 选择绘制图表类型（饼状图）
        JFreeChart chart = createPieChartByUser(statisticsData);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setOpaque(false); // 确保图表面板透明
        chartPanel.setPreferredSize(new Dimension(600, 400)); // 设置合适的尺寸
        panel.add(chartPanel, BorderLayout.EAST); // 使用 SOUTH 位置
    }

    // 创建饼状图
    private JFreeChart createPieChartByItem(List<boundDao> statisticsData) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        for (boundDao data : statisticsData) {
            dataset.setValue(ShowDataInformation.getItemNameById(data.getId()) + " - " + data.getBoundtype(), Integer.parseInt(data.getNumber()));
        }

        JFreeChart pieChart = ChartFactory.createPieChart(
                "出入库统计饼状图",  // 图表标题
                dataset,           // 数据集
                true,              // 是否显示图例
                true,
                false
        );

        // 设置饼图样式
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setCircular(true);
        return pieChart;
    }

    private JFreeChart createPieChartByUser(List<boundDao> statisticsData) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        for (boundDao data : statisticsData) {
            dataset.setValue(data.getName() + " - " + data.getBoundtype(), Integer.parseInt(data.getNumber()));
        }

        JFreeChart pieChart = ChartFactory.createPieChart(
                "出入库统计饼状图",  // 图表标题
                dataset,           // 数据集
                true,              // 是否显示图例
                true,
                false
        );

        // 设置饼图样式
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setCircular(true);
        return pieChart;
    }


    // 创建菜单项并添加事件监听
    private JMenuItem createMenuItem(String text) {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.addActionListener(mainHandler); // 使用与按钮相同的事件处理程序
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
