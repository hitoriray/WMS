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
        String nameTxt = reportView.getNameTxt();
        ReportService reportService = new ReportServiceImpl();
        List<boundDao> list = reportService.bound(labelJB, nameTxt);
        if (list.size() == 0) {
            JOptionPane.showMessageDialog(null, "您输入的单号有误！", "打印单号", 2);
        } else {
            String[] index = {"单号", "物品编号", "入库数量", "类型", "操作人员名称", "入库时间"};
            Object[][] data = new Object[list.size()][index.length];
            System.out.println("size:" + list.size());
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
            try {
                if (data[0][3].equals("入库")) {
                    reportView.OutReport(table, "C:\\Users\\ray\\Desktop\\入库信息.xls");
                } else if (data[0][3].equals("出库")) {
                    reportView.OutReport(table, "C:\\Users\\ray\\Desktop\\出库信息.xls");
                }
                JOptionPane.showMessageDialog(null, "打印成功", "打印单号", 1);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
