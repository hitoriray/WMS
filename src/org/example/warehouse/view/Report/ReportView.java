package org.example.warehouse.view.Report;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.example.warehouse.handler.ReportHandler;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportView extends JFrame {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 50));
    JLabel label = new JLabel("出入库类型：");
    JComboBox labelJB = new JComboBox();
    JLabel NameLabel = new JLabel("请输入打印单号：");
    JTextField NameTxt = new JTextField();
    JButton SureBtn = new JButton("确认");
    ReportHandler reportHandler;

    public ReportView() {
        super("打印单号");
        reportHandler = new ReportHandler(this);
        Container contentPane = getContentPane();
        Font font = new Font("宋体", Font.PLAIN, 25);
        Dimension dimension = new Dimension(120, 25);
        label.setFont(font);
        NameLabel.setFont(font);
        NameTxt.setPreferredSize(dimension);
        labelJB.setPreferredSize(dimension);
        SureBtn.setFont(new Font("宋体", Font.PLAIN, 25));


        labelJB.addItem("入库");
        labelJB.addItem("出库");
        jPanel.add(label);
        jPanel.add(labelJB);
        jPanel.add(NameLabel);
        jPanel.add(NameTxt);
        jPanel.add(SureBtn);
        SureBtn.addActionListener(reportHandler);
        contentPane.add(jPanel);
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public String getLabelJB() {
        return labelJB.getSelectedItem().toString();
    }

    public String getNameTxt() {
        return NameTxt.getText();
    }

    public void OutReport(JTable table, String fileName) throws IOException {
        // 创建工作簿
        Workbook workbook = new HSSFWorkbook();

        // 创建工作表
        Sheet sheet = workbook.createSheet("Sheet1");

        // 写入表头
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < table.getColumnCount(); i++) {
            Cell headerCell = headerRow.createCell(i);
            headerCell.setCellValue(table.getColumnName(i));
        }
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentTime = now.format(formatter);
        // 写入数据
        for (int i = 0; i < table.getRowCount(); i++) {
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < table.getColumnCount(); j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(table.getValueAt(i, j).toString());
            }
        }
        Row timeRow = sheet.createRow(table.getRowCount() + 1);
        Cell timeCell = timeRow.createCell(0);
        timeCell.setCellValue("打印时间：" + currentTime);

        // 写入文件
        FileOutputStream outputStream = new FileOutputStream(fileName);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
