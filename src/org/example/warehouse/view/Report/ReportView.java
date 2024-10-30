package org.example.warehouse.view.Report;

import com.toedter.calendar.JDateChooser;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.example.warehouse.handler.ReportHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportView extends JFrame {
    private final JPanel jPanel = new JPanel(new GridBagLayout());
    private final JLabel label = new JLabel("出入库类型：");
    private final JComboBox<String> labelJB = new JComboBox<>();
    private final JLabel startTimeLabel = new JLabel("开始时间：");
    private final JDateChooser startDateChooser = new JDateChooser(); // 使用 JDateChooser
    private final JLabel endTimeLabel = new JLabel("结束时间：");
    private final JDateChooser endDateChooser = new JDateChooser(); // 使用 JDateChooser
    private final JButton sureBtn = new JButton("确认");
    private final ReportHandler reportHandler;

    public ReportView() {
        super("打印出入库记录");
        reportHandler = new ReportHandler(this);
        Container contentPane = getContentPane();
        Font font = new Font("宋体", Font.PLAIN, 25);

        label.setFont(font);
        startTimeLabel.setFont(font);
        endTimeLabel.setFont(font);
        sureBtn.setFont(font);

        labelJB.setFont(font);
        labelJB.addItem("所有出入库");
        labelJB.addItem("入库");
        labelJB.addItem("出库");

        startDateChooser.setFont(font);
        endDateChooser.setFont(font);

        // 设置日期选择器的首选大小
        startDateChooser.setPreferredSize(new Dimension(200, 30));
        endDateChooser.setPreferredSize(new Dimension(200, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // 增加间距
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        jPanel.add(label, gbc);

        gbc.gridx = 1;
        jPanel.add(labelJB, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        jPanel.add(startTimeLabel, gbc);

        gbc.gridx = 1;
        jPanel.add(startDateChooser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        jPanel.add(endTimeLabel, gbc);

        gbc.gridx = 1;
        jPanel.add(endDateChooser, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        jPanel.add(sureBtn, gbc);

        sureBtn.addActionListener(reportHandler);

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

    public String getStartTime() {
        LocalDate date = startDateChooser.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        return date.toString();
    }

    public String getEndTime() {
        LocalDate date = endDateChooser.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        return date.toString();
    }

    public void outReport(JTable table, String fileName) {
        Workbook workbook = new HSSFWorkbook();
        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
            Sheet sheet = workbook.createSheet("Sheet1");

            // 标题样式
            HSSFCellStyle titleStyle = (HSSFCellStyle) workbook.createCellStyle();
            HSSFFont titleFont = (HSSFFont) workbook.createFont();
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 16);
            titleStyle.setFont(titleFont);
            titleStyle.setAlignment(HorizontalAlignment.CENTER);

            // 表头样式
            HSSFCellStyle headerStyle = (HSSFCellStyle) workbook.createCellStyle();
            HSSFFont headerFont = (HSSFFont) workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12);
            headerStyle.setFont(headerFont);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBottomBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());

            // 设置标题
            Row titleRow = sheet.createRow(0);
            Cell titleCell = titleRow.createCell(0);
            String titleText = "仓库出入库报告";
            titleCell.setCellValue(titleText);
            titleCell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, table.getColumnCount() - 1));

            // 写入表头
            Row headerRow = sheet.createRow(1);
            for (int i = 0; i < table.getColumnCount(); i++) {
                Cell headerCell = headerRow.createCell(i);
                headerCell.setCellValue(table.getColumnName(i));
                headerCell.setCellStyle(headerStyle);
            }

            // 设置单元格样式
            HSSFCellStyle cellStyle = (HSSFCellStyle) workbook.createCellStyle();
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);

            // 写入数据
            for (int i = 0; i < table.getRowCount(); i++) {
                Row row = sheet.createRow(i + 2);
                for (int j = 0; j < table.getColumnCount(); j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(table.getValueAt(i, j).toString());
                    cell.setCellStyle(cellStyle);
                }
            }

            // 自动调整列宽
            for (int i = 0; i < table.getColumnCount(); i++) {
                sheet.autoSizeColumn(i);
            }

            // 添加打印时间
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String currentTime = now.format(formatter);
            Row timeRow = sheet.createRow(table.getRowCount() + 2);
            Cell timeCell = timeRow.createCell(0);
            timeCell.setCellValue("打印时间：" + currentTime);

            // 写入文件
            workbook.write(outputStream);
            JOptionPane.showMessageDialog(null, "打印成功，文件保存在 " + fileName, "打印记录", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "保存文件时出现错误: " + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ReportView();
    }
}
