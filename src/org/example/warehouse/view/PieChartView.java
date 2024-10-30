package org.example.warehouse.view;

import org.example.warehouse.dao.boundDao;
import org.example.warehouse.service.impl.ShowDataInformation;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PieChartView extends JFrame {

    public PieChartView() {
        setTitle("货物入库和出库数量统计");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        showStatisticsChartByItem();
        showStatisticsChartByUser();

        setVisible(true);
    }

    private void customizePieChart(JFreeChart chart) {
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
                "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0.00%")));
//        plot.setSimpleLabels(true); // 设置为 true 使得标签更简洁
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12)); // 设置标签字体
    }

    private void showStatisticsChartByItem() {
        System.out.println("Loading statistics by item...");

        String startDate = "2024-01-01";
        String endDate = "2024-12-31";
        List<boundDao> statisticsData = ShowDataInformation.getStatisticsByItem(startDate, endDate);

        // 选择绘制图表类型（饼状图）
        JFreeChart chart = createPieChartByItem(statisticsData);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setOpaque(false); // 确保图表面板透明
        chartPanel.setPreferredSize(new Dimension(600, 400)); // 设置合适的尺寸
//        panel.add(chartPanel, BorderLayout.WEST); // 使用 CENTER 位置
    }

    private void showStatisticsChartByUser() {
        System.out.println("Loading statistics by user...");

        String startDate = "2024-01-01";
        String endDate = "2024-12-31";
        List<boundDao> statisticsData = ShowDataInformation.getStatisticsByUser(startDate, endDate);

        // 选择绘制图表类型（饼状图）
        JFreeChart chart = createPieChartByUser(statisticsData);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setOpaque(false); // 确保图表面板透明
        chartPanel.setPreferredSize(new Dimension(600, 400)); // 设置合适的尺寸
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
}
