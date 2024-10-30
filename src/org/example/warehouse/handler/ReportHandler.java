package org.example.warehouse.handler;

import org.example.warehouse.dao.boundDao;
import org.example.warehouse.service.ReportService;
import org.example.warehouse.service.impl.ReportServiceImpl;
import org.example.warehouse.view.Report.ReportView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class ReportHandler implements ActionListener {
    private ReportView reportView;

    public ReportHandler(ReportView reportView) {
        this.reportView = reportView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String labelJB = reportView.getLabelJB();
        String startTime = reportView.getStartTime();
        String endTime = reportView.getEndTime();

        ReportService reportService = new ReportServiceImpl();
        List<boundDao> list = reportService.bound(labelJB, startTime, endTime);

        if (list.size() == 0) {
            JOptionPane.showMessageDialog(null, "没有符合条件的记录！", "打印记录", 2);
        } else {
            String[] index = {"单号", "物品编号", "数量", "类型", "操作人员名称", "时间"};
            Object[][] data = new Object[list.size()][index.length];
            for (int i = 0; i < list.size(); i++) {
                boundDao bo = list.get(i);
                data[i][0] = bo.getDanhao();
                data[i][1] = bo.getId();
                data[i][2] = bo.getNumber();
                data[i][3] = bo.getBoundtype();
                data[i][4] = bo.getName();
                data[i][5] = bo.getTime();
            }
            JTable table = new JTable(data, index);
            String filePath = "C:\\Users\\ray\\Desktop\\" + labelJB + "信息.xls";
            reportView.outReport(table, filePath);
            JOptionPane.showMessageDialog(null, "打印成功，文件保存在 " + filePath, "打印记录", 1);
        }
    }
}